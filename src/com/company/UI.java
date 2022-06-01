package com.company;

import objects.obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    gamepanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyimage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    Boolean GameFinished = false;
    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(gamepanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,60);
        obj_Key key = new obj_Key();
        keyimage = key.image;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(GameFinished == true){

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the Treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.width/2 - textLength/2;
            y = gp.height/2 - (gp.tilesize*3);
            g2.drawString(text, x, y);

            text = "Your Time is: "+dFormat.format(playtime)+"s!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.width/2 - textLength/2;
            y = gp.height/2 + (gp.tilesize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.width/2 - textLength/2;
            y = gp.height/2 + (gp.tilesize*2);
            g2.drawString(text, x, y);


            gp.gameThread = null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyimage, gp.tilesize/2, gp.tilesize/2, gp.tilesize, gp.tilesize, null);
            g2.drawString("x "+ gp.p.hasKey,74,65);

            //TIME
            playtime += (double)1/60;
            g2.drawString("Time: "+dFormat.format(playtime)+"s",gp.tilesize*11,65);

            //MESSAGE
            if(messageOn == true){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tilesize/2,gp.tilesize*5);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
        }
        }
    }
}
