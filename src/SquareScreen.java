import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class SquareScreen extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int SQUARE_SIZE = 100;
    private static final int MIN_SIZE = 50;
    private static final int MAX_SIZE = 200;
    private static final int INITIAL_SIZE = SQUARE_SIZE;

    private int squareX;
    private int squareY;
    private int squareSize;

    private JPanel squarePanel;
    private JSlider sizeSlider;

    public SquareScreen() {
        setTitle("Square Screen");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        squarePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(squareX, squareY, squareSize, squareSize);
            }
        };

        squareX = (FRAME_WIDTH - SQUARE_SIZE) / 2;
        squareY = (FRAME_HEIGHT - SQUARE_SIZE) / 2;
        squareSize = SQUARE_SIZE;

        add(squarePanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();

        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareX -= 10;
                squarePanel.repaint();
            }
        });

        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareX += 10;
                squarePanel.repaint();
            }
        });

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareY -= 10;
                squarePanel.repaint();
            }
        });

        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareY += 10;
                squarePanel.repaint();
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
                squareSize = sizeSlider.getValue();
                squarePanel.repaint();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                squareX = (FRAME_WIDTH - SQUARE_SIZE) / 2;
                squareY = (FRAME_HEIGHT - SQUARE_SIZE) / 2;
                squareSize = SQUARE_SIZE;
                sizeSlider.setValue(INITIAL_SIZE);
                squarePanel.repaint();
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
