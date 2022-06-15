package com.company;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.*;


public class entity {
    gamepanel gp;
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackedArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex= 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    int hpBarCounter = 0;
    public int type;
    int dyingCounter = 0;

    // chapter status
    public int maxLife;
    public int life;

    public entity(gamepanel gp){

        this.gp = gp;

    }
    public void setAction(){}

    public void damageReaction(){

    }
    public void speak(){


        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch(gp.p.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;

        }
    }
    public void update(){

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer =  gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.p.invincible == false ){
                gp.playSE(6);
                gp.p.life -= 1;
                gp.p.invincible = true;


            }        }

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
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

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
                    if(spriteNum == 1) {image = up1;}
                    else if(spriteNum == 2) {image = up2;}
                    break;
                case "down":
                    if(spriteNum == 1) {image = down1;}
                    else if(spriteNum == 2) {image = down2;}
                    break;
                case "left":
                    if(spriteNum == 1) {image = left1;}
                    else if(spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if(spriteNum == 1) {image = right1;}
                    else if(spriteNum == 2) {image = right2;}
                    break;
            }

            //Monster HP Bar
            if(type == 2 && hpBarOn == true){

                double oneScale = (double)gp.tilesize/maxLife;
                double hpBarvalue = oneScale * life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1,screenY - 16, gp.tilesize + 2,12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY - 15,(int)hpBarvalue,10);

                hpBarCounter++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAplha(g2,0.4F);
            }
            if(dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);

            changeAplha(g2,1F);
        }
    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        if(dyingCounter <= 5) {changeAplha(g2, 0f);}
        if(dyingCounter > 5 && dyingCounter <= 10) {changeAplha(g2, 1f);}
        if(dyingCounter > 10 && dyingCounter <= 15) {changeAplha(g2, 0f);}
        if(dyingCounter > 15 && dyingCounter <= 20) {changeAplha(g2, 1f);}
        if(dyingCounter > 20 && dyingCounter <= 25) {changeAplha(g2, 0f);}
        if(dyingCounter > 25 && dyingCounter <= 30) {changeAplha(g2, 1f);}
        if(dyingCounter > 30 && dyingCounter <= 35) {changeAplha(g2, 0f);}
        if(dyingCounter > 35 && dyingCounter <= 40) {changeAplha(g2, 1f);}
        if(dyingCounter > 40) {
            dying = false;
            alive = false;
        }
    }

    public void changeAplha(Graphics2D g2, float alphaValue){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

    }

    public BufferedImage setup(String imagePath, int width, int height){

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
