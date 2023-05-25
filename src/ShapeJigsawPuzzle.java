import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;
import java.awt.datatransfer.DataFlavor;



public class ShapeJigsawPuzzle extends JFrame {

    private JPanel puzzlePanel;
    private JLabel statusLabel;
    private JButton resetButton;

    public ShapeJigsawPuzzle() {
        setTitle("Shape Jigsaw Puzzle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        puzzlePanel = new JPanel(new GridLayout(3, 3));
        statusLabel = new JLabel("Drag and drop the shapes to complete the puzzle!");
        resetButton = new JButton("Reset");

        initializePuzzle();

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetPuzzle();
            }
        });

        setLayout(new BorderLayout());
        add(puzzlePanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(resetButton, BorderLayout.SOUTH);
    }

    private void initializePuzzle() {
        puzzlePanel.removeAll();

        ShapeComponent[] shapeComponents = new ShapeComponent[9];

        for (int i = 0; i < 9; i++) {
            ShapeComponent shape = new ShapeComponent(i);
            shape.setDropTarget(new ShapeDropTarget(shape, shapeComponents));
            puzzlePanel.add(shape);
            shapeComponents[i] = shape;
        }

        pack();
    }

    private void resetPuzzle() {
        initializePuzzle();
        statusLabel.setText("Drag and drop the shapes to complete the puzzle!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ShapeJigsawPuzzle puzzle = new ShapeJigsawPuzzle();
                puzzle.setVisible(true);
            }
        });
    }
}

class ShapeComponent extends JLabel {

    private int shapeId;

    public ShapeComponent(int shapeId) {
        this.shapeId = shapeId;
        setPreferredSize(new Dimension(100, 100));
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setHorizontalAlignment(CENTER);

        DragHandler dragHandler = new DragHandler();
        addMouseListener(dragHandler);
        addMouseMotionListener(dragHandler);
    }

    public int getShapeId() {
        return shapeId;
    }

    private class DragHandler extends MouseAdapter {

        private int dragOffsetX;
        private int dragOffsetY;

        public void mousePressed(MouseEvent e) {
            dragOffsetX = e.getX();
            dragOffsetY = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            int x = e.getX() - dragOffsetX + getLocation().x;
            int y = e.getY() - dragOffsetY + getLocation().y;
            setLocation(x, y);
        }

        public void mouseReleased(MouseEvent e) {
            ShapeComponent targetShape = (ShapeComponent) e.getComponent();
            int targetShapeId = targetShape.getShapeId();

            if (targetShape.contains(e.getPoint())) {
                ShapeComponent draggedShape = ShapeComponent.this;
                int draggedShapeId = draggedShape.getShapeId();

                if (targetShapeId == draggedShapeId) {
                    targetShape.setBackground(Color.GREEN);
                    targetShape.setText("Shape " + targetShapeId + " matched!");
                } else {
                    targetShape.setBackground(Color.RED);
                    targetShape.setText("Incorrect match!");
                }
            }

            if (getParent() instanceof JPanel) {
                JPanel parent = (JPanel) getParent();
                parent.repaint();
            }
        }
    }
}

class ShapeDropTarget extends DropTarget {

    private ShapeComponent targetShape;
    private ShapeComponent[] shapeComponents;
    private Point initialPosition;

    public ShapeDropTarget(ShapeComponent targetShape, ShapeComponent[] shapeComponents) {
        this.targetShape = targetShape;
        this.shapeComponents = shapeComponents;
        this.initialPosition = targetShape.getLocation();
    }


    public void drop(DropTargetDropEvent event) {
        try {
            event.acceptDrop(DnDConstants.ACTION_MOVE);
            ShapeComponent draggedShape = (ShapeComponent) event.getTransferable().getTransferData(
                    new DataFlavor(ShapeComponent.class, "ShapeComponent"));

            if (targetShape != draggedShape) {
                Point targetShapePosition = targetShape.getLocation();
                targetShape.setLocation(draggedShape.getLocation());
                draggedShape.setLocation(targetShapePosition);
            }

            event.dropComplete(true);
        } catch (Exception e) {
            event.rejectDrop();
        }
    }
}
