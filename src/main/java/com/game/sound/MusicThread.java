package com.game.sound;

import javax.sound.sampled.*;


public class MusicThread extends Thread{

    Clip ClipAudio;

    public void run(){
        //ClipAudio.close(); - to stope music
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/music.wav"));
            AudioFormat AudioFormat = audioStream.getFormat();
            DataLine.Info di = new DataLine.Info(Clip.class, AudioFormat);
            ClipAudio = (Clip)AudioSystem.getLine(di);
            ClipAudio.open(audioStream);
            ClipAudio.loop(Clip.LOOP_CONTINUOUSLY);
            ClipAudio.start();
            System.out.println(')');
        }
        catch (Exception e) {
            System.out.println("Music play error");
            System.out.println(e.getMessage());
        }
    }
}