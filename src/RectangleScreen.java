import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class RectangleScreen extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 700;
    private static final int RECTANGLE_WIDTH = 100;
    private static final int RECTANGLE_HEIGHT = 100;
    private static final int MIN_SIZE = 50;
    private static final int MAX_SIZE = 200;
    private static final int INITIAL_SIZE = RECTANGLE_WIDTH;

    private int rectangleX;
    private int rectangleY;
    private int rectangleWidth;
    private int rectangleHeight;

    private JPanel rectanglePanel;
    private JSlider sizeSlider;

    public RectangleScreen() {
        setTitle("Rectangle Screen");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        rectanglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.YELLOW);
                g.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
            }
        };

        rectangleX = (FRAME_WIDTH - RECTANGLE_WIDTH) / 2;
        rectangleY = (FRAME_HEIGHT - RECTANGLE_HEIGHT) / 2;
        rectangleWidth = RECTANGLE_WIDTH;
        rectangleHeight = RECTANGLE_HEIGHT;

        add(rectanglePanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();




        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangleX -= 10;
                rectanglePanel.repaint();
            }
        });

        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangleX += 10;
                rectanglePanel.repaint();
            }
        });

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangleY -= 10;
                rectanglePanel.repaint();
            }
        });

        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangleY += 10;
                rectanglePanel.repaint();
            }
        });

        JButton backButton = new JButton("Back button");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
                rectangleWidth = sizeSlider.getValue();
                rectangleHeight = sizeSlider.getValue();
                rectanglePanel.repaint();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setLocation(10, 10);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangleX = (FRAME_WIDTH - RECTANGLE_WIDTH) / 2;
                rectangleY = (FRAME_HEIGHT - RECTANGLE_HEIGHT) / 2;
                rectangleWidth = RECTANGLE_WIDTH;
                rectangleHeight = RECTANGLE_HEIGHT;
                sizeSlider.setValue(INITIAL_SIZE);
                rectanglePanel.repaint();
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
