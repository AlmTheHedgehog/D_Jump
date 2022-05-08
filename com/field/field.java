package com.field;
import com.field.brick.brickType;
import com.gfx_panel;
import com.main_hero.*;

import java.util.ArrayList;

public class field {
    public ArrayList<brick> allBricks = new ArrayList<brick>();

    public field(){
        allBricks.add(new brick(brickType.STANDART, gfx_panel.P_WIDTH/2, 500-20));
    }

    public boolean HeroCollision(main_hero_cl hero){
        for(brick eachBrick:allBricks){
            if(((hero.x_coord + (hero.HERO_WIDTH/2) + hero.HALF_LEGS_WIDTH) >= eachBrick.left_bottom_coord[0]) &&
                    ((hero.x_coord + ((hero.HERO_WIDTH/2) - hero.HALF_LEGS_WIDTH)) <= 
                    (eachBrick.left_bottom_coord[0] + eachBrick.B_LENGTH))){                
                System.out.println("x");
                if(((hero.y_coord + hero.HERO_HEIGHT) >= (eachBrick.left_bottom_coord[1] - eachBrick.B_HEIGHT)) &&
                        (((hero.y_coord + hero.HERO_HEIGHT) + hero.y_vel) <
                        (eachBrick.left_bottom_coord[1] - eachBrick.B_HEIGHT))){
                    //TODO if collision with brown -> change pictures of explosion and delete it, return FALSE
                    return true;
                }
            }
        }
        return false;
    }

}
