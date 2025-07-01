public class Solution {
    public int MaxProfit(int[] prices) {
        int res_profit = 0 ;
        int sum = prices[0] ;
        for(int i = 1 ; i < prices.Length ; i++){
            if(prices[i]-sum<0){
                sum = prices[i] ;
            }
            else if(prices[i]-sum>0){
                res_profit+=prices[i]-sum;
                 sum = prices[i] ;

            }

        }
        return res_profit;
    }
}