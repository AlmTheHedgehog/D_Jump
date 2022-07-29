package com.game.gfx;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.net.URISyntaxException;

import javax.sound.sampled.*;

public class gfx_frame extends JFrame implements KeyListener, ActionListener{
    public final int F_HEIGHT = 700, F_WIDTH = 470;
    int Points;
    boolean isInGame = false;
    gfx_GamePanel GamePlane;
    gfx_StartPanel StartPlane;
    Timer ScreenTimer;
    Clip ClipAudio;
    public gfx_frame(){
        Points = 0;
        ScreenTimer = new Timer(100, this);
        ScreenTimer.start();
        StartPlane = new gfx_StartPanel(F_HEIGHT, F_WIDTH, Points);
        this.setSize(F_WIDTH, F_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(StartPlane);
        this.addKeyListener(this);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        PlayMusic();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(isInGame){
            GamePlane.main_hero.keysProcessing(KeyEvent.getKeyText(e.getKeyCode()).toLowerCase());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(isInGame){
            if((KeyEvent.getKeyText(e.getKeyCode()) == "Right") ||
             (KeyEvent.getKeyText(e.getKeyCode()) == "Left")){
                GamePlane.main_hero.keysProcessing("release");
            }
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isInGame){
            if(!GamePlane.isPanelActive){
                Points = Math.max(Points, GamePlane.getPoints());
                StartPlane = new gfx_StartPanel(F_HEIGHT, F_WIDTH, Points);
                isInGame = false;
                GamePlane.hero_timer.stop();
                remove(GamePlane);
                add(StartPlane);
                validate();
            }
        }else{
            if(!StartPlane.isPanelActive){
                GamePlane = new gfx_GamePanel(F_HEIGHT, F_WIDTH);
                GamePlane.isPanelActive = true;
                isInGame = true;
                remove(StartPlane);
                add(GamePlane);
                validate();
            }
        }
    }

    public void PlayMusic(){
        //ClipAudio.close(); - to stope music
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/music.wav"));
            AudioFormat AudioFormat = audioStream.getFormat();
            DataLine.Info di = new DataLine.Info(Clip.class, AudioFormat);
            ClipAudio = (Clip)AudioSystem.getLine(di);
            ClipAudio.open(audioStream);
            ClipAudio.loop(Clip.LOOP_CONTINUOUSLY);
            ClipAudio.start();
        }
        catch (Exception e) {
            System.out.println("Music play error");
            System.out.println(e.getMessage());
        }
    }
}
