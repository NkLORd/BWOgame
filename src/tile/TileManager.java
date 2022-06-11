package tile;

import com.company.UtilityTool;
import com.company.gamepanel;
import java.awt.*;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    gamepanel gp;
    public Tile[] tile; //array to assign tiles
    public int mapTileNum[][]; //2d array to find coordinates

    public TileManager(gamepanel gp){

        this.gp = gp;

        tile = new Tile[10]; //arbitrary number to assign tiles by number
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //50 x 50

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        setup(0,"grass", false);
        setup(1,"wall", true);
        setup(2,"water", true);
        setup(3,"earth", false);
        setup(4,"tree", true);
        setup(5,"sand", false);
    }
    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tilesize, gp.tilesize);
            tile[index].collision = collision;

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){

        try{
            //data handling of world map
            InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0; //max 12
            int row = 0; //max 16

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine(); //till it reaches eof line by line

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();


        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tilesize; //position on the map
            int worldY = worldRow * gp.tilesize;
            int screenX = worldX - gp.p.worldX + gp.p.screenX; //position on the screen
            int screenY = worldY - gp.p.worldY + gp.p.screenY;

            if(worldX + gp.tilesize > gp.p.worldX - gp.p.screenX && worldX - gp.tilesize < gp.p.worldX + gp.p.screenX && worldY + gp.tilesize > gp.p.worldY - gp.p.screenY && worldY - gp.tilesize < gp.p.worldY + gp.p.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null); //intersection of world and screen coordinates
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
