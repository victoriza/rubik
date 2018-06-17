package victoriza.rubik.model;

import victoriza.interfaces.RubikCubeInterface;

import java.util.HashMap;
import java.util.List;

public class RubikCube extends Cube implements RubikCubeInterface {

    private final static String LINE_SEPARATOR = "line.separator";
    private static final String PADDING = "         ";

    public RubikCube() {
        super();
    }

    public RubikCube(boolean isRandom) {
        super(isRandom);
    }

    public void rotateColumn(int column) {
        super.rotateColumn(column);
    }

    public void rotateRow(int row) {
        super.rotateRow(row);
    }

    public void rotateFrontFace(int zDeep) {
        super.rotateCubeFront(zDeep);
    }

    public boolean isRubikCubeFaceSolved(int face) {
        return getFace(face).areAllMatrixElementsEqual();
    }

    public boolean isRubikCubeValid() {
        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
        counter.put(0, 0);
        counter.put(1, 0);
        counter.put(2, 0);
        counter.put(3, 0);
        counter.put(4, 0);
        counter.put(5, 0);

        for (CubeFace item : getAllFaces()) {
            for (Integer element : item.getAllElements()) {
                counter.put(element, counter.get(element) + 1);
            }
        }
        return counter.get(0) == CubeFace.FACE_ELEMENTS && counter.get(1) == CubeFace.FACE_ELEMENTS &&
                counter.get(2) == CubeFace.FACE_ELEMENTS && counter.get(3) == CubeFace.FACE_ELEMENTS &&
                counter.get(4) == CubeFace.FACE_ELEMENTS && counter.get(5) == CubeFace.FACE_ELEMENTS;
    }

    public boolean isRubikCubeSolved() {
        for (CubeFace item : getAllFaces()) {
            if (!item.areAllMatrixElementsEqual()) {
                return false;
            }
        }
        return isRubikCubeValid();
    }

    @Override
    public String toString() {
        String newline = System.getProperty(LINE_SEPARATOR);
        String padding = PADDING;

        StringBuffer stringBuffer = new StringBuffer();
        List<CubeFace> faces = getAllFaces();
        stringBuffer.append(newline);

        stringBuffer.append(padding);
        stringBuffer.append(faces.get(4).getRow(0).toString());
        stringBuffer.append(newline);
        stringBuffer.append(padding);
        stringBuffer.append(faces.get(4).getRow(1).toString());
        stringBuffer.append(newline);
        stringBuffer.append(padding);
        stringBuffer.append(faces.get(4).getRow(2).toString());
        stringBuffer.append(newline);

        stringBuffer.append(faces.get(3).getRow(0).toString());
        stringBuffer.append(faces.get(0).getRow(0).toString());
        stringBuffer.append(faces.get(1).getRow(0).toString());
        stringBuffer.append(faces.get(2).getRow(0).toString());

        stringBuffer.append(newline);

        stringBuffer.append(faces.get(3).getRow(1).toString());
        stringBuffer.append(faces.get(0).getRow(1).toString());
        stringBuffer.append(faces.get(1).getRow(1).toString());
        stringBuffer.append(faces.get(2).getRow(1).toString());

        stringBuffer.append(newline);

        stringBuffer.append(faces.get(3).getRow(2).toString());
        stringBuffer.append(faces.get(0).getRow(2).toString());
        stringBuffer.append(faces.get(1).getRow(2).toString());
        stringBuffer.append(faces.get(2).getRow(2).toString());

        stringBuffer.append(newline);

        stringBuffer.append(padding);
        stringBuffer.append(faces.get(5).getRow(0).toString());
        stringBuffer.append(newline);
        stringBuffer.append(padding);
        stringBuffer.append(faces.get(5).getRow(1).toString());
        stringBuffer.append(newline);
        stringBuffer.append(padding);
        stringBuffer.append(faces.get(5).getRow(2).toString());
        stringBuffer.append(newline);

        return stringBuffer.toString();
    }
}
