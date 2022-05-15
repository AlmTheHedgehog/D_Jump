package com.main_hero;
import com.gfx_panel;
import com.field.*;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

public class main_hero_cl{
    public final int JUMP_HEIGHT = 233;  //standart jump for 700x470
    final private double SPEED_CHANGE_COEF = 6.473;  //9.33 - for max speed = 5px; 6.473 = 6px; 4.756 = 7px
    final private int P_HEIGHT, P_WIDTH, FIELD_CENTER, HERO_X_SPEED = 4;
    public final int HERO_HEIGHT = 90, HERO_WIDTH = 95, HALF_LEGS_WIDTH = 20;
    private HashMap<String, String> main_h_file_path = new HashMap<String, String>();
    public Image hero_img;
    private String hero_cur_pic;
    public int x_coord = 0, y_coord = 0, y_jump = 0, y_vel = 0, x_vel = 0; // x - left, y - top
    public boolean onGround = true, isFalling = false;

    public main_hero_cl(){
        P_HEIGHT = gfx_panel.P_HEIGHT;
        P_WIDTH = gfx_panel.P_WIDTH;
        main_h_file_path.put("left", "pic/main_h/hero_l.png");
        main_h_file_path.put("right", "pic/main_h/hero_r.png");
        main_h_file_path.put("shot", "pic/main_h/hero_s.png");
        hero_img = new ImageIcon(main_h_file_path.get("right")).getImage();
        hero_cur_pic = "right";
        FIELD_CENTER = (P_HEIGHT/2) - HERO_HEIGHT;
        y_coord = FIELD_CENTER + JUMP_HEIGHT;
    }

    /**
     * Method for picture changing
     * @param side can be "left", "right" or "shot"
     */
    private void setHeroPic(String side){
        if(hero_cur_pic != side){
            hero_img = new ImageIcon(main_h_file_path.get(side)).getImage();
            hero_cur_pic = side;
        }
        
    }

    public void action_performing(field GameField){
        x_coord += x_vel;
        y_coord += y_jump;
        if(x_coord > (P_WIDTH - (HERO_WIDTH/2))){
            x_coord = -(HERO_WIDTH/2);
        }else if(x_coord < (-(HERO_WIDTH/2))){
            x_coord = (P_WIDTH - (HERO_WIDTH/2));
        }
        if(onGround){
            onGround = false;
            isFalling = false;
        }
        if(!onGround){
            if(!isFalling){
                if(y_jump >= JUMP_HEIGHT){
                    isFalling = true;
                    y_jump = JUMP_HEIGHT - 1;
                }else{
                    y_vel = (int) Math.ceil(Math.sqrt(((-1)/SPEED_CHANGE_COEF)*(y_jump - JUMP_HEIGHT)));
                    if(((y_coord - y_jump) + HERO_HEIGHT) <= (P_HEIGHT / 2)){
                        y_coord += y_vel;
                        GameField.MoveField(y_vel);
                    }
                }
            }
            if(isFalling){
                y_coord -= y_jump;
                if(GameField.HeroCollision(this)){
                    isFalling = false;
                    onGround = true;
                    y_jump = 0;
                }else{
                    y_vel = -((int) Math.ceil(Math.sqrt(((-1)/SPEED_CHANGE_COEF)*(y_jump - JUMP_HEIGHT))));
                    y_coord += y_jump;
                }
                
                if((y_coord + HERO_HEIGHT) >= P_HEIGHT){
                    //TODO The game is lost
                }
            }
        }
        if(!onGround){
            y_jump += y_vel;
            y_coord -= y_jump;
        }        
    }

    /**
     * Method for key pressing processing
     * @param operetion can be "right", "left", "release" or "shoot"
     */
    public void keysProcessing(String operetion){
        switch(operetion){
            case "right":
                x_vel = HERO_X_SPEED;
                setHeroPic("right");
                break;
            case "left":
                x_vel = -HERO_X_SPEED;
                setHeroPic("left");
                break;
            case "release":
                x_vel = 0;
                break;
            case "shoot":
                break;
            default:
                System.out.println("Unknown key operetion");
        }
    }
}