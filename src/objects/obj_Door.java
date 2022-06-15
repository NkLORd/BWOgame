package objects;
import javax.imageio.ImageIO;
import java.io.IOException;

import com.company.entity;
import com.company.gamepanel;
//replaced superObject with entity
public class obj_Door extends entity {

    public obj_Door(gamepanel gp) {

        super(gp);
        name = "Door";
        down1 = setup("/objects/Door", gp.tilesize, gp.tilesize);
        collision =true;

        //dimensions of door
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}

