#define db double
class Solution {
public:
    db dp[193][193];

    db poss(int qA, int qB) {
        if (qA > 0 && qB <= 0) {
            return 0;
        }
        if (qA <= 0 && qB > 0) {
            return 1;
        }
        if (qA <= 0 && qB <= 0) {
            return 0.5;
        }
        if (dp[qA][qB] != -1.0) {
            return dp[qA][qB];
        }

        db t1 = 0.25 * poss(qA - 4, qB);
        t1 += 0.25 * poss(qA - 3, qB - 1);
        t1 += 0.25 * poss(qA - 2, qB - 2);
        t1 += 0.25 * poss(qA - 1, qB - 3);

        return dp[qA][qB] = t1;
    }
    double soupServings(int n) {
        if (n >= 4800) {
            return 1;
        }
        for (int i = 0; i < 193; ++i)
            for (int j = 0; j < 193; ++j)
                dp[i][j] = -1.0;
        db N = ceil(n / 25.0);
        return poss(N, N);
    }
};