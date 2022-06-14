package objects;

import com.company.entity;
import com.company.gamepanel;


public class obj_Boots extends entity {

    public obj_Boots(gamepanel gp) {

        super(gp);
        name = "Boots";
        down1 = setup("/objects/boots");

    }
}
