import javax.swing.JFrame;
import java.awt.BorderLayout;
public class Frame extends JFrame{

    HomePanel home;
    InstuctionPanel credit;
    GamePanel game;
    EndingPanel ending;
    public Frame(){
        setTitle("Space Invader");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        home = new HomePanel(this);
        add(home,BorderLayout.CENTER);

        credit = new InstuctionPanel(this);
        game = new GamePanel(this);
        ending = new EndingPanel(this);
    }

    public void showFrame(){
        setVisible(true);
    }

    public void showCreditPanel(){
        getContentPane().removeAll(); 
        add(credit, BorderLayout.CENTER); 
        revalidate(); 
        repaint();   
    }

    public void showHomePanel(){
        getContentPane().removeAll(); 
        add(home, BorderLayout.CENTER); 
        revalidate(); 
        repaint();   
    }

    public void showGamePanel(){
        getContentPane().removeAll();
        add(game, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void showEndingPanel(){
        getContentPane().removeAll();
        game = null;
        System.gc();
        add(ending, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}   