package src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

  GamePanel gp;
  Graphics2D g2;
  Font aerial_40, aerial_80b;
  public boolean messageOn = false;
  public String message = "";
  int messageCounter = 0;
  public boolean gameFinished = false;
  public int commandNum = 0;

  public UI(GamePanel gp){
  
    this.gp = gp;
    aerial_40 = new Font("Arial" , Font.PLAIN, 40);
    aerial_80b = new Font("Arial" , Font.BOLD, 80);
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

      // playState stuff later
    }

    // PAUSE STATE
    if(gp.gameState == gp.pauseState){

      drawPauseScreen();
    }
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
