package victoriza.rubik.model;

import java.util.ArrayList;
import java.util.List;

abstract class Cube  {

    private static final int FRONT_FACE = 0;
    private static final int RIGHT_FACE = 1;
    private static final int BACK_FACE = 2;
    private static final int LEFT_FACE = 3;
    private static final int TOP_FACE = 4;
    private static final int BOTTOM_FACE = 5;

    private final List<CubeFace> rCube = new ArrayList<CubeFace>();

    public Cube() {
        rCube.add(new CubeFace(FRONT_FACE));
        rCube.add(new CubeFace(RIGHT_FACE));
        rCube.add(new CubeFace(BACK_FACE));
        rCube.add(new CubeFace(LEFT_FACE));
        rCube.add(new CubeFace(TOP_FACE));
        rCube.add(new CubeFace(BOTTOM_FACE));
    }

    public Cube(boolean random) {
        rCube.add(new CubeFace(random));
        rCube.add(new CubeFace(random));
        rCube.add(new CubeFace(random));
        rCube.add(new CubeFace(random));
        rCube.add(new CubeFace(random));
        rCube.add(new CubeFace(random));
    }

    public Cube(int[][] values) {
        rCube.add(new CubeFace(values));
        rCube.add(new CubeFace(values));
        rCube.add(new CubeFace(values));
        rCube.add(new CubeFace(values));
        rCube.add(new CubeFace(values));
        rCube.add(new CubeFace(values));
    }

    protected void rotateCubeFront(int zDeep) {
        CubeFace rightFace = getFaceCopy(RIGHT_FACE);
        CubeFace leftFace = getFaceCopy(LEFT_FACE);
        CubeFace topFace = getFaceCopy(TOP_FACE);
        CubeFace bottomFace = getFaceCopy(BOTTOM_FACE);

        switch (zDeep) {
            case 0: {
                getFace(RIGHT_FACE).setColumn(0, topFace.getRow(2));
                getFace(BOTTOM_FACE).setRow(0, rightFace.getColumn(0));
                getFace(LEFT_FACE).setColumn(2, bottomFace.getRow(0));
                getFace(TOP_FACE).setRow(2, leftFace.getColumn(2));

                getFace(FRONT_FACE).rotate(true);
                break;
            }
            case 1 : {
                getFace(RIGHT_FACE).setColumn(1, topFace.getRow(1));
                getFace(LEFT_FACE).setColumn(1, bottomFace.getRow(1));
                getFace(TOP_FACE).setRow(1, leftFace.getColumn(1));
                getFace(BOTTOM_FACE).setRow(1, rightFace.getColumn(1));
                break;
            }
            case 2 : {
                getFace(RIGHT_FACE).setColumn(2, topFace.getRow(0));
                getFace(LEFT_FACE).setColumn(0, bottomFace.getRow(2));
                getFace(TOP_FACE).setRow(0, leftFace.getColumn(0));
                getFace(BOTTOM_FACE).setRow(2, rightFace.getColumn(2));

                getFace(BACK_FACE).rotate(true);
                break;
            }
        }
    }


    protected void rotateColumn(int column) {
        CubeFace f0 = getFaceCopy(FRONT_FACE);
        CubeFace f1 = getFace(RIGHT_FACE);
        CubeFace f2 = getFaceCopy(BACK_FACE);
        CubeFace f3 = getFace(LEFT_FACE);
        CubeFace f4 = getFace(TOP_FACE);
        CubeFace f5 = getFaceCopy(BOTTOM_FACE);

        //Face 0: 4 -> 0
        getFace(FRONT_FACE).setColumn(column, f4.getColumn(column));

        //Face 5: 0 -> 5
        getFace(BOTTOM_FACE).setColumn(column, f0.getColumn(column));

        //Face 2: 5 -> 2
        getFace(BACK_FACE).setColumn(column, f5.getColumn(column));

        //Face 4: 2 -> 4
        getFace(TOP_FACE).setColumn(column, f2.getColumn(column));

        //Face 1: rotate
        if (column == 2) {
            f1.rotate(true);
        }

        //Face 3: Rotate
        if (column == 0) {
            f3.rotate(true);
        }
    }

    protected void rotateRow(int row) {
        CubeFace f0 = getFaceCopy(FRONT_FACE);
        getFace(FRONT_FACE).setRow(row, getFace(RIGHT_FACE).getRow(row));
        getFace(RIGHT_FACE).setRow(row, getFace(BACK_FACE).getRow(row));
        getFace(BACK_FACE).setRow(row, getFace(LEFT_FACE).getRow(row));
        getFace(LEFT_FACE).setRow(row, f0.getRow(row));

        if (row == 0) {
            getFace(TOP_FACE).rotate(true);
        } else if (row == 2) {
            getFace(BOTTOM_FACE).rotate(true);
        }
    }

    private CubeFace getFaceCopy(int face) {
        int [][] myInt = new int[getFace(face).getMatrix().length][];
        for(int i = 0; i < getFace(face).getMatrix().length; i++)
            myInt[i] = getFace(face).getMatrix()[i].clone();
        return new CubeFace(myInt);
    }

    protected CubeFace getFace(int face) {
        return rCube.get(face);
    }

    protected void setFace(int position, CubeFace face) {
        rCube.set(position,face);
    }

    public List<CubeFace> getAllFaces() {
        return new ArrayList<CubeFace>(rCube);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (CubeFace item : rCube) {
            str.append(item.toString());
        }
        return str.toString();
    }
}