package src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import src.object.OBJ_Health;
import src.object.SuperObject;

public class UI {

  GamePanel gp;
  Graphics2D g2;
  Font aerial_40, aerial_80b;
  BufferedImage health_100, health_75, health_50, health_25, health_00;
  public boolean messageOn = false;
  public String message = "";
  int messageCounter = 0;
  public boolean gameFinished = false;
  public int commandNum = 0;

  public UI(GamePanel gp){
  
    this.gp = gp;
    aerial_40 = new Font("Arial" , Font.PLAIN, 40);
    aerial_80b = new Font("Arial" , Font.BOLD, 80);

    // CREATE HUD OBJECT
    SuperObject health = new OBJ_Health(gp);
    health_100 = health.image1;
    health_75 = health.image2;
    health_50 = health.image3;
    health_25 = health.image4;
    health_00 = health.image5;
  }

  public void showMessage(String text){

    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2){

    this.g2 = g2;
    g2.setFont(aerial_40);
    g2.setColor(Color.white);


    // TITLE STATE
    if(gp.gameState == gp.titleState){

      drawTitleScreen(); 
    }

    // PLAY STATE
    if(gp.gameState == gp.playState){

      drawPlayerLife();
    }

    // PAUSE STATE
    if(gp.gameState == gp.pauseState){

      drawPlayerLife();
      drawPauseScreen();
    }

    // DIALOGUE STATE
    // if(gp.gameState == gp.pauseState){
    // drawPlayerLife();
    // drawDialogueScreen();
    //}
  }

  public void drawPlayerLife(){

    int x = (gp.screenWidth - gp.tileSize*5) / 2;
    int y = gp.maxScreenRow/2;

    BufferedImage healthImage;

    // Select the appropriate health image based on player's health percentage
    if (gp.player.life > 75) {
        healthImage = health_100;
    } else if (gp.player.life > 50) {
        healthImage = health_75;
    } else if (gp.player.life > 25) {
        healthImage = health_50;
    } else if (gp.player.life > 0) {
        healthImage = health_25;
    } else {
        healthImage = health_00;
    }

    g2.drawImage(healthImage, x, y, null);

  }

  public void drawTitleScreen(){

    g2.setColor(new Color(70, 120,80));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    // TITLE NAME
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
    String text = "Adventure Rush";
    
    int x = getXforCenteredText(text);
    int y = gp.tileSize * 3;

    // SHADOW
    g2.setColor(Color.BLACK);
    g2.drawString(text, x + 5, y + 5);

    // MAIN COLOR
    g2.setColor(Color.white);
    g2.drawString(text, x, y);

    // COVER PHOTO
    x = gp.screenWidth/2 + gp.tileSize * 2;
    y += gp.tileSize * 2;
    g2.drawImage(gp.player.stance, x, y, gp.tileSize * 6 , gp.tileSize * 6, gp);

    // MENU
    g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
    text = "NEW GAME";
    x = getXforCenteredText(text) - gp.tileSize * 4;
    y += gp.tileSize * 2;
    g2.drawString(text, x, y);
    if(commandNum == 0){
      g2.drawString(">", x - gp.tileSize , y);
    }

    text = "LOAD GAME";
    x = getXforCenteredText(text) - gp.tileSize * 4;
    y += gp.tileSize * 2;
    g2.drawString(text, x, y);
    if(commandNum == 1){
      g2.drawString(">", x - gp.tileSize , y);
    }

    text = "QUIT";
    x = getXforCenteredText(text) - gp.tileSize * 4;
    y += gp.tileSize * 2;
    g2.drawString(text, x, y);
    if(commandNum == 2){
      g2.drawString(">", x - gp.tileSize , y);
    }

  }

  public void drawPauseScreen(){

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
    String text = "PAUSED";
    int x = getXforCenteredText (text);
    int y = gp.screenHeight/2;

    g2.drawString(text, x, y);
  }


  public int getXforCenteredText (String text){

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    
    return x;
  }
  
}
