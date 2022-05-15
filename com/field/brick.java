package com.field;

import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

import com.gfx_frame;
import com.gfx_panel;

public class brick {
    enum brickType{STANDART, UNSTABLE, MOVING};
    static private final HashMap<brickType, String> BRICKS_IMG_PATH = new HashMap<brickType, String>(){{
        put(brickType.STANDART, "pic/field/brick_green.png");
        put(brickType.MOVING, "pic/field/brick_blue.png");
        put(brickType.UNSTABLE, "pic/field/brick_brown.png");
    }};
    static final public int B_LENGTH = 70, B_HEIGHT = 20;
    private final int BRICKSPEED = 2;
    public brickType type;
    public int[] left_top_coord;
    public Image brick_img;
    private int[] movingBeginEnd;
    private int BrickVel;
    
    /**
     *Constractor for brick class
     *@param typeBrick can be STANDART, MOVING or UNSTABLE
     *@param x_left x coordinate of left bottom corner
     *@param y_down y coordinate of left bottom corner
     */
    public brick(brickType typeBrick, int x_left, int y_top){
        type = typeBrick;
        left_top_coord = new int[]{x_left, y_top};
        if(type == brickType.MOVING){
            movingBeginEnd = new int[]{(int)(Math.random() * x_left), x_left +
                    (int)(Math.random() * (gfx_panel.P_WIDTH - B_LENGTH - x_left))};
            if((movingBeginEnd[1] - movingBeginEnd[0]) < B_LENGTH){
                if((movingBeginEnd[0] - B_LENGTH) <= 0){
                    movingBeginEnd[1] += B_LENGTH;
                }else{
                    movingBeginEnd[0] -= B_LENGTH;
                }
            }
            if((int)(Math.random() * 2) > 0){
                BrickVel = -BRICKSPEED;
            }else{
                BrickVel = BRICKSPEED;
            }
        }
        brick_img = new ImageIcon(BRICKS_IMG_PATH.get(type)).getImage();
    }

    public void moveBrick(){
        if(type == brickType.MOVING){
            left_top_coord[0] += BrickVel;
            if((left_top_coord[0] > movingBeginEnd[1]) || (left_top_coord[0] < movingBeginEnd[0])){
                BrickVel *= -1;
            }
        }
    }

    //explosion function for unstable brick
}
