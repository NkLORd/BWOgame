package com.company;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class player extends entity {

    KeyHandler keyh;

    public final int screenX;
    public final int screenY;

    //public int hasKey = 0;

    int standCounter = 0;

    public boolean attackCanceled = false;

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

        attackedArea.width = 36;
        attackedArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){

        worldX = gp.tilesize * 23; //default position of character
        worldY = gp.tilesize * 21;
        speed = 6;  //player speed
        direction = "down"; //default position of character

        //player status
        level = 1;
        strength  =1; //more strength > more damage he gives
        maxLife = 6;
        life = maxLife;
        dexterity = 1; // more dexterity > less damage he receives
        exp = 0;
        nextLevelExp = 5; // experience to level up
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack(); // strength + weapon
        defence = getDefence(); // dexterity + shield

    }
    public int getAttack(){

        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefence(){

        return defence = dexterity * currentShield.defenceValue;
    }
    public void getPlayerImage(){

        up1 = setup("/playersprites/boy_up_1", gp.tilesize, gp.tilesize);
        up2 = setup("/playersprites/boy_up_2", gp.tilesize, gp.tilesize);
        down1 = setup("/playersprites/boy_down_1", gp.tilesize, gp.tilesize);
        down2 = setup("/playersprites/boy_down_2", gp.tilesize, gp.tilesize);
        left1 = setup("/playersprites/boy_left_1", gp.tilesize, gp.tilesize);
        left2 = setup("/playersprites/boy_left_2", gp.tilesize, gp.tilesize);
        right1 = setup("/playersprites/boy_right_1", gp.tilesize, gp.tilesize);
        right2 = setup("/playersprites/boy_right_2", gp.tilesize, gp.tilesize);

    }

    public void getPlayerAttackImage(){

        attackUp1 = setup("/playersprites/boy_attack_up_1", gp.tilesize, gp.tilesize * 2);
        attackUp2 = setup("/playersprites/boy_attack_up_2", gp.tilesize, gp.tilesize * 2);
        attackDown1 = setup("/playersprites/boy_attack_down_1", gp.tilesize, gp.tilesize * 2);
        attackDown2 = setup("/playersprites/boy_attack_down_2", gp.tilesize, gp.tilesize * 2);
        attackLeft1 = setup("/playersprites/boy_attack_left_1", gp.tilesize * 2, gp.tilesize);
        attackLeft2 = setup("/playersprites/boy_attack_left_2", gp.tilesize * 2, gp.tilesize);
        attackRight1 = setup("/playersprites/boy_attack_right_1", gp.tilesize * 2, gp.tilesize);
        attackRight2 = setup("/playersprites/boy_attack_right_2", gp.tilesize * 2, gp.tilesize);
    }


    public void update(){ //60 times per sec updates


        if(attacking){

            attacking();
        }

        else if(keyh.upPressed || keyh.downPressed || keyh.rightPressed || keyh.leftPressed || keyh.enterPressed)
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

            //check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
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

            if(!collisionOn && keyh.enterPressed == false){
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

            if(keyh.enterPressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;

            gp.keyh.enterPressed = false;


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
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;

            }

        }    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //Save the current worldx, worldy, solidarea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player's worldx/y for the attackarea
            switch(direction){
                case "up": worldY -= attackedArea.height; break;
                case "down": worldY += attackedArea.height; break;
                case "left": worldX -= attackedArea.width; break;
                case "right": worldX += attackedArea.width; break;
            }

            //attackArea becomes solidarea
            solidArea.width = attackedArea.width;;
            solidArea.height = attackedArea.height;

            //check monster collision with the updated worldx, worldy, solidarea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //attackarea becomes solidarea
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = attackedArea.width;;
            solidArea.height = attackedArea.height;;

        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){

        if(i != 999){

        }

    }

    public void interactNPC(int i){

        if(keyh.enterPressed){

            if(i != 999){
                if(gp.keyh.enterPressed == true){
                    attackCanceled = true;
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
                }
            }
        }
    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                gp.playSE(6);
                life -= 1;
                invincible = true;

            }

        }
    }

    public void damageMonster(int i){

        if(i != 999){

            if(gp.monster[i].invincible == false){

                gp.playSE(5);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                }
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if(attacking == false){
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tilesize;
                    if(spriteNum == 1) {image = attackUp1;}
                    if(spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackDown1;}
                    if(spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tilesize;
                    if(spriteNum == 1) {image = attackLeft1;}
                    if(spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackRight1;}
                    if(spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter,10 ,400);
    }
}