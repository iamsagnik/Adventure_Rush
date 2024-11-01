package src.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import src.main.GamePanel;
import src.main.ImageRenderer;

public class Entity {
  
  GamePanel gp;
  public int worldX, worldY;
  public int speed;
  public BufferedImage stance, up1, up2, down1, down2, left1, left2, right1, right2;     // BufferedImage : It describes an image with an accessible buffer of image data (we use this to store our image file) 
  public String direction;
  public int spriteCounter = 0;
  public int spriteNum = 1;
  public Rectangle solidArea = new Rectangle(0,0,48,48);
  public boolean collisionOn = false;

  // CHARACTER STATUS
  public int maxLife;
  public int life;

  
  public Entity(GamePanel gp){

    this.gp = gp;
  }

  public void setAction(){}

  public void update(){}
  
  public void draw(Graphics2D g2){

    BufferedImage image = null;

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldY - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

      switch (direction) {
        case "up":
          if(spriteNum == 1){
          image = up1;
          }
          if (spriteNum == 2) {
            image = up2;
          }
          break;
        case "down":
          if(spriteNum == 1){
          image = down1;
          }
          if (spriteNum == 2) {
            image = down2;
          }
          break;
        case "left":
          if(spriteNum == 1){
          image = left1;
          }
          if (spriteNum == 2) {
            image = left2;
          }
          break;
        case "right":
          if(spriteNum == 1){
          image = right1;
          }
          if (spriteNum == 2) {
            image = right2;
          }
          break;
        default:
          image = stance;
          break;
      }

      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
    }

  }

  public BufferedImage setUp (String imagePath){

    ImageRenderer imgRend = new ImageRenderer();
    BufferedImage image = null;

    try {

      image = ImageIO.read(getClass().getResourceAsStream( imagePath +".png"));
      image = imgRend.scaleImage(image, gp.tileSize, gp.tileSize);
      
    } catch (IOException e) {
      e.printStackTrace();
    }

    return image;
  }  

   
}
