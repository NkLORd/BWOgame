package objects;
import javax.imageio.ImageIO;
import java.io.IOException;
import com.company.gamepanel;

public class obj_Chest extends SuperObject{

    gamepanel gp;

    public obj_Chest(gamepanel gp) {

        this.gp = gp;

        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            uTool.scaledImage(image, gp.tilesize, gp.tilesize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
