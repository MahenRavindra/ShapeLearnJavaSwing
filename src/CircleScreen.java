import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleScreen extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int CIRCLE_SIZE = 100;

    private int circleX;
    private int circleY;
    private int circleSize;

    private JPanel circlePanel;

    public CircleScreen() {
        setTitle("Circle App");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        circlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillOval(circleX, circleY, circleSize, circleSize);
            }
        };

        circleX = (FRAME_WIDTH - CIRCLE_SIZE) / 2;
        circleY = (FRAME_HEIGHT - CIRCLE_SIZE) / 2;
        circleSize = CIRCLE_SIZE;

        add(circlePanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();

        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleX -= 10;
                circlePanel.repaint();
            }
        });

        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleX += 10;
                circlePanel.repaint();
            }
        });

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleY -= 10;
                circlePanel.repaint();
            }
        });

        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleY += 10;
                circlePanel.repaint();
            }
        });

        JButton increaseSizeButton = new JButton("Increase Size");
        increaseSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleSize += 10;
                circlePanel.repaint();
            }
        });

        JButton decreaseSizeButton = new JButton("Decrease Size");
        decreaseSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleSize -= 10;
                if (circleSize < 0) {
                    circleSize = 0;
                }
                circlePanel.repaint();
            }
        });

        JButton backButton = new JButton("Back button");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        controlPanel.add(moveLeftButton);
        controlPanel.add(moveRightButton);
        controlPanel.add(moveUpButton);
        controlPanel.add(moveDownButton);
        controlPanel.add(increaseSizeButton);
        controlPanel.add(decreaseSizeButton);
        controlPanel.add(backButton);

        return controlPanel;
    }
}
