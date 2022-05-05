package com;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class gfx_panel extends JPanel implements ActionListener{
    public int P_HEIGHT, P_WIDTH, JUMP_HEIGHT = 233;
    final double speed_chabge_coef = 6.473;  //9.33 - for max speed = 5px; 6.473 = 6px; 4.756 = 7px
    HashMap<String, String> main_h_file_path = new HashMap<String, String>();
    Image hero;
    String hero_cur_pic;
    Timer hero_timer;
    int x = 0, y_jump = 0, y_vel = 0, x_vel = 0;
    boolean onGround = true, isFalling = false;

    gfx_panel(int height, int width){
        P_HEIGHT = height;
        P_WIDTH = width;
        main_h_file_path.put("left", "pic/main_h/hero_l.png");
        main_h_file_path.put("right", "pic/main_h/hero_r.png");
        main_h_file_path.put("shot", "pic/main_h/hero_s.png");
        //this.addKeyListener(this);
        this.setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        hero = new ImageIcon(main_h_file_path.get("right")).getImage();
        hero_cur_pic = "right";
        hero_timer = new Timer(10, this);
        hero_timer.start();
    }

    public void setHeroPic(String side){
        //side = "left" || "right" || "shot"
        if(hero_cur_pic != side){
            hero = new ImageIcon(main_h_file_path.get(side)).getImage();
            hero_cur_pic = side;
        }
        
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(hero, x, (P_HEIGHT-90)-y_jump, null);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        x += x_vel;
        y_jump += y_vel;
        if(x > (P_WIDTH - (95/2))){
            x = -(95/2);
        }else if(x < (-(95/2))){
            x = (P_WIDTH - (95/2));
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
                    y_vel = (int) Math.ceil(Math.sqrt(((-1)/speed_chabge_coef)*(y_jump - JUMP_HEIGHT)));
                }
            }
            if(isFalling){
                if(y_jump <= 0){
                    isFalling = false;
                    onGround = true;
                }else{
                    y_vel = -((int) Math.ceil(Math.sqrt(((-1)/speed_chabge_coef)*(y_jump - JUMP_HEIGHT))));
                }
            }
        }
    }
}


