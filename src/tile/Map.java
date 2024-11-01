package src.tile;

import java.awt.image.BufferedImage;

import src.main.GamePanel;

public class Map extends TileManager{

  GamePanel gp;
  BufferedImage worldMap;
  public boolean miniMap = false;

  public Map(GamePanel gp){
    super(gp);
    this.gp = gp;
  }

  public void createWorldMap(){

    // worldMap = new BufferedImage(gp.maxMap);

  }
  
}
