import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleBounce extends JPanel implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CIRCLE_RADIUS = 50;
    private static final int CIRCLE_SPEED = 5;

    private int circleX;
    private int circleY;
    private int dx;
    private int dy;
    private boolean isBouncing;

    public CircleBounce() {
        circleX = WIDTH / 2 - CIRCLE_RADIUS;
        circleY = HEIGHT / 2 - CIRCLE_RADIUS;
        dx = CIRCLE_SPEED;
        dy = CIRCLE_SPEED;
        isBouncing = false;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(circleX, circleY, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
    }

    public void updateCirclePosition() {
        if (isBouncing) {
            circleX += dx;
            circleY += dy;

            if (circleX <= 0 || circleX + CIRCLE_RADIUS * 2 >= WIDTH) {
                dx *= -1;
            }

            if (circleY <= 0 || circleY + CIRCLE_RADIUS * 2 >= HEIGHT) {
                dy *= -1;
            }
        }
    }

    public void startBouncing() {
        isBouncing = true;
    }

    public void stopBouncing() {
        isBouncing = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (isBouncing) {
                stopBouncing();
            } else {
                startBouncing();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
