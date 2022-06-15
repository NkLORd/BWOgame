package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class player extends entity {

    KeyHandler keyh;

    public final int screenX;
    public final int screenY;

    //public int hasKey = 0;

    int standCounter = 0;

    public player(gamepanel gp, KeyHandler keyh){

        super(gp);
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
        speed = 6;  //player speed
        direction = "down"; //default position of character

        //player status
        maxLife = 6;
        life = maxLife;

    }
    public void getPlayerImage(){

        up1 = setup("/playersprites/boy_up_1");
        up2 = setup("/playersprites/boy_up_2");
        down1 = setup("/playersprites/boy_down_1");
        down2 = setup("/playersprites/boy_down_2");
        left1 = setup("/playersprites/boy_left_1");
        left2 = setup("/playersprites/boy_left_2");
        right1 = setup("/playersprites/boy_right_1");
        right2 = setup("/playersprites/boy_right_2");

    }
//
//    public BufferedImage setup(String imageName){
//
//        UtilityTool uTool = new UtilityTool();
//        BufferedImage image = null;
//
//        try{
//            image = ImageIO.read(getClass().getResourceAsStream("/playersprites/"+imageName+".png"));
//            image = uTool.scaledImage(image, gp.tilesize, gp.tilesize);
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return image;
//    }


    public void update(){ //60 times per sec updates

        if(keyh.upPressed || keyh.downPressed || keyh.rightPressed || keyh.leftPressed)
        {
            if(keyh.rightPressed){
                direction = "right";
            }
            if(keyh.upPressed){
                direction = "up";
            }
            if(keyh.downPressed){
                direction = "down";
            }
            if(keyh.leftPressed){
                direction = "left";
            }
//            if(keyh.rightPressed){
//                direction = "right";
//            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //check event
            gp.eHandler.checkEvent();

            gp.keyh.enterPressed = false;

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
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if (invincible == true){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincible = false;
                invincibleCounter = 0;

            }

        }    }

    public void pickUpObject(int i){

        if(i != 999){

        }

    }

    public void interactNPC(int i){

        if(i != 999){
            if(gp.keyh.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }


    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life-=1;
                invincible = true;

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
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter,10 ,400);
    }
}