package com.company;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.*;


public class entity {
    gamepanel gp;
    public int worldX;
    public int worldY;
    public static int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public static String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;

    public entity(gamepanel gp){

        this.gp = gp;

    }
    public void setAction(){}
    public void update(){}

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.p.worldX + gp.p.screenX;
        int screenY = worldY - gp.p.worldY + gp.p.screenY;

        if(worldX + gp.tilesize > gp.p.worldX - gp.p.screenX &&
            worldX - gp.tilesize < gp.p.worldX + gp.p.screenX &&
            worldY + gp.tilesize > gp.p.worldY - gp.p.screenY &&
            worldY - gp.tilesize < gp.p.worldY + gp.p.screenY){

            switch (direction) {
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    else if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    else if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    else if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    else if(spriteNum == 2){
                        image = right2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);

        }
    }
    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaledImage(image, gp.tilesize, gp.tilesize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
