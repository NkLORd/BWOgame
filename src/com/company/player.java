package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class player extends entity {

    gamepanel gp;
    KeyHandler keyh;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public player(gamepanel gp, KeyHandler keyh){

        this.gp = gp;
        this.keyh = keyh;

        screenX = gp.width/2 - (gp.tilesize/2);
        screenY = gp.height/2 - (gp.tilesize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        worldX = gp.tilesize * 23; //default position of character
        worldY = gp.tilesize * 21;
        speed = 10;  //playerspeed
        direction = "down"; //default position of character
    }
    public void getPlayerImage(){

        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }

    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/playersprites/"+imageName+".png"));
            image = uTool.scaledImage(image, gp.tilesize, gp.tilesize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }


    public void update(){ //60 times per sec updates

        if(keyh.upPressed || keyh.downPressed || keyh.rightPressed || keyh.leftPressed)
        {
            if(keyh.upPressed){
                direction = "up";
            }
            if(keyh.downPressed){
                direction = "down";
            }
            if(keyh.leftPressed){
                direction = "left";
            }
            if(keyh.rightPressed){
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            if(!collisionOn){
                switch(direction){ //cartesian coordinate movement by 4 pixels
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case"right":
                        worldX += speed;  
                        break;
                }
            }


            spriteCounter++;
            if(spriteCounter > 12){  //player image gets changed in every 12 frames
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){

        if(i != 999){

            String objectName = (this.gp.obj[i]).name;
            String str1;

            switch (objectName){
                case "key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a Key!");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You Opened the Door!");
                    }
                    else {
                        gp.ui.showMessage("You need a Key!");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed Up!");
                    break;
                case "Chest":
                    gp.ui.GameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;

            }

        }

    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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
        g2.drawImage(image, screenX, screenY, null);
    }
}