package objects;
import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Key extends SuperObject{

    public obj_Key() {
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
