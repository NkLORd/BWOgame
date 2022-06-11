package objects;
import javax.imageio.ImageIO;
import java.io.IOException;
import com.company.gamepanel;

public class obj_Key extends SuperObject{

    gamepanel gp;

    public obj_Key(gamepanel gp){

        this.gp = gp;

        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaledImage(image, gp.tilesize, gp.tilesize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
