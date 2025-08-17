class Solution {
    public double new21Game(int n, int k, int maxPts) {
        //TLE
        //return recursiveStarter(n, k, maxPts);

        //TLE
        //return tabulation(n, k, maxPts);

        //return tabulationWithSlidingWindow(n, k, maxPts);

        return tabulationWithSlidingWindowSlightlySpaceOptimized(n, k, maxPts);

    }

    public double recursiveStarter(int n, int k, int maxPts) {
        double[] memo = new double[k];
        Arrays.fill(memo, -1.0);
        return recursive(0, n, k, maxPts, memo);
    }

    public double recursive(int score, int n, int k, int maxPts, double[] memo){
        if(score >= k){
            if(score <= n){
                return 1;
            }
            return 0;
        }
        if(!(memo[score] < 0)){
            return memo[score];
        }
        double currAns = 0;
        //alice can draw anything from 1 to maxPts with equal probability (1/maxPts)
        double p = 1.0/maxPts; //in other words - probability of alice drawing current card with 'ptsDrawn' points
        //instead of multiplying p each time, we multiply it once at end
        for(int ptsDrawn = 1; ptsDrawn <= maxPts; ++ptsDrawn){
            currAns += recursive(score + ptsDrawn, n, k, maxPts, memo);
        }
        currAns *= p;
        return memo[score] = currAns;
    }

    //recursion: score was going from 0 to k - 1 + maxPtsPossible, where range [k, k - 1 + maxPtsPossible] was the base case
    public double tabulation(int n, int k, int maxPts){
        //in memo it was of size k, because [k,n] was base case, here we need to initialize that as well
        double[] dp = new double[k + maxPts];
        for(int i = k; i <= n && i < (k + maxPts); ++i){
            dp[i] = 1;
        }
        for(int score = k-1; score >= 0; --score){
            double currAns = 0;
            //alice can draw anything from 1 to maxPts with equal probability (1/maxPts)
            double p = 1.0/maxPts;
            //instead of multiplying p each time, we multiply it once at end
            for(int ptsDrawn = 1; ptsDrawn <= maxPts; ++ptsDrawn){
                currAns += dp[score + ptsDrawn];
            }
            currAns *= p;
            dp[score] = currAns;
        }
        return dp[0];
    }

    //edgecase: what if k-1 + maxPts < n, so assuming for score = k-1, we must have n-k+1 1's towards 
    //right is faulty and we will have min(n-k+1, maxPts) 1's towards right
    //can further optimize it by taking array of size k+1 - refer below
    public double tabulationWithSlidingWindow(int n, int k, int maxPts){
        //in memo it was of size k, because [k,n] was base case, here we need to initialize that as well
        double[] dp = new double[k + maxPts];
        for(int i = k; i <= n && i < (k + maxPts); ++i){
            dp[i] = 1;
        }
        double p = 1.0/maxPts;
        double currAns = Math.min(n - k + 1, maxPts); //refer edgecase above
        currAns -= 1; //decrementing 1, as we will add one in the loop to keep logic simpler for updating currAns for leftwards elements
        for(int score = k-1; score >= 0; --score){
            //alice can draw anything from 1 to maxPts with equal probability (1/maxPts)
            //instead of multiplying p each time, we multiply it once at end
            
            //adding new element for current currAns
            currAns += dp[score+1];
            dp[score] = currAns;
            dp[score] *= p;
            //removing stale element for next currAns
            double elemToRem = dp[score + maxPts];
            currAns -= elemToRem;
        }
        return dp[0];
    }

    //similar to above, ever so slightly optimized regarding space
    public double tabulationWithSlidingWindowSlightlySpaceOptimized(int n, int k, int maxPts){
        //in memo it was of size k, because [k,n] was base case, here we need to initialize that as well
        double[] dp = new double[k + 1];
        dp[k] = 1;
        double p = 1.0/maxPts;
        double currAns = Math.min(n - k + 1, maxPts); //refer edgecase above
        currAns -= 1; //decrementing 1, as we will add one in the loop to keep logic simpler for updating currAns for leftwards elements
        for(int score = k-1; score >= 0; --score){
            //alice can draw anything from 1 to maxPts with equal probability (1/maxPts)
            //instead of multiplying p each time, we multiply it once at end
            
            //adding new element for current currAns
            currAns += dp[score+1];
            dp[score] = currAns;
            dp[score] *= p;
            //removing stale element for next currAns
            double elemToRem = 0;
            if(score + maxPts > k){
                if(score + maxPts <= n){
                    elemToRem = 1;
                }
                else{
                    elemToRem = 0;
                }
            }
            else{
                elemToRem = dp[score + maxPts];
            }
            currAns -= elemToRem;
        }
        return dp[0];
    }
}