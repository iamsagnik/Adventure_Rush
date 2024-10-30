package src.main;

public class Camera {

  GamePanel gp;
  private int cameraX, cameraY;   // Position of the camera in the world
  private int screenWidth, screenHeight;  // Dimensions of the camera view
  private int worldWidth, worldHeight;    // Total world dimensions
    
    public Camera(GamePanel gp) {
        this.gp = gp;
        this.screenWidth = gp.screenWidth;
        this.screenHeight = gp.screenHeight;
        this.worldWidth = gp.maxWorldCol * gp.tileSize;
        this.worldHeight = gp.maxWorldRow * gp.tileSize;
    }   

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    //Update the camera position based on the player’s world position
    public void update(int playerWorldX, int playerWorldY, int playerScreenX, int playerScreenY) {
        // Center the camera on the player's world position minus their screen offset
        cameraX = playerWorldX - playerScreenX;
        cameraY = playerWorldY - playerScreenY;

        // Clamp cameraX to the horizontal boundaries of the world
        cameraX = Math.max(0, Math.min(cameraX, worldWidth - screenWidth));

        // Clamp cameraY to the vertical boundaries of the world
        cameraY = Math.max(0, Math.min(cameraY, worldHeight - screenHeight));
    }       
}

