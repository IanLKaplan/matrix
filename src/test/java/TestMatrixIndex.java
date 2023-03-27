import com.bearcave.Matrix;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMatrixIndex extends TestMatrixBase {

    /**
     * Test for the Matrix object getElem() method
     */
    @Test
    public void testMatrixGetElem() {
        ArrayList<double[]> rowList = new ArrayList<>() {
            {
                add(new double[]{1.0, 2.0, 3.0});
                add(new double[]{4.0, 5.0, 6.0});
            }
        };
        Matrix m = new Matrix(rowList);
        matrixEqualsRows(m, rowList);
    }

    /**
     * Test for the Matrix object setElem() method
     */
    @Test
    public void testMatrixSetElem() {
        ArrayList<double[]> rowList = new ArrayList<>() {
            {
                add(new double[]{1.0, 2.0, 3.0});
                add(new double[]{4.0, 5.0, 6.0});
            }
        };
        Matrix m1 = new Matrix(rowList);
        Matrix m2 = new Matrix();
        // allocate an MxN matrix filled with zeros
        m2.zeros(m1.getNumRows(), m1.getNumCols());
        for (int row = 0; row < m1.getNumRows(); row++) {
            double[] initRow = rowList.get(row);
            for (int col = 0; col < m1.getNumCols(); col++) {
                m2.setElem(row, col, initRow[col]);
            }
        }
        List<Double> m1Vals = Arrays.stream(m1.getMatrixData()).boxed().toList();
        List<Double> m2Vals = Arrays.stream(m2.getMatrixData()).boxed().toList();
        assertEquals(m1Vals, m2Vals);
    }

}
