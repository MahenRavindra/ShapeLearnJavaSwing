import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class PolygonScreen extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int POLYGON_SIZE = 100;
    private static final int MIN_SIZE = 50;
    private static final int MAX_SIZE = 200;
    private static final int INITIAL_SIZE = POLYGON_SIZE;

    private int polygonX;
    private int polygonY;
    private int polygonSize;

    private JPanel polygonPanel;
    private JSlider sizeSlider;

    public PolygonScreen() {
        setTitle("Polygon Screen");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        polygonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                int[] xPoints = {polygonX, polygonX + (polygonSize / 2), polygonX + (3 * polygonSize / 4),
                        polygonX + (polygonSize / 4), polygonX - (polygonSize / 4),
                        polygonX - (3 * polygonSize / 4)};
                int[] yPoints = {polygonY, polygonY + (polygonSize / 2), polygonY + polygonSize,
                        polygonY + polygonSize, polygonY + polygonSize,
                        polygonY + (polygonSize / 2)};
                g.fillPolygon(xPoints, yPoints, 6);
            }
        };

        polygonX = (FRAME_WIDTH - POLYGON_SIZE) / 2;
        polygonY = (FRAME_HEIGHT - POLYGON_SIZE) / 2;
        polygonSize = POLYGON_SIZE;

        add(polygonPanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();

        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polygonX -= 10;
                polygonPanel.repaint();
            }
        });

        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polygonX += 10;
                polygonPanel.repaint();
            }
        });

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polygonY -= 10;
                polygonPanel.repaint();
            }
        });

        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polygonY += 10;
                polygonPanel.repaint();
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
                polygonSize = sizeSlider.getValue();
                polygonPanel.repaint();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polygonX = (FRAME_WIDTH - POLYGON_SIZE) / 2;
                polygonY = (FRAME_HEIGHT - POLYGON_SIZE) / 2;
                polygonSize = POLYGON_SIZE;
                sizeSlider.setValue(INITIAL_SIZE);
                polygonPanel.repaint();
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
