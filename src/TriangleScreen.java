import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class TriangleScreen extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int TRIANGLE_SIZE = 100;
    private static final int MIN_SIZE = 50;
    private static final int MAX_SIZE = 200;
    private static final int INITIAL_SIZE = TRIANGLE_SIZE;

    private int triangleX;
    private int triangleY;
    private int triangleSize;

    private JPanel trianglePanel;
    private JSlider sizeSlider;

    public TriangleScreen() {
        setTitle("Triangle Screen");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        trianglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                int[] xPoints = {triangleX, triangleX + (triangleSize / 2), triangleX - (triangleSize / 2)};
                int[] yPoints = {triangleY, triangleY + triangleSize, triangleY + triangleSize};
                g.fillPolygon(xPoints, yPoints, 3);
            }
        };

        triangleX = (FRAME_WIDTH - TRIANGLE_SIZE) / 2;
        triangleY = (FRAME_HEIGHT - TRIANGLE_SIZE) / 2;
        triangleSize = TRIANGLE_SIZE;

        add(trianglePanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();

        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangleX -= 10;
                trianglePanel.repaint();
            }
        });

        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangleX += 10;
                trianglePanel.repaint();
            }
        });

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangleY -= 10;
                trianglePanel.repaint();
            }
        });

        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangleY += 10;
                trianglePanel.repaint();
            }
        });

        sizeSlider = new JSlider(JSlider.HORIZONTAL, MIN_SIZE, MAX_SIZE, INITIAL_SIZE);
        sizeSlider.setMajorTickSpacing(25);
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                triangleSize = sizeSlider.getValue();
                trianglePanel.repaint();
            }
        });

        JButton resetButton = new JButton("Reset");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangleX = (FRAME_WIDTH - TRIANGLE_SIZE) / 2;
                triangleY = (FRAME_HEIGHT - TRIANGLE_SIZE) / 2;
                triangleSize = TRIANGLE_SIZE;
                sizeSlider.setValue(INITIAL_SIZE);
                trianglePanel.repaint();
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
        controlPanel.add(sizeSlider);
        controlPanel.add(resetButton);
        controlPanel.add(backButton);

        return controlPanel;
    }

}
