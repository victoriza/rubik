package victoriza.canvas;

import victoriza.rubik.model.CubeFace;
import victoriza.rubik.model.RubikCube;

import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RubikDrawing extends Canvas {

    private static final Logger logger = Logger.getLogger("log");

    private static final int CUBE_UNIT = 50;
    public static final int FACE_UNIT = CUBE_UNIT * 3;
    public static final int FACE_PADDING = 5;

    private static final int INITIAL_PADDING_X = CUBE_UNIT * 3;
    private static final int INITIAL_PADDING_Y = CUBE_UNIT * 3;

    private RubikCube rCube;

    public RubikDrawing(RubikCube cube) {
        super();
        rCube = cube;
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
        logger.log(Level.INFO, rCube.toString());
        List<CubeFace> faces = rCube.getAllFaces();

        int paddingX = INITIAL_PADDING_X;
        int paddingY = INITIAL_PADDING_Y;

        int currentFace = 0;
        for (CubeFace face : faces) {
            logger.log(Level.INFO, face.toString());
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
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 0)), (paddingY + (CUBE_UNIT * 0)), matrix[0][0]);
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 1)), (paddingY + (CUBE_UNIT * 0)), matrix[0][1]);
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 2)), (paddingY + (CUBE_UNIT * 0)), matrix[0][2]);

            drawCubeItem(g, (paddingX + (CUBE_UNIT * 0)), (paddingY + (CUBE_UNIT * 1)), matrix[1][0]);
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 1)), (paddingY + (CUBE_UNIT * 1)), matrix[1][1]);
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 2)), (paddingY + (CUBE_UNIT * 1)), matrix[1][2]);

            drawCubeItem(g, (paddingX + (CUBE_UNIT * 0)), (paddingY + (CUBE_UNIT * 2)), matrix[2][0]);
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 1)), (paddingY + (CUBE_UNIT * 2)), matrix[2][1]);
            drawCubeItem(g, (paddingX + (CUBE_UNIT * 2)), (paddingY + (CUBE_UNIT * 2)), matrix[2][2]);

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
        switch (element % 6) {
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
