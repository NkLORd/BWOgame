package com.company;

import java.awt.*;

public class EventHandler {
    gamepanel gp;
    EventRect eventRect[][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(gamepanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[col][row]  = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void checkEvent(){
        // check if char is more than 1 tile away from an event
        int xDistance = Math.abs(gp.p.worldX - previousEventX);
        int yDistance = Math.abs(gp.p.worldX - previousEventX);
        int distance = Math.max(xDistance, yDistance);
        if(distance> gp.tilesize){
            canTouchEvent = true;
        }
        if (canTouchEvent == true){
            if(hit(27, 16, "right")==true){
                damagePit(27, 16, gp.dialogueState);
            }
            if(hit(23, 19, "any")==true){
                damagePit(27, 16, gp.dialogueState);
            }
//        if(hit(27, 14, "any")==true){
//            teleport(gp.dialogueState);
//        }
            if(hit(23, 12, "up")==true){
                healingPool(23, 12, gp.dialogueState);
            }

        }

    }
    //damage detection
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.p.solidArea.x = gp.p.worldX + gp.p.solidArea.x ;
        gp.p.solidArea.y = gp.p.worldY + gp.p.solidArea.y ;
        eventRect[col][row].x = col * gp.tilesize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tilesize + eventRect[col][row].y;

        if (gp.p.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if (gp.p.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.p.worldX;
                previousEventY = gp.p.worldY;
            }
        }
        gp.p.solidArea.x = gp.p.solidAreaDefaultX;
        gp.p.solidArea.y = gp.p.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleported!";
        gp.p.worldX = gp.tilesize * 37;
        gp.p.worldY = gp.tilesize * 10;
    }
    public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.p.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingPool(int col, int row, int gameState){
        if(gp.keyh.enterPressed==true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water.\nYour life has been restored!";
            gp.p.life = gp.p.maxLife;

        }

    }
}
