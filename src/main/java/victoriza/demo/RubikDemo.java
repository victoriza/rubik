package victoriza.demo;

import victoriza.canvas.RubikDrawing;
import victoriza.rubik.model.RubikCube;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RubikDemo {

    private final static JFrame FRAME = new JFrame("Rubik");

    private final static int[][] values = { {0,1,2},
                                            {3,4,3},
                                            {0,1,0}};

    private final static RubikCube rCube = new RubikCube();
    private final static RubikDrawing RUBIK_CANVAS = new RubikDrawing(rCube);

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
                randomOperation();
                FRAME.getContentPane().add(RUBIK_CANVAS);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private static void randomOperation() {
        rCube.rotateRow(0);
        rCube.rotateRow(2);
        rCube.rotateColumn(0);
        rCube.rotateColumn(2);
    }
}
