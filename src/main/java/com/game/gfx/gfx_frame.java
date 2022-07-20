package com.game.gfx;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class gfx_frame extends JFrame implements KeyListener{
    public final int F_HEIGHT = 700, F_WIDTH = 470;
    gfx_GamePanel GamePlane;
    gfx_StartPanel StartPlane;
    public gfx_frame(){
        GamePlane = new gfx_GamePanel(F_HEIGHT, F_WIDTH);
        StartPlane = new gfx_StartPanel(F_HEIGHT, F_WIDTH);
        this.setSize(F_WIDTH, F_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(StartPlane);
        this.addKeyListener(this);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        GamePlane.main_hero.keysProcessing(KeyEvent.getKeyText(e.getKeyCode()).toLowerCase());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if((KeyEvent.getKeyText(e.getKeyCode()) == "Right") ||
             (KeyEvent.getKeyText(e.getKeyCode()) == "Left")){
                GamePlane.main_hero.keysProcessing("release");
        }
        
    }
}

//TODO plane like a screen - it`s needed to create new classes of planes for hello and lose screens

//TODO add music :)
