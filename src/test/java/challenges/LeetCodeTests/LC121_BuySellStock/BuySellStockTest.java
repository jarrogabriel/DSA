package challenges.LeetCodeTests.LC121_BuySellStock;

import challenges.LeetCode.LC121_BuySellStock.BuySellStock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuySellStockTest {


    @Test
    void testMaxProfit() {
        // Test cases for maximum profit
        assertEquals(5, BuySellStock.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, BuySellStock.maxProfit(new int[]{7, 6, 4, 3, 1}));
        assertEquals(3, BuySellStock.maxProfit(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testEmptyOrSingleDay() {
        // Test cases for empty or single-day prices
        assertEquals(0, BuySellStock.maxProfit(new int[]{}));
        assertEquals(0, BuySellStock.maxProfit(new int[]{5}));
    }

    @Test
    void testNoProfit() {
        // Test cases where no profit can be made
        assertEquals(0, BuySellStock.maxProfit(new int[]{10, 9, 8, 7}));
    }

}
