import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class shapeBlast extends JPanel {

    private boolean circleClicked;
    private boolean triangleClicked;
    private boolean polygonClicked;
    private boolean rectangleClicked;
    private boolean squareClicked;

    private Image backgroundImage;

    public shapeBlast() {
        circleClicked = false;
        triangleClicked = false;
        polygonClicked = false;
        rectangleClicked = false;
        squareClicked = false;

//        JLabel lb=new JLabel("Click the shapes");
//        lb.setFont(new Font("Arial", Font.BOLD, 30));
//        lb.setForeground(Color.orange);
//        frame.add(lb);



        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Home h = new Home();
                h.setVisible(true);
            }
        });
        backgroundImage = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/orange.jpeg").getImage();

        add(backButton,BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                circleClicked = isPointInsideCircle(mouseX, mouseY, 50, 50, 100);
                triangleClicked = isPointInsideTriangle(mouseX, mouseY, 200, 50, 250, 150, 150, 150);
                polygonClicked = isPointInsidePolygon(mouseX, mouseY, new int[]{300, 350, 375, 325, 275},
                        new int[]{50, 75, 125, 175, 125});
                rectangleClicked = isPointInsideRectangle(mouseX, mouseY, 400, 50, 100, 75);
                squareClicked = isPointInsideRectangle(mouseX, mouseY, 525, 50, 100, 100);

                if (circleClicked){
                    //go to cicle screeN
                    CircleScreen cs = new CircleScreen();
                    cs.setVisible(true);
                }
                if (triangleClicked){
                    //go to traingle screen
                    TriangleScreen ts = new TriangleScreen();
                    ts.setVisible(true);
                }
                if (polygonClicked) {
                    //go to polygon screen
                    PolygonScreen ps = new PolygonScreen();
                    ps.setVisible(true);
                }
                if(rectangleClicked){
                    //go to rectangle screen
                    RectangleScreen rs = new RectangleScreen();
                    rs.setVisible(true);
                }
                if(squareClicked){
                    //go to square screen
                    SquareScreen ss = new SquareScreen();
                    ss.setVisible(true);
                }

                repaint();
            }
        });
    }

    private boolean isPointInsideCircle(int pointX, int pointY, int circleX, int circleY, int radius) {
        int dx = pointX - circleX;
        int dy = pointY - circleY;
        return dx * dx + dy * dy <= radius * radius;
    }

    private boolean isPointInsideTriangle(int pointX, int pointY, int x1, int y1, int x2, int y2, int x3, int y3) {
        int d1 = sign(pointX, pointY, x1, y1, x2, y2);
        int d2 = sign(pointX, pointY, x2, y2, x3, y3);
        int d3 = sign(pointX, pointY, x3, y3, x1, y1);

        boolean hasNegative = (d1 < 0) || (d2 < 0) || (d3 < 0);
        boolean hasPositive = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(hasNegative && hasPositive);
    }

    private int sign(int pointX, int pointY, int x1, int y1, int x2, int y2) {
        return (pointX - x2) * (y1 - y2) - (x1 - x2) * (pointY - y2);
    }

    private boolean isPointInsidePolygon(int pointX, int pointY, int[] xPoints, int[] yPoints) {
        Polygon polygon = new Polygon(xPoints, yPoints, xPoints.length);
        return polygon.contains(pointX, pointY);
    }

    private boolean isPointInsideRectangle(int pointX, int pointY, int rectX, int rectY, int width, int height) {
        return pointX >= rectX && pointX <= rectX + width && pointY >= rectY && pointY <= rectY + height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);


        // Draw Circle
        g.setColor(circleClicked ? Color.YELLOW : Color.RED);
        g.fillOval(50, 50, 100, 100);

        // Draw Triangle
        g.setColor(triangleClicked ? Color.YELLOW : Color.GREEN);
        int[] xPoints = {200, 250, 150};
        int[] yPoints = {50, 150, 150};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g.fillPolygon(triangle);

        // Draw Polygon
        g.setColor(polygonClicked ? Color.YELLOW : Color.BLUE);
        int[] xPoints2 = {300, 350, 375, 325, 275};
        int[] yPoints2 = {50, 75, 125, 175, 125};
        Polygon polygon = new Polygon(xPoints2, yPoints2, 5);
        g.fillPolygon(polygon);

        // Draw Rectangle
        g.setColor(rectangleClicked ? Color.YELLOW : Color.ORANGE);
        g.fillRect(450, 50, 100, 75);

        // Draw Square
        g.setColor(squareClicked ? Color.YELLOW : Color.YELLOW);
        g.fillRect(605, 50, 100, 100);

    }

}
