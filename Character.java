import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Character {
    private int pos_x; 
    private int pos_y;
    private int hp;
    private Image character;
    private int height;
    private int width;
    private int speed; 
    private ArrayList<Projectile> projectiles; 
    private final int maxProjectiles; 
    private boolean isMovingRight;
    private boolean isMovingLeft;
    private boolean canShoot;

    public Character(Score score) {
        this.hp = 3;
        this.pos_x = 10;
        this.pos_y = 600;
        this.width = 60;
        this.height = 60;
        this.speed = 5;
        this.canShoot = true;
        this.maxProjectiles = 3;
        try {
            this.character = ImageIO.read(new File("Image/blue.png"));
        } catch (Exception e) {
            System.out.println("Failed to load player image");
        }
        projectiles = new ArrayList<>();
    }

    public Character(int x, int y, Image image){
        this.hp = 1;
        this.pos_x = x;
        this.pos_y = y;
        this.width = 60;
        this.height = 60;
        this.speed = 5;
        this.canShoot = true;
        this.maxProjectiles = 3;
        this.character = image;
        projectiles = new ArrayList<>();
    }

    // Check if character is inbound
    public boolean inbound(int x){
        return x > 0 && x < 1220; // Assuming 1280 width minus character width (60)
    }

    // Create projectile
    public void shootProjectile() {
        if (projectiles.size() < maxProjectiles && this.canShoot) { 
            Projectile newProjectile = new Projectile(this.pos_x, this.pos_y); 
            projectiles.add(newProjectile); 
        } else {
            System.out.println("Max projectiles reached!");
        }
    }

    // Check activiness of projectile
    public void isActive(){
        projectiles.removeIf(p -> !p.inbound(p.getPosY()));
    }

    // Check projectile collision
    public void playerProjectileCollision(ArrayList<Character> enemies, Score score) {
        projectiles.removeIf(p -> p.playerProjectileCollision(enemies, score));
    }

    // Getter methods
    public int getPosX() {
        return this.pos_x;
    }

    public int getPosY() {
        return this.pos_y;
    }

    public Image getCharacter() {
        return this.character;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getspeed() {
        return this.speed;
    }

    public boolean getIsMovingRight() {
        return isMovingRight;
    }

    public boolean getIsMovingLeft() {
        return isMovingLeft;
    }

    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public boolean getCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setPosX(int x) {
        this.pos_x = x;
    }

    public void setPosY(int y) {
        this.pos_y = y;
    }

    public void setPlayerPosX(int x){
        if (inbound(x)){
            this.pos_x = x;
        }
    }

    public ArrayList<Projectile> getProjectile() {
        return projectiles;
    }
}
