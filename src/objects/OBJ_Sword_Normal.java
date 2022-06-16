package objects;

import com.company.entity;
import com.company.gamepanel;

public class OBJ_Sword_Normal extends entity {
    public OBJ_Sword_Normal(gamepanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.tilesize, gp.tilesize);
        attackValue = 1;
    }
}
