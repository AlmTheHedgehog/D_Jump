package com.game.gfx;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class gfx_StartPanel extends JPanel{
    public static int P_HEIGHT, P_WIDTH;
    public int Points;
    public boolean isPanelActive = false;
    private final int BUTT_WXH[] = {300, 150};
    enum ButtStatus{PLAY_PRESSED, PLAY_UNPRESSED};
    ButtStatus ButtStat;
    ButtConteiner ButtCont;
    JLabel PointsLabel;
    JPanel helpPanel;
    gfx_StartPanel(int height, int width, int points){
        P_HEIGHT = height;
        P_WIDTH = width;
        Points = points;
        isPanelActive = true;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0, 100)));
        PointsLabel = new JLabel(Integer.toString(Points));
        PointsLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        PointsLabel.setForeground(new Color(10, 10, 100));
        PointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(PointsLabel);
        setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        ButtStat = ButtStatus.PLAY_UNPRESSED;
        ButtCont = new ButtConteiner();
        helpPanel = new JPanel();
        helpPanel.add(ButtCont);
        add(Box.createRigidArea(new Dimension(0, 200)));
        add(helpPanel);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(ButtCont.ButtImage, ButtCont.getX(), ButtCont.getY() + helpPanel.getY(), null);
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
            isPanelActive = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {           
        }

        @Override
        public void mouseExited(MouseEvent e) {            
        }
    }
    
}
