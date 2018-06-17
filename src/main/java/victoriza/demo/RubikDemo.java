package victoriza.demo;

import victoriza.canvas.RubikDrawing;
import victoriza.rubik.model.RubikCube;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RubikDemo {

    private final static JFrame FRAME = new JFrame("Rubik");
    private final static RubikDrawing RUBIK_CANVAS = new RubikDrawing(new RubikCube(false));

    private static int CANVAS_WIDTH = 600;
    private static int CANVAS_HEIGHT = RubikDrawing.FACE_UNIT * 3 + RubikDrawing.FACE_PADDING * 3;

    public static void main(String[] args) {
        RUBIK_CANVAS.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        FRAME.add(RUBIK_CANVAS);
        FRAME.pack();
        FRAME.setVisible(true);

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                RUBIK_CANVAS.setRubikCube(new RubikCube(true));
                FRAME.getContentPane().add(RUBIK_CANVAS);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
