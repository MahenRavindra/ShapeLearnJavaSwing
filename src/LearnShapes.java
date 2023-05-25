import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LearnShapes extends JPanel {
    private static final int WIDTH = 1420;
    private static final int HEIGHT = 1000;

    private Image backgroundImage;


    private List<String> shapeNames;
    private List<Shape> shapes;


















    public LearnShapes() {
        shapeNames = new ArrayList<>();
        shapes = new ArrayList<>();

        JButton backButton = new JButton("Back button");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Home h = new Home();
                h.setVisible(true);
            }
        });

        backgroundImage = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/shapebkgrund.jpg").getImage();
        add(backButton, BorderLayout.SOUTH);



//        shapeNames.add("Circle");
//        shapeNames.add("Square");
//        shapeNames.add("Rectangle");
//        shapeNames.add("Polygon");
//
//        shapes.add(new Circle(100, 100, 50));
//        shapes.add(new Square(100, 200, 100));
//        shapes.add(new Rectangle(100, 350, 150, 80));
//        shapes.add(new PolygonShape(new int[]{350, 400, 450}, new int[]{400, 300, 400}, 3));

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);

        int x = 20;
        int y = 50;

        for (int i = 0; i < shapes.size(); i++) {
            String shapeName = shapeNames.get(i);
            Shape shape = shapes.get(i);

            g.setColor(Color.BLUE);
            shape.draw(g);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(shapeName, x, y);

            y += 150;
        }
    }

    abstract class Shape {
        protected int x;
        protected int y;

        public Shape(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public abstract void draw(Graphics g);
    }

    class Circle extends Shape {
        private int radius;

        public Circle(int x, int y, int radius) {
            super(x, y);
            this.radius = radius;
        }

        @Override
        public void draw(Graphics g) {
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }

    class Square extends Shape {
        private int side;

        public Square(int x, int y, int side) {
            super(x, y);
            this.side = side;
        }

        @Override
        public void draw(Graphics g) {
            g.fillRect(x - side / 2, y - side / 2, side, side);
        }
    }

    class Rectangle extends Shape {
        private int width;
        private int height;

        public Rectangle(int x, int y, int width, int height) {
            super(x, y);
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw(Graphics g) {
            g.fillRect(x - width / 2, y - height / 2, width, height);

        }
    }

    class PolygonShape extends Shape {
        private int[] xPoints;
        private int[] yPoints;
        private int nPoints;

        public PolygonShape(int[] xPoints, int[] yPoints, int nPoints) {
            super(xPoints[0], yPoints[0]);
            this.xPoints = xPoints;
            this.yPoints = yPoints;
            this.nPoints = nPoints;
        }

        @Override
        public void draw(Graphics g) {
            g.fillPolygon(new Polygon(xPoints, yPoints, nPoints));
        }
    }

}
