package com.company;

import java.util.Random;

public class npc_oldman extends entity{

    public npc_oldman(gamepanel gp){
        super(gp);

        direction = "down";
        speed = 10;

        getImage();
    }
    public void getImage(){
        //change image name acc to images
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");

    }
    public void setAction(){

        Random random = new Random();
        int i = random.nextInt(100)+1;
    }

}
