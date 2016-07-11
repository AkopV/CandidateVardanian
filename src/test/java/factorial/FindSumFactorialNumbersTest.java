package factorial;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.vardanian.factorial.FindSumFactorialNumbers;

import java.math.BigInteger;

public class FindSumFactorialNumbersTest {

    @Test
    public void testSum() {
        FindSumFactorialNumbers findSumFactorialNumbers = new FindSumFactorialNumbers();
        Assert.assertEquals(findSumFactorialNumbers.sum(BigInteger.valueOf(28)), 10);
    }
}
