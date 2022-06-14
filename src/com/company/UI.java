package com.company;

import objects.OBJ_Heart;
import objects.SuperObject;
import com.company.player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    gamepanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    //BufferedImage keyimage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean GameFinished = false;
//    double playtime;
//    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0: the first screen, 1: Second screen



    public UI(gamepanel gp){
        this.gp = gp;
        arial_40 = new Font("Cambria",Font.PLAIN,40);
        arial_80B = new Font("Cambria",Font.BOLD,60);
        //obj_Key key = new obj_Key(gp);
        //keyimage = key.image;

        //create hud obj
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        //title state
        if(gp.gameState == gp.titleState){

            drawTitleScreen();

        }

        //this is play state
        if (gp.gameState == gp.playState){
           drawPlayerLife();
            // Do PlayState stuff later
        }
        //this is pause state
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPausedScreen();
        }
        //dialogue state
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();

        }
        
    }

    public void drawTitleScreen(){

        if(titleScreenState == 0){

            g2.setColor(new Color(0,0,0));
            g2.fillRect(0,0, gp.width,gp.height);

            //title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "Baby's Way Out";
            int x = getXforCenteredText(text);
            int y = gp.tilesize * 3;

            //Shadow
            g2.setColor(Color.gray);
            g2.drawString(text, x+3, y+3);
            //Main Color
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            //Baby's image to be displayed
            x = gp.width/2 - (gp.tilesize * 2)/2;
            y += gp.tilesize * 2;
            g2.drawImage(gp.p.down1, x, y, gp.tilesize * 2,gp.tilesize *2,null);

            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tilesize * 3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tilesize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tilesize * 1;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tilesize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tilesize * 1;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tilesize, y);
            }
        }
        else if(titleScreenState == 1){

            //Class Section Screen
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select Your Class";
            int x = getXforCenteredText(text);
            int y = gp.tilesize * 3;
            g2.drawString(text, x, y);

            text = "Infant";
            x = getXforCenteredText(text);
            y += gp.tilesize * 3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tilesize, y);
            }

            text = "Toddler";
            x = getXforCenteredText(text);
            y += gp.tilesize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tilesize, y);
            }

            text = "Kid";
            x = getXforCenteredText(text);
            y += gp.tilesize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tilesize, y);
            }
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tilesize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x - gp.tilesize, y);
            }
        }

    }

    public void drawPlayerLife(){

        gp.p.life = 6; //will have to change this
        int x = gp.tilesize/2;
        int y = gp.tilesize/2;
        int i = 0;

        //draw blank heart
        while(i < gp.p.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tilesize;
        }

        //reset
        x = gp.tilesize/2;
        y = gp.tilesize/2;
        i = 0;

        //draw current life
        while(i < gp.p.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.p.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tilesize;
        }
    }
    public void drawPausedScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.height/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen() {
        //dialogue window
        int x = gp.tilesize*2;
        int y = gp.tilesize/2;
        int width = gp.width - (gp.tilesize*4);
        int height = gp.tilesize*4;
        drawSubWindow( x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F)); // to change the font of dialogues
        x += gp.tilesize;
        y += gp.tilesize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;

        }

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(225,225,225);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10 , 25, 25 );
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.width/2 - length/2;
        return x;
    }
}
        