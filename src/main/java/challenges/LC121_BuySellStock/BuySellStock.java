package challenges.LC121_BuySellStock;

public class BuySellStock {


    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.printf("%d", maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int bestBuy = 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < prices[bestBuy])
            {
                bestBuy = i;
            }
            else
            {
                if((prices[i] - prices[bestBuy]) > profit)
                {
                    profit = prices[i] - prices[bestBuy];
                }
            }
        }
        return profit;
    }
}
