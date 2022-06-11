package com.company;

import objects.obj_Boots;
import objects.obj_Key;
import objects.obj_Door;
import objects.obj_Chest;

public class AssetSetter {
    gamepanel gp;
    public AssetSetter(gamepanel gp){
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new obj_Key(gp);
        gp.obj[0].worldX = 23 * gp.tilesize;
        gp.obj[0].worldY = 7 * gp.tilesize;

        gp.obj[1] = new obj_Key(gp);
        gp.obj[1].worldX = 23 * gp.tilesize;
        gp.obj[1].worldY = 40 * gp.tilesize;

        gp.obj[2] = new obj_Key(gp);
        gp.obj[2].worldX = 38 * gp.tilesize;
        gp.obj[2].worldY = 7 * gp.tilesize;

        gp.obj[3] = new obj_Door(gp);
        gp.obj[3].worldX = 10 * gp.tilesize;
        gp.obj[3].worldY = 11 * gp.tilesize;

        gp.obj[4] = new obj_Door(gp);
        gp.obj[4].worldX = 8 * gp.tilesize;
        gp.obj[4].worldY = 28 * gp.tilesize;

        gp.obj[5] = new obj_Door(gp);
        gp.obj[5].worldX = 12 * gp.tilesize;
        gp.obj[5].worldY = 22 * gp.tilesize;

        gp.obj[6] = new obj_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tilesize;
        gp.obj[6].worldY = 7 * gp.tilesize;

        gp.obj[7] = new obj_Boots(gp);
        gp.obj[7].worldX = 37 * gp.tilesize;
        gp.obj[7].worldY = 42 * gp.tilesize;
    }
}
