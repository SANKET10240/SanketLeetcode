class Solution {
public:
    int minimizeMax(vector<int>& nums, int p) {
        sort(nums.begin(), nums.end());
        return binarySearch(nums, p);
    }

    //////////////////////////////////////////////////////
    bool canFormPairs(const vector<int>& nums, int d, int p) {
        int c = 0;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums[i + 1] - nums[i] <= d) {
                c++;
                i++;
            }
            if (c >= p)
                return true;
        }
        return false;
    }

    //////////////////////////////////////////////////////
    int binarySearch(const vector<int>& nums, int p) {
        int L = 0, H = nums.back() - nums.front();
        while (L < H) {
            int M = L + ((H - L) >> 1);
            if (canFormPairs(nums, M, p)) {
                H = M;
            } else {
                L = M + 1;
            }
        }
        return L;
    }
};