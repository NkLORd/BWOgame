package com.company;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class entity {
    public static int worldX;
    public static int worldY;
    public static int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public static String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;


    public Rectangle solidArea;
    public static int solidAreaDefaultX;
    public static int solidAreaDefaultY;
    public boolean collisionOn = false;

}