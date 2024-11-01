package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class OBJ_Health extends SuperObject{

  GamePanel gp;

  public OBJ_Health(GamePanel gp){

    this.gp = gp;

    name = "health";
    try {
      image1 = ImageIO.read(getClass().getResourceAsStream("/res/objects/health_100.png"));
      image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/health_75.png"));
      image3 = ImageIO.read(getClass().getResourceAsStream("/res/objects/health_50.png"));
      image4 = ImageIO.read(getClass().getResourceAsStream("/res/objects/health_25.png"));
      image5 = ImageIO.read(getClass().getResourceAsStream("/res/objects/health_00.png"));
      image1 = uTool.scaleImage(image1, gp.tileSize * 5, gp.tileSize * 3) ;
      image2 = uTool.scaleImage(image2, gp.tileSize * 5, gp.tileSize * 3);
      image3 = uTool.scaleImage(image3, gp.tileSize * 5, gp.tileSize * 3);
      image4 = uTool.scaleImage(image4, gp.tileSize * 5, gp.tileSize * 3);
      image5 = uTool.scaleImage(image5, gp.tileSize * 5, gp.tileSize * 3);
      
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
}
