import com.bearcave.ReadMatrix;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestNumericLineParse {

    /**
     * Test for the ReadMatrix.processRow() function that parses a string containing a white space separated
     * set of values.
     */
    @Test
    public void testNumericLineParse() {
        final double[] doubleVals = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        StringBuilder builder = new StringBuilder();
        Arrays.stream(doubleVals).forEach(val -> builder.append(val + " "));
        String doubleStr = builder.toString();
        ReadMatrix readMatrix = new ReadMatrix();
        double[] parsedVals = readMatrix.processRow(doubleStr);
        List<Double> originalList = Arrays.stream(doubleVals).boxed().toList();
        List<Double> parsedList = Arrays.stream(parsedVals).boxed().toList();
        assertEquals(originalList, parsedList);
    }
}
