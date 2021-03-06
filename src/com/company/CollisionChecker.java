package com.company;

public class CollisionChecker {
    gamepanel gp;
    public CollisionChecker(gamepanel gp){
        this.gp = gp;
    }
    public void checkTile(entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tilesize;
        int entityRightCol = entityRightWorldX/gp.tilesize;
        int entityTopRow = entityTopWorldY/gp.tilesize;
        int entityBottomRow = entityBottomWorldY/gp.tilesize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tilesize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tilesize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tilesize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tilesize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }

    }
    public int checkObject(entity entity, boolean player){
        int index = 999; //any arbitrary number, to check index of the interacting object

        for(int i = 0; i < gp.obj.length; i++ ){

            if(gp.obj[i]!= null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY+ entity.solidArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if ((gp.obj[i]).collision)
                        entity.collisionOn = true;
                    if (player == true)
                        index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                (this.gp.obj[i]).solidArea.x = (this.gp.obj[i]).solidAreaDefaultX;
                (this.gp.obj[i]).solidArea.y = (this.gp.obj[i]).solidAreaDefaultY;
            }
        }

        return index;
    }
    //NPC or MONSTER
    public int checkEntity(entity entity, entity[] target){

        int index = 999; //any arbitrary number, to check index of the interacting object

        for(int i = 0; i < target.length; i++ ){

            if(target[i]!= null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY+ entity.solidArea.y;
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entity.direction){
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                    }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                (target[i]).solidArea.x = (target[i]).solidAreaDefaultX;
                (target[i]).solidArea.y = (target[i]).solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(entity entity){
         boolean contactPlayer = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY+ entity.solidArea.y;
        gp.p.solidArea.x = gp.p.worldX + gp.p.solidArea.x;
        gp.p.solidArea.y = gp.p.worldY + gp.p.solidArea.y;

        switch(entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed; break;
            case "down":
                entity.solidArea.y += entity.speed; break;
            case "left":
                entity.solidArea.x -= entity.speed; break;
            case "right":
                entity.solidArea.x += entity.speed; break;
        }
        if(entity.solidArea.intersects(gp.p.solidArea)){
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.p.solidArea.x = gp.p.solidAreaDefaultX;
        gp.p.solidArea.y = gp.p.solidAreaDefaultY;
        return contactPlayer;
    }

}

