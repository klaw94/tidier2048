package SecondCopy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecondCopy extends JFrame implements ActionListener {
    JPanel textPanel;
    JPanel grid;
    int[][] cells = new int[4][4];
    JLabel[][] cellLabels = new JLabel[4][4];
    JLabel textField;
    JFrame frame;
    int size = 4;

    int score = 0;
    boolean didIMove = false;
    boolean didIWin = false;


    public SecondCopy() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("2048");
        grid = new JPanel();
        grid.setLayout(new GridLayout(size, size));

        textPanel = new JPanel();
        textPanel.setVisible(true);
        textPanel.setBackground(new Color(252, 248, 232));

        textField = new JLabel();
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setForeground(new Color(148, 180, 159));
        textField.setFont(new Font("mv Boli", Font.BOLD, 24));
        textField.setText("Score : " + score);

        for (int i = 0; i < cells.length; i++) {
            for (int x = 0; x < cells.length; x++) {
                cellLabels[i][x] = new JLabel();
                cellLabels[i][x].setSize(200, 200);
                cellLabels[i][x].setHorizontalAlignment(SwingConstants.CENTER);
                cellLabels[i][x].setVerticalAlignment(SwingConstants.CENTER);
                cellLabels[i][x].setFont(new Font("mv Boli", Font.BOLD, 16));
                cellLabels[i][x].setBorder(BorderFactory.createLineBorder(new Color(118, 84, 154), 1));
                cellLabels[i][x].setText(" ");
                grid.add(cellLabels[i][x]);
            }
        }

        frame.add(textPanel, BorderLayout.NORTH);
        textPanel.add(textField);
        frame.add(grid);
        frame.setSize(400, 400);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.addKeyListener(new MyKeyAdapter());
        frame.setVisible(true);
        frame.revalidate();

        startGame();
    }

    private void startGame() {
        generateCell();
        generateCell();
        paintCells();
    }

    private void generateCell() {
        int num = 2;
        if ((int) Math.floor(Math.random() * 10) == 4) {
            num = 4;
        }
        int randomOne = (int) Math.floor(Math.random() * size);
        int randomTwo = (int) Math.floor(Math.random() * size);
        if (cellLabels[randomOne][randomTwo].getText().equals(" ")) {
            cells[randomOne][randomTwo] = num;
            cellLabels[randomOne][randomTwo].setText("" + num);
        } else {
            generateCell();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class MyKeyAdapter extends KeyAdapter {
        char direction = 'W';
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_RIGHT:
                    direction = 'R';
                    break;
                case KeyEvent.VK_LEFT:
                    direction = 'L';
                    break;
                case KeyEvent.VK_UP:
                    direction = 'U';

                    break;
                case KeyEvent.VK_DOWN:
                    direction = 'D';
                    break;
            }
            handleCellMovement(direction);
        }
    }

    private void handleCellMovement(char direction) {
        if (!didIWin) {
            didIMove = false;
            moveCells(direction);
            sumDoubles(direction);
            moveCells(direction);
            if (didIMove) {
                generateCell();
            }
            textField.setText("Score : " + score);
            paintCells();
            checkGameOver();
            checkVictory();
        }
    }

    private void checkVictory() {
        for (int i = 0; i < cells.length; i++) {
            for (int x = 0; x < cells.length; x++) {
                if (cells[i][x] == 2048) {
                    textField.setText("You Won!!");
                    didIWin = true;
                }
            }
        }
    }

    private void checkGameOver() {
        int previousNum = 25;
        for (int i = 0; i < cells.length; i++) {
            for (int x = 0; x < cells.length; x++) {
                if (cellLabels[i][x].getText().equals(" ") || cells[i][x] == previousNum) {
                    return;
                } else if (x != 3) {
                    previousNum = cells[i][x];
                } else if (x == 3) {
                    previousNum = 25;
                }
            }
        }
        previousNum = 25;
        for (int x = 0; x < cells.length; x++) {
            for (int i = 0; i < cells.length; i++) {
                if (cellLabels[i][x].getText().equals(" ") || cells[i][x] == previousNum) {
                    return;
                } else if (i != 3) {
                    previousNum = cells[i][x];
                } else if (i == 3) {
                    previousNum = 25;
                }
            }
        }
        textField.setText("You lost");
    }

    private void paintCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int x = 0; x < cells.length; x++) {
                if (cells[i][x] == 0) {
                    cellLabels[i][x].setOpaque(false);
                }
                if (cells[i][x] == 2) {
                    cellLabels[i][x].setBackground(new Color(119, 221, 118));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 4) {
                    cellLabels[i][x].setBackground(new Color(189, 231, 189));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 8) {
                    cellLabels[i][x].setBackground(new Color(231, 241, 232));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 16) {
                    cellLabels[i][x].setBackground(new Color(255, 213, 212));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 32) {
                    cellLabels[i][x].setBackground(new Color(255, 182, 179));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 64) {
                    cellLabels[i][x].setBackground(new Color(255, 105, 98));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 128) {
                    cellLabels[i][x].setBackground(new Color(195, 43, 14));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 256) {
                    cellLabels[i][x].setBackground(new Color(255, 152, 0));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 512) {
                    cellLabels[i][x].setBackground(new Color(255, 193, 0));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 1024) {
                    cellLabels[i][x].setBackground(new Color(255, 236, 25));
                    cellLabels[i][x].setOpaque(true);
                }
                if (cells[i][x] == 2048) {
                    cellLabels[i][x].setBackground(new Color(255, 105, 180));
                    cellLabels[i][x].setOpaque(true);
                }

            }

        }
    }

    private void moveCells(char direction) {
        int newX = 0;
        int newY = 0;
        //This determines the direction in which we are gonna move the loop. We loop in that direction and call moveCell in every single cell.
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells.length; x++) {
                if (direction == 'L') {
                    newY = y;
                    newX = x;
                }

                if (direction == 'R') {
                    newY = y;
                    newX = cells.length - 1 - x;
                }

                if (direction == 'U') {
                    newY = x;
                    newX = y;
                }

                if (direction == 'D') {
                    newY = cells.length - 1 - y;
                    newX = x;
                }

                moveSingleCell(direction, newY, newX);
            }
        }
    }

    private void moveSingleCell(char direction, int y, int x) {
        int newX, newY;
        //The cell is only doing the moving if it is not empty.
        if (cells[y][x] != 0) {
            for (int i = 0; i < cells.length; i++) {
                //This is "L"
                //If it is not empty, we loop through the rest of the row/column. The direction of the
                // loop depends on the direction of the movement
                newX = i;
                newY = y;
                if (direction == 'R') {
                    newX = cells.length - i - 1;
                    newY = y;
                } else if (direction == 'U') {
                    newX = x;
                    newY = i;
                } else if (direction == 'D') {
                    newX = x;
                    newY = cells.length - i - 1;
                }

                if (!cellLabels[newY][newX].getText().equals(" ") && newX == x && newY == y) {
                    //We stop when we arrive to the cell that we are moving.
                    break;
                } else if (cellLabels[newY][newX].getText().equals(" ")) {
                    //The cell gets the value visually and numerically.
                    cellLabels[newY][newX].setText("" + cells[y][x]);
                    cells[newY][newX] = cells[y][x];
                    //The cell that we are moving is reset to 0 and empty.
                    cells[y][x] = 0;
                    cellLabels[y][x].setText(" ");
                    didIMove = true;
                    break;
                }
            }
        }
    }



    private void sumDoubles(char direction) {
        int newX = 0;
        int newY = 0;
        int columnExtraNum = 0;
        int rowExtraNum = 0;
        for (int y = 0; y < cells.length; y++) {
            for (int x = 1; x < cells.length; x++) {

                if (direction == 'L') {
                    newY = y;
                    newX = x;
                    columnExtraNum = -1;
                    rowExtraNum = 0;

                }
                if (direction == 'R') {
                    newY = y;
                    newX = cells.length - 1 - x;
                    columnExtraNum = 1;
                    rowExtraNum = 0;

                }
                if (direction == 'U') {
                    newY = x;
                    newX =y;
                    rowExtraNum = - 1;
                    columnExtraNum = 0;

                }
                if (direction == 'D') {
                    newY = cells.length - 1 - x;
                    newX = y;
                    rowExtraNum = 1;
                    columnExtraNum = 0;

                }

                if (cells[newY][newX] != 0) {
                    if (Integer.parseInt(cellLabels[newY + rowExtraNum][newX + columnExtraNum].getText()) == cells[newY][newX]) {
                        cellLabels[newY+ rowExtraNum][newX + columnExtraNum].setText("" + cells[newY][newX] * 2);
                        cells[newY+ rowExtraNum][newX + columnExtraNum] = cells[newY][newX] * 2;
                        score += cells[newY][newX] * 2;
                        cells[newY][newX] = 0;
                        cellLabels[newY][newX].setText(" ");
                        didIMove = true;
                        x++;
                    }

                }

            }
        }
    }

    public static void main(String args[]) {
        SecondCopy game = new SecondCopy();
    }
}