package src.entity;

import src.main.GamePanel;


public class NPC extends Entity{

  public NPC(GamePanel gp) {
    super(gp);
    direction = "down";
    speed = 2;

    getImage();
  }

public void getImage() {

    stance = setUp("/res/characters/final_boss/final_boss_stance01");
    up1 = setUp("/res/characters/final_boss/final_boss_stance01");
    up2 = setUp("/res/characters/final_boss/final_boss_stance01");
    down1 = setUp("/res/characters/final_boss/final_boss_stance01");
    down2 = setUp("/res/characters/final_boss/final_boss_stance01");    
    left1 = setUp("/res/characters/final_boss/final_boss_stance01");
    left2 = setUp("/res/characters/final_boss/final_boss_stance01");
    right1 = setUp("/res/characters/final_boss/final_boss_stance01");
    right2 = setUp("/res/characters/final_boss/final_boss_stance01");
  }

  
  
}
