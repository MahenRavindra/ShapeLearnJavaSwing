import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShapeMatchingGame extends JFrame {

    private JLabel shapeLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel resultLabel;

    private Shape[] shapes;
    private int currentShapeIndex;

    public ShapeMatchingGame() {
        setTitle("Shape Matching Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        shapeLabel = new JLabel();
        shapeLabel.setPreferredSize(new Dimension(200, 200));
        add(shapeLabel);

        answerField = new JTextField(20);
        add(answerField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        add(submitButton);

        resultLabel = new JLabel();
        add(resultLabel);

        shapes = createShapes();
        currentShapeIndex = 0;
        displayShape();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Shape[] createShapes() {
        Shape[] shapes = new Shape[3];

        // Create shapes with their names or descriptions
        Shape triangle = new Shape("Triangle", new ImageIcon("triangle.png"));
        Shape square = new Shape("Square", new ImageIcon("square.png"));
        Shape circle = new Shape("Circle", new ImageIcon("circle.png"));

        shapes[0] = triangle;
        shapes[1] = square;
        shapes[2] = circle;

        return shapes;
    }

    private void displayShape() {
        Shape currentShape = shapes[currentShapeIndex];
        shapeLabel.setIcon(currentShape.getIcon());
        answerField.setText("");
        resultLabel.setText("");
    }

    private void checkAnswer() {
        String playerAnswer = answerField.getText().trim().toLowerCase();
        Shape currentShape = shapes[currentShapeIndex];
        String correctAnswer = currentShape.getName().toLowerCase();

        if (playerAnswer.equals(correctAnswer)) {
            resultLabel.setText("Correct!");
        } else {
            resultLabel.setText("Incorrect!");
        }

        currentShapeIndex++;
        if (currentShapeIndex < shapes.length) {
            displayShape();
        } else {
            resultLabel.setText("Game Over!");
            answerField.setEditable(false);
            submitButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapeMatchingGame();
            }
        });
    }
}

class Shape {
    private String name;
    private ImageIcon icon;

    public Shape(String name, ImageIcon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
