import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import cells.Cell;

public class BoardGUI extends JPanel {
    private final Board board;
    private JSlider speedSlider;
    private boolean running = false;
    private int speed;

    public BoardGUI(Board board) {
        this.board = board;
        setupUI();
    }

    private void setupUI() {
        speedSlider = new JSlider(100, 5000, 100);
        JSlider lifespanSlider = new JSlider(10, 200, 100);
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = true;
                new Thread(() -> runSimulation()).start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
                board.reset();
                repaint();
            }
        });

        lifespanSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Cell.setMaxLifespan(lifespanSlider.getValue());

            }
        });

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speed = speedSlider.getValue();
            }
        });

        this.setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3));
        controlPanel.add(new JLabel("Speed:"));
        controlPanel.add(new JLabel(String.valueOf(speed)));
        controlPanel.add(speedSlider);
        controlPanel.add(startButton);
        controlPanel.add(new JLabel("Lifespan:"));
        controlPanel.add(new JLabel(String.valueOf(lifespanSlider.getValue())));
        controlPanel.add(lifespanSlider);
        controlPanel.add(stopButton);
        controlPanel.add(resetButton);

        this.add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] grid = board.getGrid();
        int cellSize = 20;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAlive()) {
                    if (grid[i][j].getAge() == 1) {
                        g.setColor(Color.BLACK);
                    } else if (grid[i][j].getAge() == 2) {
                        g.setColor(Color.DARK_GRAY);
                    } else if (grid[i][j].getAge() == 3) {
                        g.setColor(Color.GRAY);
                    }
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.WHITE);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    private void runSimulation() {
        while (running) {
            board.updateGrid();
            repaint();
            try {
                Thread.sleep(speedSlider.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board(35, 35);
        BoardGUI boardGUI = new BoardGUI(board);
        JFrame frame = new JFrame("Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(boardGUI);
        frame.setVisible(true);
    }
}