package victoriza.canvas;

import victoriza.rubik.model.CubeFace;
import victoriza.rubik.model.RubikCube;

import java.awt.*;
import java.util.List;

public class RubikDrawing extends Canvas {

    public static final int CUBE_UNIT = 50;
    public static final int FACE_UNIT = CUBE_UNIT * 3;
    public static final int FACE_PADDING = 5;

    private static final int INITIAL_PADDING_X = CUBE_UNIT * 3;
    private static final int INITIAL_PADDING_Y = CUBE_UNIT * 3;

    private RubikCube rCube;

    public RubikDrawing(RubikCube cube) {
        super();
        rCube = cube;
    }

    public void setRubikCube(RubikCube rubikCube) {
        rCube = rubikCube;
    }

    /**
     *          [4, 4, 4]
     *          [4, 4, 4]
     *          [4, 4, 4]
     * [3, 3, 3][0, 0, 0][1, 1, 1][2, 2, 2]
     * [3, 3, 3][0, 0, 0][1, 1, 1][2, 2, 2]
     * [3, 3, 3][0, 0, 0][1, 1, 1][2, 2, 2]
     *          [5, 5, 5]
     *          [5, 5, 5]
     *          [5, 5, 5]
     *
     * @param g
     */

    @Override
    public void paint(Graphics g) {
        System.out.println("paint!");

        List<CubeFace> faces = rCube.getAllFaces();

        int paddingX = INITIAL_PADDING_X;
        int paddingY = INITIAL_PADDING_Y;

        int currentFace = 0;
        for (CubeFace face : faces) {

            if (currentFace == 0) {
                paddingX = INITIAL_PADDING_X;
                paddingY = INITIAL_PADDING_Y;
            } else if (currentFace == 1) {
                paddingX += FACE_UNIT+FACE_PADDING;
            } else if (currentFace == 2) {
                paddingX += FACE_UNIT+FACE_PADDING;
            } else if (currentFace == 3) {
                paddingX = INITIAL_PADDING_X - FACE_UNIT - FACE_PADDING;
                paddingY = INITIAL_PADDING_Y;
            } else if (currentFace == 4) {
                paddingX = INITIAL_PADDING_X;
                paddingY = INITIAL_PADDING_Y - FACE_UNIT - FACE_PADDING;
            } else if (currentFace == 5) {
                paddingX = INITIAL_PADDING_X;
                paddingY = INITIAL_PADDING_Y + FACE_UNIT + FACE_PADDING;
            }

            int[][] matrix = face.getMatrix();
            int i, j;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    drawCubeItem(g, (paddingX + (CUBE_UNIT * i)), (paddingY + (CUBE_UNIT * j)), matrix[i][j]);
                }
            }
            currentFace ++;
        }
    }

    private void drawCubeItem(Graphics g, int x, int y, int element) {
        int innerPadding = 2;
        g.setColor(Color.BLACK);
        g.fillRect(
                x,
                y,
                CUBE_UNIT,
                CUBE_UNIT);

        g.setColor(getColor(element));
        g.fillRect(
                x+innerPadding,
                y+innerPadding,
                CUBE_UNIT - 2*innerPadding,
                CUBE_UNIT- 2*innerPadding);
    }

    private Color getColor(int element) {
        switch (element) {
            case 0 : {
                return Color.RED;
            } case 1 : {
                return Color.BLUE;
            } case 2 : {
                return Color.GREEN;
            } case 3 : {
                return Color.WHITE;
            } case 4 : {
                return Color.YELLOW;
            } case 5 : {
                return Color.ORANGE;
            }
        }
        return Color.CYAN;
    }
}
