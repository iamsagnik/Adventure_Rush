package src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

  GamePanel gp;
  public boolean upPressed, downPressed, leftPressed, rightPressed , jumpPressed;
  boolean showDebugValues = false;

  public KeyHandler(GamePanel gp){
    this.gp = gp;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

    int code = e.getKeyCode();

    // TITLE STATE
    if(gp.gameState == gp.titleState){
      if(code == KeyEvent.VK_UP){
        gp.ui.commandNum--;
        if(gp.ui.commandNum < 0)
          gp.ui.commandNum = 2;
      }
      if(code == KeyEvent.VK_DOWN){
        gp.ui.commandNum++;
        if(gp.ui.commandNum > 2)
          gp.ui.commandNum = 0;
      }
      if(code == KeyEvent.VK_ENTER){
        if(gp.ui.commandNum == 0){
          gp.gameState = gp.playState;
          //gp.playMusic(0);
        }
        if(gp.ui.commandNum == 1){
            // add later
        }
        if(gp.ui.commandNum == 2){
            System.exit(0);
        }
      }
    }

    
    if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
      upPressed = true;
      System.out.println("up key is pressed");
    }
    if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
      downPressed = true;
      System.out.println("down key is pressed");
    }
    if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
      leftPressed = true;
      System.out.println("left key is pressed");
    }
    if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
      rightPressed = true;
      System.out.println("right key is pressed");
    }
    if(code == KeyEvent.VK_P){
      if(gp.gameState == gp.playState){
        gp.gameState = gp.pauseState;
      }
      else{
        gp.gameState = gp.playState;
      }
      System.out.println("pause button is pressed");
    }
    if(code == KeyEvent.VK_R){

      switch (gp.currentMap) {
        case 0: gp.tileM.loadMap("/res/maps/layer01.txt",0); break;
      }
    
    }
    if(code == KeyEvent.VK_M){
      if(gp.map.miniMapOn == false){
        gp.map.miniMapOn = true;
      }
      else{
        gp.map.miniMapOn = false;
      }
    }

    // DEBUG
    if(code == KeyEvent.VK_T){
      if(showDebugValues == false){
        showDebugValues = true;
      }else{
        showDebugValues = false;
      }
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {

    int code = e.getKeyCode();
    
    if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
      upPressed = false;
    }
    if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
      downPressed = false;
    }
    if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
      leftPressed = false;
    }
    if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
      rightPressed = false;
    }   

  }
  
}
