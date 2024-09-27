import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    private Frame frame;
    private Image backgroundImage;
    private Character player;
    private boolean running;
    private ArrayList<Character> enemies;
    private Score score;
    private JLabel scoreLabel;
    private Image[] enemyRacialSegregationCenter;
    private boolean movingRight = true;  // New flag to track enemy movement direction

    GamePanel(Frame frame) {

        enemyRacialSegregationCenter = new Image[4];

        // Reading file
        try {
            backgroundImage = ImageIO.read(new File("E:/Codes/Java/Space Invader/Image/background3.png"));
            enemyRacialSegregationCenter[0] = ImageIO.read(new File("E:/Codes/Java/Space Invader/Image/blue.png"));
            enemyRacialSegregationCenter[1] = ImageIO.read(new File("E:/Codes/Java/Space Invader/Image/green_enemy.png"));
            enemyRacialSegregationCenter[2] = ImageIO.read(new File("E:/Codes/Java/Space Invader/Image/red_enemy.png"));
            enemyRacialSegregationCenter[3] = ImageIO.read(new File("E:/Codes/Java/Space Invader/Image/yellow_enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oh Nose Something went wrong with the image loader");
        }

        // Panel settings
        this.frame = frame;
        setSize(1280, 720);
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);

        // Game components
        player = new Character(score);
        enemies = new ArrayList<>();
        score = new Score();

        scoreLabel = new JLabel("Score: " + score.getScore());
        scoreLabel.setForeground(Color.white);
        scoreLabel.setBounds(0, 0, 400, 100);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        add(scoreLabel);

        int xSpacing = 10;
        int ySpacing = 20;
        int layer = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                enemies.add(new Character(300 + (60 + xSpacing) * j, (60 + ySpacing) * layer, enemyRacialSegregationCenter[i]));
            }
            layer++;
        }

        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1280, 720, this);
        } else {
            System.out.println("Image not found or failed to load.");
        }
        g.drawImage(player.getCharacter(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), this);

        for (Projectile p : player.getProjectile()) {
            g.drawImage(p.getProjectImage(), p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight(), this);
        }

        for (Character e : enemies) {
            g.drawImage(e.getCharacter(), e.getPosX(), e.getPosY(), e.getWidth(), e.getHeight(), this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String key = KeyEvent.getKeyText(keyCode);

        if (key.equalsIgnoreCase("D")) {
            player.setIsMovingRight(true);
        } else if (key.equalsIgnoreCase("A")) {
            player.setIsMovingLeft(true);
        } else if (key.equalsIgnoreCase("SPACE") && player.getCanShoot()) {
            player.shootProjectile();
            player.setCanShoot(false);
            System.out.println(score.getScore());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String key = KeyEvent.getKeyText(keyCode);

        if (key.equalsIgnoreCase("D")) {
            player.setIsMovingRight(false);
        } else if (key.equalsIgnoreCase("A")) {
            player.setIsMovingLeft(false);
        } else if (key.equalsIgnoreCase("SPACE")) {
            player.setCanShoot(true);
            System.out.println(enemies.size());
        }
    }

    @Override
    public void run() {
        while (running) {
            update();
            revalidate();
            repaint();

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // Move player right
        if (player.getIsMovingRight()) {
            player.setPlayerPosX(player.getPosX() + player.getspeed());
        }
        // Move player left
        if (player.getIsMovingLeft()) {
            player.setPlayerPosX(player.getPosX() - player.getspeed());
        }

        // Update player projectiles
        player.isActive();
        player.playerProjectileCollision(enemies, score);
        scoreLabel.setText("Score:" + score.getScore());

        // Remove dead enemies
        Iterator<Character> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Character e = iterator.next();
            if (e.getHp() <= 0) {
                iterator.remove();
            }
        }

        // Update projectile positions
        for (Projectile p : player.getProjectile()) {
            p.setPosY(p.getPosY() - p.getSpeed());
        }

        moveEnemiesUnison(); 

        if (enemies.size() == 0) {
            frame.showEndingPanel();
        }
    }

private void moveEnemiesUnison() {
    int moveAmount = movingRight ? 5 : -5;  // Move direction
    boolean switchDirection = false;  // Flag to check if any enemy hits the boundary

    for (Character enemy : enemies) {
        if (movingRight && enemy.getPosX() + enemy.getWidth() >= getWidth()) {
            switchDirection = true; 
            break;
        } else if (!movingRight && enemy.getPosX() <= 0) {
            switchDirection = true; 
            break;
        }
    }

    // Move enemies and switch direction if necessary
    for (Character enemy : enemies) {
        enemy.setPosX(enemy.getPosX() + moveAmount);
    }

    if (switchDirection) {
        movingRight = !movingRight;  
        for (Character enemy : enemies) {
            enemy.setPosY(enemy.getPosY() + 2);  
        }
    }
}

}
