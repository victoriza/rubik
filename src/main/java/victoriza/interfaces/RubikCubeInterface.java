package victoriza.interfaces;

public interface RubikCubeInterface {

    boolean isRubikCubeSolved();

    boolean isRubikCubeFaceSolved(int face);

    void rotateColumn(int column);

    void rotateRow(int row);

    void rotateFrontFace(int zDeep);
}
