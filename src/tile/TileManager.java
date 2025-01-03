package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import javax.imageio.ImageIO;


import src.main.GamePanel;
import src.main.UtilityTool;

public class TileManager {
  
  GamePanel gp;
  public Tile[] tile;
  public int mapTileNum[][][]; // first dimension is to store map no.
  public int[][] overlayTileNum; // Holds only tree tiles or other overlays

  public TileManager(GamePanel gp){

    this.gp = gp;

    tile = new Tile[50];
    mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
    overlayTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

    getTileManager();
    loadMap("/res/maps/layer01.txt", 0);
    generateForestRegion(10, 25, 60, 70, 18);
    
  }

  public void getTileManager(){


    // PLACEHOLDERS

    setUp(0, "grass01", false);
    setUp(1, "grass01", false);
    setUp(2, "grass01", false);
    setUp(3, "grass01", false);
    setUp(4, "grass01", false);
    setUp(5, "grass01", false);
    setUp(6, "grass01", false);
    setUp(7, "grass01", false);
    setUp(8, "grass01", false);
    setUp(9, "grass01", false);    

    // ACTUAL TILES 

    setUp(10, "grass01", false);
    setUp(11, "grass02", false);
    setUp(12, "wall01", true);
    setUp(13, "water_up01", true);
    setUp(14, "water_up02", true);
    setUp(15, "water_up03", true);
    setUp(16, "wall01", true);
    setUp(17, "grass01", false);
    setUp(18, "tree02_32", true);
    setUp(19, "bush01", true);
    setUp(20, "earth01", false);
  }

  public void setUp(int index, String imageName , boolean collision){

    UtilityTool uTool = new UtilityTool();

    try {

      tile[index] = new Tile();
      System.out.println("Loading image from path: /res/tiles/" + imageName + ".png");
      InputStream imageStream = getClass().getResourceAsStream("/res/tiles/" + imageName + ".png");
      if (imageStream == null) {
        System.out.println("Error: Could not load image for " + imageName);
        return;
      }
      tile[index].image = ImageIO.read(imageStream);
      tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
      tile[index].collision = collision;


    } catch (IOException e) {
      e.printStackTrace();
    }

  }



  public void loadMap(String filePath, int map){

    try {

      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while(col < gp.maxWorldCol && row < gp.maxWorldRow){

        String line = br.readLine();

        while (col < gp.maxWorldCol) {

          String numbers[] = line.split("  ");

          int num = Integer.parseInt(numbers[col]);

          mapTileNum[map][col][row] = num;
          col++;
        }

        if (col == gp.maxWorldCol) {
          col = 0;
          row++;
        }
      }
      br.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
        
  
  public void generateForestRegion(int startCol, int startRow, int endCol, int endRow, int tileIndex) {
    Random random = new Random();

    for (int col = startCol; col <= endCol; col++) {
        for (int row = startRow; row <= endRow; row++) {
            // Skip tiles with collision in the base layer
            if (tile[mapTileNum[gp.currentMap][col][row]].collision) {
                continue;
            }

            // Randomize placement for a more natural look
            if (random.nextInt(100) < 30) { // 20% chance to place a tree
                overlayTileNum[col][row] = tileIndex; // Place the tree in overlay layer
                
            }
        }
    }
}


public void draw(Graphics2D g2) {
  int cameraX = gp.camera.getCameraX();
  int cameraY = gp.camera.getCameraY();

  for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
      for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
          int tileX = worldCol * gp.tileSize;
          int tileY = worldRow * gp.tileSize;

          int screenX = tileX - cameraX;
          int screenY = tileY - cameraY;

          // Only draw tiles that are within the visible screen area
          if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
              screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
              
              // Draw the base tile
              int baseTileIndex = mapTileNum[gp.currentMap][worldCol][worldRow];
              g2.drawImage(tile[baseTileIndex].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

              // Draw the overlay tile (e.g., tree) if present
              int overlayTileIndex = overlayTileNum[worldCol][worldRow];
              if (overlayTileIndex != 0) { // Assuming 0 means no overlay
                  g2.drawImage(tile[overlayTileIndex].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
              }
          }
      }
  }
}

}
