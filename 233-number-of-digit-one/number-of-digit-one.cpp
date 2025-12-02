class Solution {
private: 
    int dp[11][2][10];
    int solve(string& s, int idx, bool tight,int cnt){
        if(idx == s.length()) return cnt;

        if(dp[idx][tight][cnt] != -1) return dp[idx][tight][cnt];

        int up = tight == true ? s[idx] - '0' : 9;
        int ans = 0;
        for(int dig = 0; dig <= up; dig++){
            ans += solve(s, idx + 1, (tight && dig == up), dig == 1 ? cnt + 1 : cnt);
        }
        return dp[idx][tight][cnt] = ans;
    }
public:
    int countDigitOne(int n) {
        string s = to_string(n);
        memset(dp, -1, sizeof(dp));
        return solve(s, 0, 1,0);
    }
};