import com.bearcave.Matrix;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class TestMatrixConstructor {

    /**
     * Test Matrix construction from an array list of matrix rows.
     */
    @Test
    public void testMatrixConstructor() {
        ArrayList<double[]> rowList = new ArrayList<>() {
            {
                add(new double[]{1.0, 2.0, 3.0});
                add(new double[]{4.0, 5.0, 6.0});
            }
        };
        Matrix m = new Matrix(rowList);
        List<Double> doubleList = IntStream.range(0, 6).mapToObj(i -> Double.valueOf(i+1)).toList();
        List<Double> matListVals = Arrays.stream(m.getMatrixData()).boxed().toList();
        assertEquals(doubleList, matListVals);
    }

}
