import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class EndingPanel extends JPanel{
    private Frame frame;
    private Image backgroundImage; 

    public EndingPanel(Frame frame) {
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

        JLabel label = new JLabel("THE END");
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setBounds(600,800,400,0);

        add(label);
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

}
