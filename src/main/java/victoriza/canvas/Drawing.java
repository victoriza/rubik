package victoriza.canvas;

import java.awt.*;
import javax.swing.JFrame;

public class Drawing extends Canvas {

    protected static int CANVAS_HEIGTH = 600;
    protected static int CANVAS_WIDTH = 400;

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Drawing();
        canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGTH);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.fillOval(100, 100, 100, 100);
    }

    public void boxOval(Graphics g, Rectangle bb) {
        g.fillOval(bb.x, bb.y, bb.width, bb.height);
    }

    public void mickey(Graphics g, Rectangle bb) {
        boxOval(g, bb);

        int dx = bb.width / 2;
        int dy = bb.height / 2;
        Rectangle half = new Rectangle(bb.x, bb.y, dx, dy);

        half.translate(-dx / 2, -dy / 2);
        boxOval(g, half);

        half.translate(dx * 2, 0);
        boxOval(g, half);
    }

}