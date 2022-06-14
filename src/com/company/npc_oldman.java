package com.company;

import java.util.Random;

public class npc_oldman extends entity{

    public npc_oldman(gamepanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();

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
    public void setDialogue() // for storing the dialogue text for the NPC
    {
        dialogues[0] = "Hello champ!";
        dialogues[1] = "So you have come to this island \nto find the treasure?";
        dialogues[2] = "I use to be a great wizard....\nbut you see now I'm a bit old \nto take any adventure";
        dialogues[3] = "Well, good luck to you!!";
        dialogues[4] = "Come to me if you need any help ;)";
    }    public void setAction(){

        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;  //pick up a number from 1 to 100
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i<= 50){
                direction = "down";
            }
            if(i > 50 && i<= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void speak(){
        super.speak();
    }

}
