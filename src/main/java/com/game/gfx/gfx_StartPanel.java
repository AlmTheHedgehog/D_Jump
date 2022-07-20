package com.game.gfx;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class gfx_StartPanel extends JPanel{
    public static int P_HEIGHT, P_WIDTH;
    private final int BUTT_WXH[] = {300, 150};
    enum ButtStatus{PLAY_PRESSED, PLAY_UNPRESSED};
    ButtStatus ButtStat;
    ButtConteiner ButtCont;
    GridBagConstraints PanelContainer;
    gfx_StartPanel(int height, int width){
        P_HEIGHT = height;
        P_WIDTH = width;
        setLayout(new GridBagLayout());
        PanelContainer = new GridBagConstraints();
        ButtStat = ButtStatus.PLAY_UNPRESSED;
        ButtCont = new ButtConteiner();
        PanelContainer.insets = new Insets(P_HEIGHT/2,0,0,0);
        add(ButtCont, PanelContainer);
        setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(ButtCont.ButtImage, ButtCont.getX(), ButtCont.getY(), null);
    }

    public void makeRepaint(){
        repaint();
    }

    class ButtConteiner extends JPanel implements MouseListener{
        private final HashMap<ButtStatus, URL> BUTT_IMG_PATH = new HashMap<ButtStatus, URL>(){{
            put(ButtStatus.PLAY_PRESSED, getClass().getResource("/interface/Start_butt_pressed.png"));
            put(ButtStatus.PLAY_UNPRESSED, getClass().getResource("/interface/Start_butt_unpressed.png"));
        }};
        Image ButtImage;
        ButtConteiner(){
            addMouseListener(this);
            setPreferredSize(new Dimension(BUTT_WXH[0], BUTT_WXH[1]));
            ButtImage = new ImageIcon(BUTT_IMG_PATH.get(ButtStat)).getImage();
        }

        void ChangeButtImage(){
            ButtImage = new ImageIcon(BUTT_IMG_PATH.get(ButtStat)).getImage();
            makeRepaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO change screen
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ButtStat = ButtStatus.PLAY_PRESSED;
            ChangeButtImage();            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            ButtStat = ButtStatus.PLAY_UNPRESSED;
            ChangeButtImage();
        }

        @Override
        public void mouseEntered(MouseEvent e) {           
        }

        @Override
        public void mouseExited(MouseEvent e) {            
        }
    }
    
}
