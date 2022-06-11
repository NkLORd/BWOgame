package objects;
import java.awt.*;
import java.awt.image.BufferedImage;

import com.company.UtilityTool;
import com.company.gamepanel;

public class SuperObject{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0 ;
    public int solidAreaDefaultY = 0 ;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, gamepanel gp ) {

        int screenX = worldX - gp.p.worldX + gp.p.screenX;
        int screenY = worldY - gp.p.worldY + gp.p.screenY;

        if(worldX + gp.tilesize > gp.p.worldX - gp.p.screenX && worldX - gp.tilesize < gp.p.worldX + gp.p.screenX && worldY + gp.tilesize > gp.p.worldY - gp.p.screenY && worldY - gp.tilesize < gp.p.worldY + gp.p.screenY){
            g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);

        }
    }
}