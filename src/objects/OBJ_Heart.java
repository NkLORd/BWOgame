package objects;

import com.company.entity;
import com.company.gamepanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends entity {

    public OBJ_Heart(gamepanel gp){
        super(gp);
        name = "Heart";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");


    }
}
