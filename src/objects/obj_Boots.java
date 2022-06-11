package objects;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.company.gamepanel;


public class obj_Boots extends SuperObject{

    gamepanel gp;

    public obj_Boots(gamepanel gp) {

        this.gp = gp;

        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaledImage(image, gp.tilesize, gp.tilesize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
