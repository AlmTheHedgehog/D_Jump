package com;
import javax.swing.*;
import java.awt.event.*;

public class gfx_frame extends JFrame implements KeyListener{
    public final int F_HEIGHT = 700, F_WIDTH = 470;
    gfx_panel plane = new gfx_panel(F_HEIGHT, F_WIDTH);
    gfx_frame(){
        this.setSize(F_WIDTH, F_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(plane);
        this.addKeyListener(this);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(KeyEvent.getKeyText(e.getKeyCode())){
            case "Right":
                plane.x_vel = 5;
                plane.setHeroPic("right");
                break;
            case "Left":
                plane.x_vel = -5;
                plane.setHeroPic("left");
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if((KeyEvent.getKeyText(e.getKeyCode()) == "Right") ||
             (KeyEvent.getKeyText(e.getKeyCode()) == "Left")){
            plane.x_vel = 0;
        }
        
    }
}
