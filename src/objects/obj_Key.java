package objects;
import javax.imageio.ImageIO;
import java.io.IOException;

import com.company.entity;
import com.company.gamepanel;

public class obj_Key extends entity {

    public obj_Key(gamepanel gp){
        super(gp);
        name = "key";
        down1 = setup("/objects/key");

    }

}
