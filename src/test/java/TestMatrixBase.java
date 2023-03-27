import com.bearcave.Matrix;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestMatrixBase {

    protected void matrixEqualsRows(Matrix m, ArrayList<double[]> rowList) {
        var matRows = m.getNumRows();
        assertEquals(matRows, rowList.size());
        for (int row = 0; row < m.getNumRows(); row++) {
            double[] initRow = rowList.get(row);
            for (int col = 0; col < m.getNumCols(); col++) {
                assertEquals(m.getElem(row, col), initRow[col], 0.0);
            }
        }
    }
}
