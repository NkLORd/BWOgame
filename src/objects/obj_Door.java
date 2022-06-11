package objects;
import javax.imageio.ImageIO;
import java.io.IOException;
import com.company.gamepanel;

public class obj_Door extends SuperObject{

    gamepanel gp;

    public obj_Door(gamepanel gp) {

        this.gp = gp;

        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaledImage(image, gp.tilesize, gp.tilesize);

        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
