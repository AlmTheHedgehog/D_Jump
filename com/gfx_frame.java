package src;
import javax.swing.*;

public class gfx_frame extends JFrame {
    public final int F_HEIGHT = 700, F_WIDTH = 470;
    gfx_frame(){
        this.setSize(F_WIDTH, F_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
