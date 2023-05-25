import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends JFrame {
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 700;
    private static final int CELL_SIZE = 20;
    private static final int NUM_ROWS = FRAME_HEIGHT / CELL_SIZE;
    private static final int NUM_COLS = FRAME_WIDTH / CELL_SIZE;

    private List<Point> snake;
    private Point food;
    private Direction direction;
    private boolean gameOver;

    private JButton backButton;


    public SnakeGame() {
        setTitle("Snake Game");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && direction != Direction.DOWN)
                    direction = Direction.UP;
                else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != Direction.UP)
                    direction = Direction.DOWN;
                else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != Direction.RIGHT)
                    direction = Direction.LEFT;
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != Direction.LEFT)
                    direction = Direction.RIGHT;
            }
        });

        snake = new ArrayList<>();
        snake.add(new Point(NUM_COLS / 2, NUM_ROWS / 2));
        generateFood();
        direction = Direction.RIGHT;
        gameOver = false;

        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
                drawSnake(g);
                drawFood(g);
                setBackground(Color.ORANGE);
            }

            private void drawGrid(Graphics g) {
                g.setColor(Color.BLACK);
                for (int i = 0; i < NUM_COLS; i++) {
                    g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, FRAME_HEIGHT);
                }
                for (int i = 0; i < NUM_ROWS; i++) {
                    g.drawLine(0, i * CELL_SIZE, FRAME_WIDTH, i * CELL_SIZE);
                }
            }

            private void drawSnake(Graphics g) {
                g.setColor(Color.GREEN);
                for (Point point : snake) {
                    g.fillRect(point.x * CELL_SIZE, point.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }

            private void drawFood(Graphics g) {
                g.setColor(Color.RED);
                g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        };

        JButton upButton = new JButton("Up");


        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (direction != Direction.DOWN)
                    direction = Direction.UP;

            }
        });

        JButton downButton = new JButton("Down");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (direction != Direction.UP)
                    direction = Direction.DOWN;
            }
        });

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (direction != Direction.RIGHT)
                    direction = Direction.LEFT;
            }
        });

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (direction != Direction.LEFT)
                    direction = Direction.RIGHT;
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        buttonPanel.add(backButton);

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    moveSnake();
                    checkCollision();
                    gamePanel.repaint();
                }
            }
        });
        timer.start();
    }

    private void moveSnake() {
        Point head = snake.get(0);
        int dx = 0, dy = 0;
        switch (direction) {
            case UP:
                dy = -1;
                break;
            case DOWN:
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                break;
            case RIGHT:
                dx = 1;
                break;
        }
        int newX = (head.x + dx + NUM_COLS) % NUM_COLS;
        int newY = (head.y + dy + NUM_ROWS) % NUM_ROWS;
        Point newHead = new Point(newX, newY);
        snake.add(0, newHead);
        if (!newHead.equals(food)) {
            snake.remove(snake.size() - 1);
        } else {
            generateFood();
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    private void generateFood() {
        Random random = new Random();
        int x = random.nextInt(NUM_COLS);
        int y = random.nextInt(NUM_ROWS);
        food = new Point(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SnakeGame game = new SnakeGame();
                game.setVisible(true);
            }
        });
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
