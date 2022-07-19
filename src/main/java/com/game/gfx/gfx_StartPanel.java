package com.game.gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gfx_StartPanel extends JPanel implements ActionListener, MouseListener{
    public static int P_HEIGHT, P_WIDTH;
    gfx_StartPanel(int height, int width){
        P_HEIGHT = height;
        P_WIDTH = width;
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawRect(12, 12, 100, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("x");
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
}
