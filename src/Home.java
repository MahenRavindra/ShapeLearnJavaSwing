import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Home extends JFrame {
    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top Bar
        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(getWidth(), 50)); // Set preferred size for the top bar

        // Text in Top Bar
        JLabel titleLabel = new JLabel("SHAPE QUEST");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.orange);
        topBar.add(titleLabel);

        ImageIcon backgroundImage = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/bg_one.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        //backgroundLabel.setSize(1420, 1000);
        // Buttons Panel


        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2)); // Set GridLayout with 2 rows and 2 columns
        buttonsPanel.setPreferredSize(new Dimension(1420, 1000)); // Set preferred size for the panel


        JButton button1 = new JButton();//learn shapes
        button1.setPreferredSize(new Dimension(150, 150)); // Set preferred size for the button

        try {
            ImageIcon icon = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/learn_shapes2.png");
            button1.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();//shape display
                LearnShapes shapeDisplay = new LearnShapes();
                frame.getContentPane().add(shapeDisplay);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setSize(1420, 1000);//new
            }
        });

        JButton button2 = new JButton();//shape game
        button2.setPreferredSize(new Dimension(150, 50));
        button2.setBackground(Color.BLUE); // Set the background color
        button2.setForeground(Color.WHITE); // Set the foreground color
        button2.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font
        // Add an icon to the button
        try {
            ImageIcon icon = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/Teris_game2.png");
            button2.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TetrisGame tg = new TetrisGame();
                tg.setVisible(true);
                tg.setSize(1420, 800);
            }
        });


        JButton button3 = new JButton();//shape blast
        button3.setPreferredSize(new Dimension(150, 50)); // Set preferred size for the button

        try {
            ImageIcon icon = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/shape_blast2.png");
            button3.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Shape Blast");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                shapeBlast shapePanel = new shapeBlast();
                frame.add(shapePanel);
//                JLabel lb=new JLabel("Click the shapes");
//                lb.setVisible(false);
//                frame.add(lb);

                frame.setSize(1420, 1000);
                frame.setVisible(true);
            }
        });

        JButton button4 = new JButton();//snake game
        button4.setPreferredSize(new Dimension(150, 50)); // Set preferred size for the button

        try {
            ImageIcon icon = new ImageIcon("/Users/g.l.tharindulakshan/Downloads/HCI practical/hci/src/snake_game22.png");
            button4.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame sg = new SnakeGame();
                sg.setVisible(true);

            }
        });
//
//        JButton button5 = new JButton("JigSaw Puzzle game");
//        button5.setPreferredSize(new Dimension(150, 50)); // Set preferred size for the button
//        button5.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ShapeJigsawPuzzle sjp = new ShapeJigsawPuzzle();
//                sjp.setVisible(true);
//            }
//        });
//
//        JButton button6 = new JButton("Shape matching game");
//        button6.setPreferredSize(new Dimension(150, 50)); // Set preferred size for the button
//        button6.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ShapeMatchingGame smg = new ShapeMatchingGame();
//                smg.setVisible(true);
//            }
//        });

        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(button4);
//        buttonsPanel.add(button5);
//        buttonsPanel.add(button6);

        // Add top bar and buttons panel to the frame
        add(topBar, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);



        pack();
        setLocationRelativeTo(null);
    }
}
