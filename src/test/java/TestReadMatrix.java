import com.bearcave.Matrix;
import com.bearcave.ReadMatrix;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestReadMatrix extends TestMatrixBase {

    private final static String matrixText = """
                  1 2 3 4
                  5 6 7 8
            \s
                  1 2
                  3 4
                  5 6
                  7 8
            """;
    private final static ArrayList<double[]> rowListA = new ArrayList<>() {
        {
            add(new double[]{1.0, 2.0, 3.0, 4.0});
            add(new double[]{5.0, 6.0, 7.0, 8.0});
        }
    };

    private final static ArrayList<double[]> rowListB = new ArrayList<>() {
        {
            add(new double[]{1.0, 2.0});
            add(new double[]{3.0, 4.0});
            add(new double[]{5.0, 6.0});
            add(new double[]{7.0, 8.0});
        }
    };

    @Test
    public void testReadMatrix() {
        StringReader reader = new StringReader(matrixText);
        BufferedReader bufReader = new BufferedReader(reader);
        ReadMatrix readMatrix = new ReadMatrix();
        try {
            List<Matrix> matList = readMatrix.readMatrix(bufReader);
            Matrix matA = matList.get(0);
            Matrix matB = matList.get(1);
            matrixEqualsRows(matA, rowListA);
            matrixEqualsRows(matB, rowListB);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}
