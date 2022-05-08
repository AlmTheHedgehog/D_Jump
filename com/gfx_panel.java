package com;
import com.main_hero.main_hero_cl;
import com.field.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gfx_panel extends JPanel implements ActionListener{
    public static int P_HEIGHT, P_WIDTH;
    Timer hero_timer;
    main_hero_cl main_hero;
    field GameField;
    gfx_panel(int height, int width){
        P_HEIGHT = height;
        P_WIDTH = width;
        main_hero = new main_hero_cl();
        GameField = new field();
        this.setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        hero_timer = new Timer(5, this);
        hero_timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        for(brick eachBrick:GameField.allBricks){
            g2D.drawImage(eachBrick.brick_img, eachBrick.left_bottom_coord[0], eachBrick.left_bottom_coord[1], null);
        }
        g2D.drawImage(main_hero.hero_img, main_hero.x_coord, main_hero.y_coord, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        main_hero.action_performing(GameField);
    }
}


