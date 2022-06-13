package com.company;

import objects.obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    gamepanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    //BufferedImage keyimage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean GameFinished = false;
    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(gamepanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,60);
        //obj_Key key = new obj_Key(gp);
        //keyimage = key.image;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState){
            // Do PlayState stuff later
        }
        if(gp.gameState == gp.pauseState){
            drawPausedScreen();
        }
        
    }
    public void drawPausedScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.height/2;

        g2.drawString(text,x,y);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.width/2 - length/2;
        return x;
    }
}
        