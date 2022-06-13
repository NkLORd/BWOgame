package com.company;

public class AssetSetter {
    gamepanel gp;
    public AssetSetter(gamepanel gp){
        this.gp = gp;
    }
    public void setObject() {
        
    }
    public void setNPC(){
        gp.npc[0] = new npc_oldman(gp);
        gp.npc[0].worldX = gp.tilesize * 21;
        gp.npc[0].worldY = gp.tilesize * 21;

    }
}
