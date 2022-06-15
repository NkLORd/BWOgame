package objects;

import com.company.entity;
import com.company.gamepanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends entity {

    public OBJ_Heart(gamepanel gp){
        super(gp);
        name = "Heart";
        image = setup("/objects/heart_full", gp.tilesize, gp.tilesize);
        image2 = setup("/objects/heart_half", gp.tilesize, gp.tilesize);
        image3 = setup("/objects/heart_blank", gp.tilesize, gp.tilesize);


    }
}
