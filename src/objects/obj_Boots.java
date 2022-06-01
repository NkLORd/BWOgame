package objects;
import java.io.IOException;
import javax.imageio.ImageIO;


public class obj_Boots extends SuperObject{

    public obj_Boots() {

        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
