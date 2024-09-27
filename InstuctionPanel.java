import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class InstuctionPanel extends JPanel implements ActionListener{
    
    private Frame frame;
    private Image backgroundImage;   

    // constructor 
    public InstuctionPanel(Frame frame){

        // panel setup
        this.frame = frame;
        setSize(1280,720);
        setLayout(null);

        //reading file
        try {
            backgroundImage = ImageIO.read(new File("E:/Codes/Java/Space Invader/Image/background2.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("potatos");
        }

        // Panel Component
        JLabel heading = new JLabel();
        heading.setText("How To Play");
        heading.setForeground(Color.white);
        heading.setFont(new Font("Arial", Font.PLAIN, 30));
        heading.setBounds(20,20,400,40);

        JLabel p1 = new JLabel();
        p1.setText("Destroy waves of alien invaders while avoiding their fire!");
        p1.setForeground(Color.white);
        p1.setBounds(20,70,600,20);
        p1.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel subHeading1 = new JLabel();
        subHeading1.setText("Instruction");
        subHeading1.setForeground(Color.white);
        subHeading1.setFont(new Font("Arial", Font.PLAIN, 30));
        subHeading1.setBounds(20,120,400,40);

        JTextArea text = new JTextArea("1. Start the Game: Once the game begins, you'll control a ship at the bottom of the screen.\r\n\n" + //
                "2. Shoot the Aliens: Aliens move from side to side and gradually descend. Shoot them before they get too low.\r\n\n" + //
                "3. Avoid Enemy Fire: Watch out for bullets from the aliens. Move your ship out of the way or take cover behind barriers (if available).\r\n\n" + //
                        "" + //
                "4. Clear Each Wave: Once you destroy all the aliens on-screen, a new wave will appear. Each wave will be faster and more challenging.\r\n\n" + //
                "5. Bonus Points: Occasionally, a special UFO flies across the top of the screen. Shoot it for bonus points.\r\n\n" + //
                "6. Stay Focused: The game gets faster over time, and the aliens will move more quickly as fewer remain.");

        text.setWrapStyleWord(true); 
        text.setLineWrap(true); 
        text.setEditable(false);
        text.setBounds(20,160,800,380);
        text.setOpaque(false);
        text.setForeground(Color.white);
        text.setFont(new Font("Arial", Font.PLAIN, 20));

        // buttons
        JButton homeButton = new JButton("Home");
        homeButton.setForeground(Color.white);
        homeButton.setBounds(20,600, 150,40);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.setFont(new Font("Arial", Font.PLAIN, 40));
        homeButton.addActionListener(this);

        JLabel credit = new JLabel("Created by: Tony Lee");
        credit.setBounds(400,600,450,40);
        credit.setForeground(Color.white);
        credit.setFont(new Font("Arial", Font.PLAIN, 40));

        add(heading);
        add(subHeading1);
        add(p1);
        add(text);
        add(homeButton);
        add(credit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0,1280,720, this);
        } else {
            System.out.println("Image not found or failed to load.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Home")){
            frame.showHomePanel();
        }
    }
}
