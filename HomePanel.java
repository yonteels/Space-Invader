import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class HomePanel extends JPanel implements ActionListener  {
    
    private Image backgroundImage;  
    private Frame frame; 
    public HomePanel(Frame frame) {
        // making a reference to frame
        this.frame = frame; 

        //reading file
        try {
            backgroundImage = ImageIO.read(new File("Image/background.png"));  
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Panel Setting
        setSize(1280, 720);
        setLayout(null);

        // Panel Component
        JLabel title = new JLabel();
        title.setForeground(Color.white);

        // buttons
        JButton startButton = new JButton("Start");
        startButton.setForeground(Color.white);
        startButton.setBounds(450,360, 150,40);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFont(new Font("Arial", Font.PLAIN, 40));
        startButton.addActionListener(this);

        JButton instructionButton = new JButton("Instruction");
        instructionButton.setForeground(Color.white);   
        instructionButton.setBounds(650,360, 300,40);
        instructionButton.setOpaque(false);
        instructionButton.setContentAreaFilled(false);
        instructionButton.setBorderPainted(false);
        instructionButton.setFont(new Font("Arial", Font.PLAIN, 40));
        instructionButton.addActionListener(this);

        //Adding component
        add(title);
        add(startButton);
        add(instructionButton);
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
        if (e.getActionCommand().equals("Start")){
            frame.showGamePanel();
        }
        else if(e.getActionCommand().equals("Instruction")){ 
            frame.showCreditPanel();
        }
        else{
            System.out.println("other");
        }
    }
}
