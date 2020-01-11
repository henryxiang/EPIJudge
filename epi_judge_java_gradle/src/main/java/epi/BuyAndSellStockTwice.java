package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // TODO - you fill in here.
    double[] forward = new double[prices.size()];
    double low = prices.get(0);
    for (int i = 1; i < prices.size(); i++) {
      double p = prices.get(i);
      forward[i] = Math.max(forward[i-1], p-low);
      low = Math.min(low, p);
    }

    double[] backward = new double[prices.size()+1];
    double high = prices.get(prices.size()-1);
    for (int i = prices.size()-2; i >=0; i--) {
      double p = prices.get(i);
      backward[i] = Math.max(backward[i+1], high-p);
      high = Math.max(high, p);
    }

    double maxProfit = 0.0;
    for(int i = 1; i <= prices.size(); i++) {
      maxProfit = Math.max(maxProfit, forward[i-1]+backward[i]);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
