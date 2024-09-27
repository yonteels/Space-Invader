import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Projectile {

    private int pos_x;
    private int pos_y;
    private int width = 30;
    private int height =30;
    private int speed =10; 
    private Image projectilImage;
    
    //constructor 
    public Projectile(){
        this.pos_x = 0;
    }

    public Projectile(int pos_x, int pos_y){
        this.pos_x = pos_x;
        this.pos_y = pos_y;

        try{
            this.projectilImage = ImageIO.read(new File("Image/projectile_1.png"));
        }
        catch(Exception e){
            System.out.println("kevin is a wasted yute");
        }
    }   


    //inBound function
    public boolean inbound(int y){
        if(y > 0 && y< 720){
            return true;
        }
        else return false;
    }

public boolean playerProjectileCollision(ArrayList<Character> enemies, Score score) {
    for (Character e : enemies) {
        if (this.getPosX() >= e.getPosX() && this.getPosX() <= e.getPosX() + e.getWidth() &&
            this.getPosY() >= e.getPosY() && this.getPosY() <= e.getPosY() + e.getHeight()) {
            e.setHp(e.getHp() - 1); 
            score.setScore(score.getScore()+10);
            return true;
        }
    }
    return false;  
}

    // getter function
    public int getPosX(){
        return this.pos_x;
    }

    public Image getProjectImage(){
        return this.projectilImage;
    }

    public int getPosY(){
        return this.pos_y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
    public int getSpeed(){
        return this.speed;
    }

    // setter function
    public void setPosX(int x){
        this.pos_x = x;
    }

    public void setPosY(int y){
        this.pos_y = y;
    }
}