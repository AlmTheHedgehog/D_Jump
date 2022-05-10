package com.field;
import com.field.brick.brickType;
import com.gfx_panel;
import com.main_hero.*;

import java.util.ArrayList;

public class field {
    public ArrayList<brick> allBricks = new ArrayList<brick>();
    private int MIN_DIST_BTW_BRICKS = 20, MAX_DIST_BTW_BRICKS = 220;

    public field(){
        allBricks.add(new brick(brickType.STANDART, gfx_panel.P_WIDTH/2, 500-20));
        allBricks.add(new brick(brickType.STANDART, gfx_panel.P_WIDTH/3, 400-20));
        allBricks.add(new brick(brickType.STANDART, 300, 200-20));
    }

    public boolean HeroCollision(main_hero_cl hero){
        for(brick eachBrick:allBricks){
            if(((hero.x_coord + (hero.HERO_WIDTH/2) + hero.HALF_LEGS_WIDTH) >= eachBrick.left_top_coord[0]) &&
                    ((hero.x_coord + ((hero.HERO_WIDTH/2) - hero.HALF_LEGS_WIDTH)) <= 
                    (eachBrick.left_top_coord[0] + eachBrick.B_LENGTH))){
                if(((hero.y_coord + hero.HERO_HEIGHT) >= eachBrick.left_top_coord[1]) &&
                        (((hero.y_coord + hero.HERO_HEIGHT) + hero.y_vel) < eachBrick.left_top_coord[1])){
                    //TODO if collision with brown -> change pictures of explosion and delete it, return FALSE
                    return true;
                }
            }
        }
        return false;
    }
    
    public void MoveField(int dist){
        ArrayList<brick> newAllBricks = new ArrayList<brick>();
        int y_topBrick = gfx_panel.P_HEIGHT; 
        for(brick eachBrick:allBricks){
            eachBrick.left_top_coord[1] += dist;
            if(y_topBrick > eachBrick.left_top_coord[1]){
                y_topBrick = eachBrick.left_top_coord[1];
            }
            if(!(eachBrick.left_top_coord[1] >= gfx_panel.P_HEIGHT)){
                newAllBricks.add(eachBrick);
            }
        }
        allBricks = newAllBricks;
        FieldFilling(y_topBrick);
        //TODO add new bricks at top
    }

    void FieldFilling(int y_top){
        if(((y_top - MIN_DIST_BTW_BRICKS) - brick.B_HEIGHT) >= 0){    
            if(y_top >= MAX_DIST_BTW_BRICKS){
                newBrick(true);
            }else{
                int probability = (int)(Math.random() * 101);
                if(probability <= 5){
                    newBrick(false);
                }
            }
        }
    }

    void newBrick(boolean notUnstable){
        int probability = (int)(Math.random() * 101);
        if(probability <= 80){
            allBricks.add(new brick(brickType.STANDART, (int)(Math.random() * (gfx_panel.P_WIDTH 
                            - brick.B_LENGTH)), 0));
        }else if((probability > 95) || (notUnstable)){
            allBricks.add(new brick(brickType.MOVING, (int)(Math.random() * (gfx_panel.P_WIDTH 
                            - brick.B_LENGTH)), 0));
        }else{
            allBricks.add(new brick(brickType.UNSTABLE, (int)(Math.random() * (gfx_panel.P_WIDTH 
                            - brick.B_LENGTH)), 0));
        }
    }
}
