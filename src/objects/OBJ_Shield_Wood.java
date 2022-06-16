package objects;

import com.company.entity;
import com.company.gamepanel;

public class OBJ_Shield_Wood extends entity {
    public OBJ_Shield_Wood(gamepanel gp) {
        super(gp);

         name = "Wood Shield";
         down1  = setup("/objects/shield_wood", gp.tilesize, gp.tilesize);
         defenceValue = 1;
    }
}
