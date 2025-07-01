#include <unordered_map>

class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        unordered_map<char, int> mapS1;
        for (char c:s1) mapS1[c]++;
        for (int i=0; i<s2.size(); i++) { 
            unordered_map<char, int> mapS2;
            if (mapS1.find(s2[i])!=mapS1.end() && i+s1.size()-1<s2.size()) { 
                for (int j=i; j<i+s1.size(); j++) mapS2[s2[j]]++;
                if (mapS2==mapS1) return true;
            }
        }
        return false;
    }
};