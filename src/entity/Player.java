package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity{

  KeyHandler keyH;

  public final int screenX;
  public final int screenY;

  public Player(GamePanel gp , KeyHandler keyH) {

    super(gp);
    this.keyH = keyH;

    // CENTER THE PLAYER ON THE SCREEN

    screenX = gp.screenWidth/2 - (gp.tileSize/2);
    screenY = gp.screenHeight/2 - (gp.tileSize/2);

    // COLLISION BOX

    solidArea = new Rectangle( 10 , 18 , 28, 28);

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues () {

    worldX = gp.tileSize * 40;
    worldY = gp.tileSize * 40;

    speed = 3;
    direction = "null";
  }

  public void getPlayerImage() {

    stance = setUp("/res/characters/player/mp_stance");
    up1 = setUp("/res/characters/player/mp_up1_walk");
    up2 = setUp("/res/characters/player/mp_up2_walk");
    down1 = setUp("/res/characters/player/mp_down1_walk");
    down2 = setUp("/res/characters/player/mp_down2_walk");    
    left1 = setUp("/res/characters/player/mp_left1_walk");
    left2 = setUp("/res/characters/player/mp_left2_walk");
    right1 = setUp("/res/characters/player/mp_right1_walk");
    right2 = setUp("/res/characters/player/mp_right2_walk");
  }

  public void update() {

    if( keyH.upPressed == false && keyH.downPressed == false && keyH.leftPressed == false && keyH.rightPressed == false ){
    direction = "null";
    }

    else if( keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ){
      if( keyH.upPressed == true){
        direction = "up";
      }
      else if( keyH.downPressed == true ){
        direction = "down";
      }
      else if( keyH.leftPressed == true){
        direction = "left";
      }
      else if( keyH.rightPressed == true){
        direction = "right";
      }

      // CHECK TILE COLLSION 
      collisionOn = false;
      gp.cChecker.checkTile(this);

      //IF COLLISION IS FALSE PLAYER MOVE
      if(collisionOn == false){

        // switch (direction) {
        //   case "up": worldY -= speed; break;
        //   case "down": worldY += speed; break;
        //   case "left": worldX -= speed; break;
        //   case "right": worldX += speed; break;
        // }

        switch (direction) {
          case "up": 
              worldY = Math.max(0, worldY - speed); 
              break;
          case "down": 
              worldY = Math.min((gp.maxWorldRow - 1) * gp.tileSize, worldY + speed); 
              break;
          case "left": 
              worldX = Math.max(0, worldX - speed); 
              break;
          case "right": 
              worldX = Math.min((gp.maxWorldCol - 1) * gp.tileSize, worldX + speed); 
              break;
      }

      }

      spriteCounter++;
      if (spriteCounter > 12) {
        if (spriteNum == 1) {
          spriteNum = 2;
        }
        else if (spriteNum == 2){
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    }
  }

  public void draw( Graphics2D g2) {

  BufferedImage image = null;

  switch (direction) {
    case "up": image = (spriteNum == 1) ? up1 : up2; break;
    case "down": image = (spriteNum == 1) ? down1 : down2; break;
    case "left": image = (spriteNum == 1) ? left1 : left2; break;
    case "right": image = (spriteNum == 1) ? right1 : right2; break;
    default: image = stance; break;
  }

  int renderX = screenX;
  int renderY = screenY;

  // Handle left edge (camera at left boundary)
  if (gp.camera.getCameraX() == 0) {
    renderX = worldX;
  }
  // Handle right edge (camera at right boundary)
  else if (gp.camera.getCameraX() >= gp.worldWidth - gp.screenWidth) {
      renderX = worldX - (gp.worldWidth - gp.screenWidth);
  }
  // Handle top edge (camera at top boundary)
  if (gp.camera.getCameraY() == 0) {
      renderY = worldY;
  }
  // Handle bottom edge (camera at bottom boundary)
  else if (gp.camera.getCameraY() >= gp.worldHeight - gp.screenHeight) {
      renderY = worldY - (gp.worldHeight - gp.screenHeight);
  }

  // Draw the player at the adjusted position
  g2.drawImage(image, renderX, renderY, gp.tileSize, gp.tileSize, null);

  }

}
