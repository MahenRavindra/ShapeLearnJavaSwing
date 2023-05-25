import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TetrisGame extends JFrame {

    private Board board;

    private JButton backButton;

    public TetrisGame() {
        board = new Board();
        add(board);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);

        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1420, 1000);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    board.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    board.moveRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    board.moveDown();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    board.rotate();
                }
            }
        });

        setFocusable(true);
    }

    public static void main(String[] args) {

    }
}

class Board extends JPanel {

    private final int BOARD_WIDTH = 40;
    private final int BOARD_HEIGHT = 20;
    private final int CELL_SIZE = 30;

    private Timer timer;
    private boolean[][] board;
    private Point currentPiece;
    private int currentPieceType;
    private int currentRotation;
    private int score;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
        setBackground(Color.ORANGE);
        setFocusable(true);

        initBoard();
        startGame();
    }

    private void initBoard() {
        board = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
        currentPiece = new Point();
        currentPieceType = getRandomPiece();
        currentRotation = 0;
        score = 0;

        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveDown();
            }
        });
    }

    private void startGame() {
        timer.start();
    }

    private int getRandomPiece() {
        return (int) (Math.random() * 7);
    }

    public void moveDown() {
        if (canMove(currentPiece.x, currentPiece.y + 1, currentRotation)) {
            currentPiece.y++;
            repaint();
        } else {
            placePiece();
            checkRows();
            currentPiece.setLocation(BOARD_WIDTH / 2, 0);
            currentPieceType = getRandomPiece();
            currentRotation = 0;

            if (!canMove(currentPiece.x, currentPiece.y, currentRotation)) {
                gameOver();
            }
        }
    }

    public void moveLeft() {
        if (canMove(currentPiece.x - 1, currentPiece.y, currentRotation)) {
            currentPiece.x--;
            repaint();
        }
    }

    public void moveRight() {
        if (canMove(currentPiece.x + 1, currentPiece.y, currentRotation)) {
            currentPiece.x++;
            repaint();
        }
    }

    public void rotate() {
        int newRotation = (currentRotation + 1) % 4;
        if (canMove(currentPiece.x, currentPiece.y, newRotation)) {
            currentRotation = newRotation;
            repaint();
        }
    }

    private boolean canMove(int x, int y, int rotation) {
        int[][] piece = getPiece(currentPieceType, rotation);
        for (int row = 0; row < piece.length; row++) {
            for (int col = 0; col < piece[row].length; col++) {
                if (piece[row][col] == 1) {
                    int newX = x + col;
                    int newY = y + row;
                    if (newX < 0 || newX >= BOARD_WIDTH || newY >= BOARD_HEIGHT || (newY >= 0 && board[newY][newX])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placePiece() {
        int[][] piece = getPiece(currentPieceType, currentRotation);
        for (int row = 0; row < piece.length; row++) {
            for (int col = 0; col < piece[row].length; col++) {
                if (piece[row][col] == 1) {
                    int x = currentPiece.x + col;
                    int y = currentPiece.y + row;
                    if (y >= 0) {
                        board[y][x] = true;
                    }
                }
            }
        }
    }

    private int[][] getPiece(int type, int rotation) {
        int[][] piece = null;
        switch (type) {
            case 0: // I
                piece = new int[][]{{1, 1, 1, 1}};
                break;
            case 1: // J
                piece = new int[][]{{1, 1, 1}, {0, 0, 1}};
                break;
            case 2: // L
                piece = new int[][]{{1, 1, 1}, {1, 0, 0}};
                break;
            case 3: // O
                piece = new int[][]{{1, 1}, {1, 1}};
                break;
            case 4: // S
                piece = new int[][]{{0, 1, 1}, {1, 1, 0}};
                break;
            case 5: // T
                piece = new int[][]{{1, 1, 1}, {0, 1, 0}};
                break;
            case 6: // Z
                piece = new int[][]{{1, 1, 0}, {0, 1, 1}};
                break;
        }
        for (int i = 0; i < rotation; i++) {
            piece = rotatePiece(piece);
        }
        return piece;
    }

    private int[][] rotatePiece(int[][] piece) {
        int[][] rotatedPiece = new int[piece[0].length][piece.length];
        for (int row = 0; row < piece.length; row++) {
            for (int col = 0; col < piece[row].length; col++) {
                rotatedPiece[col][piece.length - 1 - row] = piece[row][col];
            }
        }
        return rotatedPiece;
    }

    private void checkRows() {
        for (int row = BOARD_HEIGHT - 1; row >= 0; row--) {
            boolean isRowFull = true;
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (!board[row][col]) {
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                score++;
                for (int y = row; y > 0; y--) {
                    for (int x = 0; x < BOARD_WIDTH; x++) {
                        board[y][x] = board[y - 1][x];
                    }
                }
                for (int x = 0; x < BOARD_WIDTH; x++) {
                    board[0][x] = false;
                }
            }
        }
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over\nScore: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        initBoard();
        startGame();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (board[row][col]) {
                    drawSquare(g, col * CELL_SIZE, row * CELL_SIZE);
                }
            }
        }

        int[][] piece = getPiece(currentPieceType, currentRotation);
        for (int row = 0; row < piece.length; row++) {
            for (int col = 0; col < piece[row].length; col++) {
                if (piece[row][col] == 1) {
                    int x = (currentPiece.x + col) * CELL_SIZE;
                    int y = (currentPiece.y + row) * CELL_SIZE;
                    drawSquare(g, x, y);
                }
            }
        }
    }

    private void drawSquare(Graphics g, int x, int y) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
    }
}
