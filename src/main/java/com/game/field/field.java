package com.game.field;
import com.game.field.brick.brickType;
import com.game.gfx.gfx_panel;
import com.game.main_hero.*;

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
                    (eachBrick.left_top_coord[0] + brick.B_LENGTH))){
                if(((hero.y_coord + hero.HERO_HEIGHT) >= eachBrick.left_top_coord[1]) &&
                        (((hero.y_coord + hero.HERO_HEIGHT) + hero.y_vel) < eachBrick.left_top_coord[1])){
                    if(eachBrick.type == brickType.UNSTABLE){
                        allBricks.remove(eachBrick);
                        return false;
                    }else{
                        return true;
                    }
                    //TODO if collision with brown -> change pictures of explosion and delete it, return FALSE
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
    }

    void FieldFilling(int y_top){
        if((y_top - MIN_DIST_BTW_BRICKS) >= 0){    
            if((y_top + brick.B_HEIGHT) >= MAX_DIST_BTW_BRICKS){
                newBrick(true);
            }else{
                int probability = (int)(Math.random() * 101);
                if(probability <= 5){
                    newBrick(false, y_top + brick.B_HEIGHT);
                }
            }
        }
    }

    void newBrick(boolean notUnstable){
        newBrick(notUnstable, 1, 0);
    }
    void newBrick(boolean notUnstable, int prevBrickDist){
        newBrick(notUnstable, prevBrickDist, 0);
    }
    void newBrick(boolean notUnstable, int prevBrickDist, int availableSpace){
        int SpaceForBrick = 1;
        if(availableSpace != 0){
            SpaceForBrick = 2;
        }
        int probability = (int)(Math.random() * 101);
        if(probability <= 80){
            allBricks.add(new brick(brickType.STANDART, (int)(Math.random() * (gfx_panel.P_WIDTH - brick.B_LENGTH)), 
                            (int)((Math.random())*(-availableSpace)) - (brick.B_HEIGHT * SpaceForBrick)));
        }else if((probability > 95) || (notUnstable)){
            allBricks.add(new brick(brickType.MOVING, (int)(Math.random() * (gfx_panel.P_WIDTH - brick.B_LENGTH)), 
                            (int)((Math.random())*(-availableSpace)) - (brick.B_HEIGHT * SpaceForBrick)));
        }else{
            allBricks.add(new brick(brickType.UNSTABLE, (int)(Math.random() * (gfx_panel.P_WIDTH 
                            - brick.B_LENGTH)), -brick.B_HEIGHT));
            availableSpace = (MAX_DIST_BTW_BRICKS - prevBrickDist) - brick.B_HEIGHT;
            newBrick(true, SpaceForBrick, availableSpace);            
        }
    }
}
