package objects;
import javax.imageio.ImageIO;
import java.io.IOException;

import com.company.entity;
import com.company.gamepanel;

public class obj_Chest extends entity {

    public obj_Chest(gamepanel gp) {

        super(gp);
        name = "Chest";
        down1 = setup("/objects/Chest");

    }
}
