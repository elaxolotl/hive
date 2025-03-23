import javax.swing.*;
import java.awt.*;
import cells.Cell;

public class BoardGUI extends JPanel {
    private final Board board;

    public BoardGUI(Board board) {
        this.board = board;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] grid = board.getGrid();
        int cellSize = 20;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAlive()) {
                    if (grid[i][j].getAge()==1){
                        g.setColor(Color.BLACK);
                    } else if (grid[i][j].getAge()==2){g.setColor(Color.DARK_GRAY);}
                    else if (grid[i][j].getAge()==3){g.setColor(Color.GRAY);}

                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.WHITE);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(50, 50);
        BoardGUI boardGUI = new BoardGUI(board);
        JFrame frame = new JFrame("Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(boardGUI);
        frame.setVisible(true);
        boardGUI.repaint();
        Thread.sleep(100);

        while (true) {
            board.updateGrid();
            boardGUI.repaint();
            Thread.sleep(100);
        }
    }
}