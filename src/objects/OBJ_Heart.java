package objects;

import com.company.gamepanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{
    gamepanel gp;

    public OBJ_Heart(gamepanel gp){

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image = uTool.scaledImage(image, gp.tilesize, gp.tilesize);
            image2 = uTool.scaledImage(image2, gp.tilesize, gp.tilesize);
            image3 = uTool.scaledImage(image3, gp.tilesize, gp.tilesize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
