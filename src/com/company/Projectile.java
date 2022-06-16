package com.company;


public class Projectile extends entity{

    entity user;
    public Projectile(gamepanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, entity user){

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update(){

        if(user == gp.p){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.p.damageMonster(monsterIndex);
                alive = false;
            }
        }
        if( user != gp.p){

        }

        switch(direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;

        }

        life--;
        if(life <= 0){
            alive = false;
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
    }
}
