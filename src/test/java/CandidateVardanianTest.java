import org.testng.Assert;
import org.testng.annotations.Test;
import vardanian.factorialSum.FindSumFactorialNumbers;

import java.math.BigInteger;

public class CandidateVardanianTest {

    FindSumFactorialNumbers findSumFactorialNumbers = new FindSumFactorialNumbers();

    @Test
    public void testSum1() {
        Assert.assertEquals(findSumFactorialNumbers.sum(BigInteger.valueOf(28)), 10);
    }

    @Test
    public void testSum2() {
        Assert.assertEquals(findSumFactorialNumbers.sum(BigInteger.valueOf(210)), 3);
    }
}
