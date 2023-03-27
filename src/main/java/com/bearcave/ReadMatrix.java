package com.bearcave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * <p>
 * Read the information for one or more matrices. Each matrix is in row/column order. The matrices
 * are separated by white space.  For example:
 * </p>
 * <pre>
 *     1 2 3 4
 *     5 6 7 8
 *
 *     1 2
 *     3 4
 *     5 6
 *     7 8
 * </pre>
 */
public class ReadMatrix {

    public static class MatrixFormatException extends Exception {
        public MatrixFormatException(final String message) {
            super(message);
        }
    }

    /**
     *
     * @param line a string containing numeric values separated by white space.
     * @return an array of doubles. If the string had no numeric values, the array will be zero length.
     */
    public double[] processRow(String line) {
        line = line.trim();
        double[] rowValues = new double[0];
        if (! line.isEmpty()) {
            rowValues = Stream.of(line.split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        }
        return rowValues;
    }


    public List<Matrix> readMatrix(BufferedReader reader) throws Exception {
        ArrayList<Matrix> matList = new ArrayList<>();
        ArrayList<double[]> vectorList = new ArrayList<>();
        var lineCount = 0;
        var numCols = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            double[] matrixRow = processRow(line);
            if (numCols == 0) {
                numCols = matrixRow.length;
            }
            if (matrixRow.length > 0) {
                if (numCols == matrixRow.length) {
                    vectorList.add(matrixRow);
                } else {
                    String message = "Line " + lineCount + " expected " + numCols + " columns, found " + matrixRow.length;
                    throw new MatrixFormatException(message);
                }
            } else {
                numCols = 0;
                if (!vectorList.isEmpty()) {
                    Matrix matrix = new Matrix(vectorList);
                    matList.add(matrix);
                    vectorList.clear();
                }
            }
            lineCount++;
        }
        if (!vectorList.isEmpty()) {
            Matrix matrix = new Matrix(vectorList);
            matList.add(matrix);
        }
        return matList;
    }

    public List<Matrix> readMatrix(final String filePath) throws Exception {
        List<Matrix> matList = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufReader = new BufferedReader(reader)) {
            matList = readMatrix(bufReader);
        }
        return matList;
    }

}
