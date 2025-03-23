import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import cells.Cell;

public class BoardGUI extends JPanel {
    private final Board board;
    private final JSlider speedSlider;
    private final JSlider lifespanSlider;
    private final JButton startButton;
    private final JButton resetButton;
    private final JLabel speedLabel;
    private final JLabel lifespanLabel;
    private boolean running = false;
    private int speed;
    private final BoardPanel boardPanel;

    public BoardGUI(Board board) {
        this.board = board;
        this.speedSlider = new JSlider(100, 5000, 100);
        this.lifespanSlider = new JSlider(3, 200, 100);
        this.startButton = new JButton("Start");
        this.resetButton = new JButton("Reset");
        this.speedLabel = new JLabel("Speed: " + speedSlider.getValue());
        this.lifespanLabel = new JLabel("LifeSpan: " + lifespanSlider.getValue());
        this.boardPanel = new BoardPanel();
        setupUI();
        addListeners();
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(lifespanLabel);
        controlPanel.add(lifespanSlider);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        startButton.addActionListener(e -> toggleSimulation());
        resetButton.addActionListener(e -> resetBoard());
        lifespanSlider.addChangeListener(e -> updateLifespan());
        speedSlider.addChangeListener(e -> updateSpeed());
    }

    private void toggleSimulation() {
        running = !running;
        startButton.setText(running ? "Stop" : "Start");
        if (running) {
            new Thread(this::runSimulation).start();
        }
    }

    private void resetBoard() {
        running = false;
        board.reset();
        boardPanel.repaint();
    }

    private void updateLifespan() {
        int newLifespan = lifespanSlider.getValue();
        Cell.setMaxLifespan(newLifespan);
        lifespanLabel.setText("LifeSpan: " + newLifespan);
    }

    private void updateSpeed() {
        speed = speedSlider.getValue();
        speedLabel.setText("Speed: " + speed);
    }

    private void runSimulation() {
        while (running) {
            board.updateGrid();
            boardPanel.repaint();
            try {
                Thread.sleep(speedSlider.getValue());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class BoardPanel extends JPanel {
        private final int cellSize = 20;

        public BoardPanel() {
            int width = board.getGrid()[0].length * cellSize;
            int height = board.getGrid().length * cellSize;
            setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Cell[][] grid = board.getGrid();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    g.setColor(grid[i][j].getAlive() ? getCellColor(grid[i][j].getAge()) : Color.WHITE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }

        private Color getCellColor(int age) {
            return switch (age) {
                case 1 -> Color.BLACK;
                case 2 -> Color.DARK_GRAY;
                case 3 -> Color.GRAY;
                default -> Color.WHITE;
            };
        }
    }

    public static void main(String[] args) {
        Board board = new Board(35, 35);
        JFrame frame = new JFrame("Game of Hive");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoardGUI boardGUI = new BoardGUI(board);
        frame.getContentPane().add(boardGUI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
