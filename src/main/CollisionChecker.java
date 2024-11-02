package src.main;

import src.entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker( GamePanel gp){

        this.gp = gp; 
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2, overlayTileNum1, overlayTileNum2;

        entity.collisionOn = false; // Reset collision flag at the start

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                overlayTileNum1 = gp.tileM.overlayTileNum[entityLeftCol][entityTopRow];
                overlayTileNum2 = gp.tileM.overlayTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ||
                    (overlayTileNum1 != 0 && gp.tileM.tile[overlayTileNum1].collision) ||
                    (overlayTileNum2 != 0 && gp.tileM.tile[overlayTileNum2].collision)) {
                    System.out.println("collisioned");
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                overlayTileNum1 = gp.tileM.overlayTileNum[entityLeftCol][entityBottomRow];
                overlayTileNum2 = gp.tileM.overlayTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ||
                    (overlayTileNum1 != 0 && gp.tileM.tile[overlayTileNum1].collision) ||
                    (overlayTileNum2 != 0 && gp.tileM.tile[overlayTileNum2].collision)) {
                    System.out.println("collisioned");
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                overlayTileNum1 = gp.tileM.overlayTileNum[entityLeftCol][entityTopRow];
                overlayTileNum2 = gp.tileM.overlayTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ||
                    (overlayTileNum1 != 0 && gp.tileM.tile[overlayTileNum1].collision) ||
                    (overlayTileNum2 != 0 && gp.tileM.tile[overlayTileNum2].collision)) {
                    System.out.println("collisioned");
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                overlayTileNum1 = gp.tileM.overlayTileNum[entityRightCol][entityTopRow];
                overlayTileNum2 = gp.tileM.overlayTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ||
                    (overlayTileNum1 != 0 && gp.tileM.tile[overlayTileNum1].collision) ||
                    (overlayTileNum2 != 0 && gp.tileM.tile[overlayTileNum2].collision)) {
                    System.out.println("collisioned");
                    entity.collisionOn = true;
                }
                break;
        }
    }
    
}
