package victoriza.screen;

import java.awt.*;
import javax.swing.JFrame;

public class Drawing extends Canvas {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Drawing();
        canvas.setSize(600, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.fillOval(100, 100, 200, 200);
        g.fillRoundRect(100, 100, 50, 50,1,1);
    }
    
}