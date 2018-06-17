package victoriza.test;

import org.junit.Assert;
import org.junit.Test;
import victoriza.rubik.model.RubikCube;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRubikCube {

    private static final Logger logger = Logger.getLogger("log");

    @Test
    public void testInitialState() {
        logger.log(Level.INFO,"# Testing initial state");
        RubikCube cube = new RubikCube();
        logger.log(Level.INFO,cube.toString());
        logger.log(Level.INFO,"Is solved: "+cube.isRubikCubeSolved());

        Assert.assertTrue(cube.isRubikCubeSolved());
        Assert.assertTrue(cube.isRubikCubeValid());
    }

    @Test
    public void testColOperations() {
        RubikCube cube = new RubikCube();
        logger.log(Level.INFO,"# Testing column operations:");

        logger.log(Level.INFO,cube.toString());
        cube.rotateColumn(0);
        cube.rotateColumn(0);
        cube.rotateColumn(1);
        cube.rotateColumn(2);
        logger.log(Level.INFO,cube.toString());
        Assert.assertTrue(cube.isRubikCubeValid());
    }

    @Test
    public void testRowOperations() {
        RubikCube cube = new RubikCube();
        logger.log(Level.INFO,"# Testing row operations:");

        logger.log(Level.INFO,cube.toString());
        cube.rotateRow(0);
        cube.rotateRow(0);
        cube.rotateRow(1);
        cube.rotateRow(2);

        logger.log(Level.INFO,cube.toString());
        Assert.assertTrue(cube.isRubikCubeValid());
    }

    @Test
    public void testBoth() {
        int face = 0;

        RubikCube cube = new RubikCube();
        logger.log(Level.INFO,"# Testing both operations on face: "+face);
        logger.log(Level.INFO,cube.toString());

        cube.rotateRow(0);
        cube.rotateRow(1);
        cube.rotateRow(1);
        cube.rotateRow(2);
        cube.rotateRow(2);

        cube.rotateColumn(0);
        cube.rotateColumn(2);

        logger.log(Level.INFO,cube.toString());
        Assert.assertTrue(cube.isRubikCubeValid());
    }

    @Test
    public void testRandom() {
        int face = 0;
        RubikCube cube = new RubikCube(true);
        logger.log(Level.INFO,"# Testing Random: "+face);
        logger.log(Level.INFO,cube.toString());

        cube.rotateColumn(0);

        logger.log(Level.INFO,cube.toString());
        Assert.assertTrue(cube.isRubikCubeValid());
    }

    @Test
    public void rotateFace() {
        RubikCube cube = new RubikCube(true);
        rotateFace(cube,0);
        rotateFace(cube,1);
        rotateFace(cube,2);
    }

    private void rotateFace(RubikCube cube,int zDeep) {
        logger.log(Level.INFO,"# Testing Rotate zDeep: "+zDeep);
        logger.log(Level.INFO,cube.toString());
        cube.rotateFrontFace(zDeep);
        logger.log(Level.INFO,cube.toString());
    }
}
