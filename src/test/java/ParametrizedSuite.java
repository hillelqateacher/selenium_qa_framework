import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

//@RunWith(Parameterized.class)
@RunWith(JUnitParamsRunner.class)
public class ParametrizedSuite {

    @Parameterized.Parameters
    public static Collection<Integer[]> sumParameters() {
        return Arrays.asList(
                new Integer[]{20, 20, 40},
                new Integer[]{30, 30, 50}
        );
    }

    @Parameterized.Parameters
    public static Collection<Integer[]> minusParameters() {
        return Arrays.asList(
                new Integer[]{20, 20, 0},
                new Integer[]{30, 10, 20}
        );
    }

    @Parameterized.Parameter(0)
    public Integer operandOne;

    @Parameterized.Parameter(1)
    public Integer operandTwo;

    @Parameterized.Parameter(2)
    public Integer sumResult;

    @Test
    public void sumTest() {
        final Integer actualSumResult = operandOne + operandTwo;
        Assert.assertEquals("There is incorrect sum result!", sumResult, actualSumResult);
    }

    @Test
    @Parameters(method = "minusParameters")
    public void minusTest(final int operand_1, final int operand_2, final int expectedResult) {
        final int actualMinusResult = operand_1 - operand_2;
        Assert.assertEquals("There is incorrect minus result!", expectedResult, actualMinusResult);
    }
}
