class Solution {
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        // Sort potions for binary search
        sort(potions.begin(), potions.end());
        int m = potions.size();
        vector<int> result;

        // For each spell, use binary search to find the number of successful pairs
        for (int spell : spells) {
            long long minPotion = (success + spell - 1) / spell; // Ceiling of success / spell
            // Find the first potion >= minPotion using binary search
            int index = lower_bound(potions.begin(), potions.end(), minPotion) - potions.begin();
            result.push_back(m - index); // Successful pairs count
        }

        return result;
    }
};
