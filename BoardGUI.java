import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import cells.Cell;

public class BoardGUI extends JPanel {
    private final Board board;
    private final JSlider speedSlider;
    private final JSlider lifespanSlider;
    private final JButton startButton;
    private final JButton resetButton;
    private final JButton chartButton;
    private final JLabel speedLabel;
    private final JLabel lifespanLabel;
    private final JLabel generationLabel;
    private final JLabel populationLabel;
    private boolean running = false;
    private final BoardPanel boardPanel;
    private int generation=0;
    private int population=0;
    private ArrayList<Double> data = new ArrayList<>();

    public BoardGUI(Board board) {
        this.board = board;
        this.speedSlider = new JSlider(100, 5000, 100);
        this.lifespanSlider = new JSlider(3, 200, 100);
        this.startButton = new JButton("Start");
        this.resetButton = new JButton("Reset");
        this.chartButton = new JButton("Chart");
        this.speedLabel = new JLabel("Speed: " + speedSlider.getValue());
        this.lifespanLabel = new JLabel("LifeSpan: " + lifespanSlider.getValue());
        this.boardPanel = new BoardPanel();
        this.generationLabel = new JLabel("generation: "+ generation);
        this.populationLabel = new JLabel("population: "+ board.calculatePopulation());
        setupUI();
        addListeners();
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(lifespanLabel);
        controlPanel.add(lifespanSlider);
        controlPanel.add(generationLabel);
        controlPanel.add(populationLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(chartButton);

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        startButton.addActionListener(e -> toggleSimulation());
        resetButton.addActionListener(e -> resetBoard());
        chartButton.addActionListener(e -> showChart());
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
        int speed = speedSlider.getValue();
        speedLabel.setText("Speed: " + speed);
    }

    private void runSimulation() {
        while (running) {
            data.add(board.getAverageLifeSpan());
            board.updateGrid();
            generation++;
            boardPanel.repaint();
            try {
                Thread.sleep(speedSlider.getValue());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void showChart() {
        JFrame chartFrame = new JFrame("Line Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.add(new ChartPanel(data));
        chartFrame.setSize(800, 600);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.setVisible(true);
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
            generationLabel.setText("Generation: " + generation);
            populationLabel.setText("Population: " + board.calculatePopulation());
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    g.setColor(grid[i][j].getAlive() ? getCellColor(grid[i][j]) : Color.WHITE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }

        private Color getCellColor(Cell cell) {
            int age = cell.getAge();
            if (!cell.getAlive()){
                return Color.WHITE;
            }
            return switch (age) {
                case 1 -> Color.BLACK;
                case 2 -> Color.DARK_GRAY;
                default -> Color.GRAY;
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
