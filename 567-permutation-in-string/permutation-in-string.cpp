class Solution {
public:

    bool isFreqSame(vector<int>& freq1, vector<int>& freq2){
        for(int i = 0; i < 26; i++){
            if(freq1[i] != freq2[i]){
                return false;
            }
        }
        return true;
    }
    bool checkInclusion(string s1, string s2) {
     int m = s1.length();
     int n = s2.length();
     if(m > n){
        return false;
     }   

     vector<int> freq1(26,0), freq2(26,0);

     for(int i = 0; i < m; i++){
        freq1[s1[i] - 'a']++;
        freq2[s2[i] - 'a']++;
     }

     if(isFreqSame(freq1, freq2)){
        return true;
     }

     for(int i = m; i < n; i++){
        freq2[s2[i] - 'a']++;
        freq2[s2[i - m] - 'a']--;

        if(isFreqSame(freq1, freq2)){
            return true;
        }
     }
     return false;
    }
};