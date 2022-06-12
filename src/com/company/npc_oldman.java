package com.company;

public class npc_oldman extends entity{
    
    public npc_oldman(gamepanel gp){
        super(gp);

        direction ="down";
        speed = 1;
    
        getPlayerImage();
    }
    public void getPlayerImage(){
        //change image name acc to images
        up1 = setup("/npc/doggo");
        up2 = setup("/npc/doggo");
        down1 = setup("/npc/doggo");
        down2 = setup("/npc/doggo");
        left1 = setup("/npc/doggo");
        left2 = setup("/npc/doggo");
        right1 = setup("/npc/doggo");
        right2 = setup("/npc/doggo");

    }

}
