package objects;
import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Chest extends SuperObject{

    public obj_Chest() {
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
