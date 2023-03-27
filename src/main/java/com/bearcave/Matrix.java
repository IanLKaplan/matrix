package com.bearcave;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A container for a 2-D matrix.  The matrix data is stored in a one dimensional array
 * that is indexed as a 2-D array.
 */
@Getter
@Setter
public class Matrix {
    private double[] matrixData;
    private int numCols;
    private int numRows;

    public Matrix() {
        this.numRows = 0;
        this.numCols = 0;
        this.matrixData = new double[0];
    }

    /**
     * Initialize the matrix from a list of matrix rows.
     * @param rowList a list of matrix rows, where each row is a double array.
     */
    public Matrix(List<double[]> rowList) {
        this();
        if (! rowList.isEmpty()) {
            this.numRows = rowList.size();
            this.numCols = rowList.get(0).length;
            var dataSize = this.numRows * numCols;
            this.matrixData = new double[dataSize];
            IntStream.range(0, this.numRows).forEach(ix -> System.arraycopy(rowList.get(ix), 0,
                            this.matrixData, ix * this.numCols, rowList.get(ix).length));
        }
    }

    /**
     * Initialize a numRows X numCols matrix to zero. This allocates a new matrix array.
     * @param numRows
     * @param numCols
     */
    public void zeros(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        matrixData = new double[numCols * numRows];
        Arrays.fill(matrixData, 0.0);
    }

    public void setElem(int rowIx, int colIx, double value) {
        var offset = (rowIx * this.numCols) + colIx;
        matrixData[offset] = value;
    }

    public double getElem(int rowIx, int colIx) {
        var offset = (rowIx * this.numCols) + colIx;
        return matrixData[offset];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, this.numRows).forEach(row -> {
            IntStream.range(0, this.numCols).forEach(col -> builder.append(String.format("%.4f ", this.matrixData[(row * this.numCols) + col])));
            builder.append("\n");
        });
        return builder.toString();
    }
}
