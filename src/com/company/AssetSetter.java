package com.company;

import monster.MON_GreenSlime;
import objects.obj_Door;

public class AssetSetter {
    gamepanel gp;
    public AssetSetter(gamepanel gp){
        this.gp = gp;
    }
    public void setObject() {
//        gp.obj[0] = new obj_Door(gp);
//        gp.obj[0].worldX = gp.tilesize * 21; // this need to be deleted but im commenting it for now
//        gp.obj[0].worldY = gp.tilesize * 22;
//
//        gp.obj[1] = new obj_Door(gp);
//        gp.obj[1].worldX = gp.tilesize * 23;
//        gp.obj[1].worldY = gp.tilesize * 25;

    }
    public void setNPC(){



        gp.npc[0] = new npc_oldman(gp);
        gp.npc[0].worldX = gp.tilesize * 21;
        gp.npc[0].worldY = gp.tilesize * 21;

        gp.npc[1] = new npc_oldman(gp);
//        gp.npc[1].worldX = gp.tilesize * 11;  // this need to be deleted but im commenting it for now
//        gp.npc[1].worldY = gp.tilesize * 21;
//
//        gp.npc[2] = new npc_oldman(gp);
//        gp.npc[2].worldX = gp.tilesize * 31;
//        gp.npc[2].worldY = gp.tilesize * 21;
//
//        gp.npc[3] = new npc_oldman(gp);
//        gp.npc[3].worldX = gp.tilesize * 21;
//        gp.npc[3].worldY = gp.tilesize * 11;
//
//        gp.npc[4] = new npc_oldman(gp);
//        gp.npc[4].worldX = gp.tilesize * 21;
//        gp.npc[4].worldY = gp.tilesize * 31;

    }
    public void setMonster() {
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*23;
        gp.monster[i].worldY = gp.tilesize*36;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*23;
        gp.monster[i].worldY = gp.tilesize*37;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*25;
        gp.monster[i].worldY = gp.tilesize*38;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*22;
        gp.monster[i].worldY = gp.tilesize*40;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*28;
        gp.monster[i].worldY = gp.tilesize*39;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*36;
        gp.monster[i].worldY = gp.tilesize*39;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*33;
        gp.monster[i].worldY = gp.tilesize*39;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*35;
        gp.monster[i].worldY = gp.tilesize*35;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tilesize*38;
        gp.monster[i].worldY = gp.tilesize*38;
        i++;
    }
}
