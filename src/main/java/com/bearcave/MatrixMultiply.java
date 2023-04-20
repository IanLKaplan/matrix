
package com.bearcave;

import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

/**
 *
 * <p>
 * Multiply two matrix objects using the matrix multiply algorithm.
 * </p>
 */
public class MatrixMultiply {

    private MatrixMultiply() {}

    public static final BinaryOperator<Matrix> matmultStream = (matA, matB) -> {
        Matrix matC = new Matrix();
        var matArows = matA.getNumRows();
        var matAcols = matA.getNumCols();
        var matBrows = matB.getNumRows();
        var matBcols = matB.getNumCols();
        if (matAcols == matBrows) {
            // result matrix is A rows X B columns
            matC.zeros(matArows, matBcols);
            IntStream.range(0, matArows)
                    .forEach(i -> IntStream.range(0, matBcols)
                            .parallel()
                            .forEach(j ->
                                IntStream.range(0, matAcols)
                                        .forEach(k -> {
                                            double multAdd = Math.fma(matA.getElem(i, k), matB.getElem(k, j), matC.getElem(i, j));
                                            matC.setElem(i, j, multAdd);
                                        })
                            )
                    );
        }
        return matC;
    };


    public static final BinaryOperator<Matrix> matmult = (matA, matB) -> {
        Matrix matC = new Matrix();
        var matArows = matA.getNumRows();
        var matAcols = matA.getNumCols();
        var matBrows = matB.getNumRows();
        var matBcols = matB.getNumCols();
        if (matAcols == matBrows) {
            // result matrix is A rows X B columns
            matC.zeros(matArows, matBcols);
            for (int i = 0; i < matArows; i++) {
                for (int j = 0; j < matBcols; j++) {
                    // dot product is row A dot cols B
                    double dotProd = 0;
                    for (int k = 0; k < matAcols; k++) {
                        // Math.fma() generates the FMA instruction fma(x, y, z) = (x * y) + z
                        // dotProd = dotProd + matA.getElem(i, k) * matB.getElem(k, j);
                        dotProd = Math.fma(matA.getElem(i, k), matB.getElem(k, j), dotProd);
                    }
                    matC.setElem(i, j, dotProd);
                }
            }
        }
        return matC;
    };


}
