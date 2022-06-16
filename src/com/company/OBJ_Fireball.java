package com.company;

public class OBJ_Fireball extends Projectile{

    gamepanel gp;
    public OBJ_Fireball(gamepanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCoat = 1;
        alive = false;
        getImage();

    }

    public void getImage(){

        up1 = setup("/objects/fireball_up_1", gp.tilesize, gp.tilesize);
        up2 = setup("/objects/fireball_up_2", gp.tilesize, gp.tilesize);
        down1 = setup("/objects/fireball_down_1", gp.tilesize, gp.tilesize);
        down2 = setup("/objects/fireball_down_2", gp.tilesize, gp.tilesize);
        left1 = setup("/objects/fireball_left_1", gp.tilesize, gp.tilesize);
        left2 = setup("/objects/fireball_left_2", gp.tilesize, gp.tilesize);
        right1 = setup("/objects/fireball_right_1", gp.tilesize, gp.tilesize);
        right2 = setup("/objects/fireball_right_2", gp.tilesize, gp.tilesize);

    }



}
