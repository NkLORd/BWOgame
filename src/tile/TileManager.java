package tile;

import com.company.gamepanel;
import java.awt.*;
import javax.imageio.ImageIO;

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

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        }catch (IOException e) {
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
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tilesize, gp.tilesize, null); //intersection of world and screen coordinates
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
