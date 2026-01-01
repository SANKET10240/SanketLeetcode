class Solution {
public:
    bool isAll9(vector<int>& digits){
        bool ans=1;
        for(int d: digits)
            ans=( ans & d==9);
        return ans;
    }

    vector<int> plusOne(vector<int>& digits) {
        int n=digits.size();
        if (isAll9(digits))
        {
            digits.assign(n+1, 0);
            digits[0]=1;
            return digits;
        }
        else
        {
            digits[n-1]++;
            if (digits[n-1]!=10) return digits;
            else {
                digits.pop_back();
                digits=plusOne(digits);
                digits.push_back(0);
                return digits;
            }
        }
    }
};