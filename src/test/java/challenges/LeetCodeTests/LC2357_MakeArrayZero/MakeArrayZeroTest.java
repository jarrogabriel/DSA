package challenges.LeetCodeTests.LC2357_MakeArrayZero;

import challenges.LeetCode.LC2357_MakeArrayZero.MakeArrayZero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakeArrayZeroTest {

    @Test
    void testEmptyArray() {
        int[] nums = {};
        assertEquals(0, MakeArrayZero.countOperations(nums));
    }

    @Test
    void testArrayWithZerosOnly() {
        int[] nums = {0, 0, 0};
        assertEquals(0, MakeArrayZero.countOperations(nums));
    }

    @Test
    void testArrayWithUniqueNumbers() {
        int[] nums = {1, 2, 3};
        assertEquals(3, MakeArrayZero.countOperations(nums));
    }

    @Test
    void testArrayWithDuplicates() {
        int[] nums = {1, 2, 2, 3, 3, 3};
        assertEquals(3, MakeArrayZero.countOperations(nums));
    }

    @Test
    void testArrayWithMixedZerosAndNumbers() {
        int[] nums = {0, 1, 0, 2, 3, 0};
        assertEquals(3, MakeArrayZero.countOperations(nums));
    }
}
