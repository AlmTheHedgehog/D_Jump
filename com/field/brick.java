package com.field;

import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

public class brick {
    enum brickType{STANDART, UNSTABLE, MOVING};
    static private final HashMap<brickType, String> BRICKS_IMG_PATH = new HashMap<brickType, String>(){{
        put(brickType.STANDART, "pic/field/brick_green.png");
        put(brickType.MOVING, "pic/field/brick_blue.png");
        put(brickType.UNSTABLE, "pic/field/brick_brown.png");
    }};
    static final public int B_LENGTH = 70, B_HEIGHT = 20;
    public brickType type;
    public int[] left_top_coord;
    public Image brick_img;
    
    /**
     *Constractor for brick class
     *@param typeBrick can be STANDART, MOVING or UNSTABLE
     *@param x_left x coordinate of left bottom corner
     *@param y_down y coordinate of left bottom corner
     */
    public brick(brickType typeBrick, int x_left, int y_top){
        type = typeBrick;
        left_top_coord = new int[]{x_left, y_top};
        brick_img = new ImageIcon(BRICKS_IMG_PATH.get(type)).getImage();
    }
}
