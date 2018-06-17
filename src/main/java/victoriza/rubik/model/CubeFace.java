package victoriza.rubik.model;

import java.util.ArrayList;
import java.util.List;

public class CubeFace {

    private final static int MAX_ROWS = 3;
    private final static int MAX_COLUMNS = 3;

    protected static final int FACE_ELEMENTS = MAX_ROWS * MAX_COLUMNS;

    private final int matrix[][] = new int[MAX_ROWS][MAX_COLUMNS];

    protected CubeFace(int number) {
        int i,j;
        for(i=0;i<MAX_ROWS;i++) {
            for(j=0;j<MAX_COLUMNS;j++) {
                matrix[i][j]=number;
            }
        }
    }

    protected CubeFace(boolean random) {
        int i,j;
        for(i=0;i<MAX_ROWS;i++) {
            for(j=0;j<MAX_COLUMNS;j++) {
                matrix[i][j] = 0 + (int)(Math.random() * ((MAX_ROWS * 2) + 1));
            }
        }
    }

    protected CubeFace(int[][] values) {
        int i,j;
        for(i=0;i<MAX_ROWS;i++) {
            for(j=0;j<MAX_COLUMNS;j++) {
                matrix[i][j]=values[i][j];
            }
        }
    }

    protected boolean areAllMatrixElementsEqual() {
        int i, j;
        int value = getElement(0,0);
        for (i = 0; i<MAX_ROWS; i++) {
            for (j = 0;j<MAX_COLUMNS; j++) {
                if (getElement(i,j) != value) {
                    return false;
                }
            }
        }
        return true;
    }

    protected List<Integer> getColumn(int column) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(matrix[0][column]);
        list.add(matrix[1][column]);
        list.add(matrix[2][column]);
        return list;
    }

    protected List<Integer> getRow(int row) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(matrix[row][0]);
        list.add(matrix[row][1]);
        list.add(matrix[row][2]);
        return list;
    }

    protected void setColumn(int column, List<Integer> values){
        matrix[0][column] = values.get(0);
        matrix[1][column] = values.get(1);
        matrix[2][column] = values.get(2);
    }

    protected void setRow(int row, List<Integer> values){
        matrix[row][0] = values.get(0);
        matrix[row][1] = values.get(1);
        matrix[row][2] = values.get(2);
    }

    private int getElement(int x,int y) {
        return matrix[x % MAX_ROWS][y % MAX_COLUMNS];
    }

    protected List<Integer> getAllElements() {
        List<Integer> elements = new ArrayList<Integer>();
        int i, j;
        for (i = 0; i <MAX_ROWS; i++) {
            for (j = 0; j <MAX_COLUMNS; j++) {
                elements.add(matrix[i][j]);
            }
        }
        return elements;
    }

    protected void rotate(boolean isClockWise) {
        if (isClockWise) {
            rotate();
            rotate();
            rotate();
        } else {
            rotate();
        }
    }

    private void rotate() {
        for (int x = 0; x < MAX_ROWS / 2; x++)
        {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y < MAX_ROWS-x-1; y++)
            {
                // store current cell in temp variable
                int temp = matrix[x][y];

                // move values from right to top
                matrix[x][y] = matrix[y][MAX_ROWS-1-x];

                // move values from bottom to right
                matrix[y][MAX_ROWS-1-x] = matrix[MAX_ROWS-1-x][MAX_ROWS-1-y];

                // move values from left to bottom
                matrix[MAX_ROWS-1-x][MAX_ROWS-1-y] = matrix[MAX_ROWS-1-y][x];

                // assign temp to left
                matrix[MAX_ROWS-1-y][x] = temp;
            }
        }


    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String newline = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        int i, j;
        for (i = 0; i <MAX_ROWS; i++) {
            for (j = 0; j <MAX_COLUMNS; j++) {
                stringBuffer.append(matrix[i][j]);
            }
            stringBuffer.append(newline);
        }
        return stringBuffer.toString();
    }
}
