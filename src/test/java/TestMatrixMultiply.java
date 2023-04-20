import com.bearcave.Matrix;
import com.bearcave.MatrixMultiply;
import org.junit.Test;

import java.util.ArrayList;

public class TestMatrixMultiply extends TestMatrixBase {

    /**
     * <pre>
     *  1 2 3 4    X  1 2 3
     *  5 6 7 8       4 5 6
     *                7 8 9
     *                1 2 3
     * </pre>
     * resulting in
     *<pre>
     *  34  44  54
     *   86 112 138
     * </pre>
     */

    private final static ArrayList<double[]> matAlist = new ArrayList<>() {
        {
            add(new double[]{1.0, 2.0, 3.0, 4.0});
            add(new double[]{5.0, 6.0, 7.0, 8.0});
        }
    };

    private final static ArrayList<double[]> matBlist = new ArrayList<>() {
        {
            add(new double[]{1.0, 2.0, 3.0});
            add(new double[]{4.0, 5.0, 6.0});
            add(new double[]{7.0, 8.0, 9.0});
            add(new double[]{1.0, 2.0, 3.0});
        }
    };

    private final static ArrayList<double[]> matClist = new ArrayList<>() {
        {
            add(new double[]{34.0, 44.0, 54.0});
            add(new double[]{86.0, 112.0, 138.0});
        }
    };

    @Test
    public void testMatrixMult() {
        Matrix matA = new Matrix(matAlist);
        Matrix matB = new Matrix(matBlist);
        Matrix matC = MatrixMultiply.matmult.apply(matA, matB);
        matrixEqualsRows(matC, matClist);
    }

    @Test
    public void testParallelMatrixMult() {
        Matrix matA = new Matrix(matAlist);
        Matrix matB = new Matrix(matBlist);
        Matrix matC = MatrixMultiply.matmultStream.apply(matA, matB);
        matrixEqualsRows(matC, matClist);
    }
}
