class Solution {
public:
    int numRabbits(vector<int>& answers) {
        int n=answers.size();
        int ans=0;
        unordered_map<int,int>mp;
        for(auto x:answers){
            mp[x]++;
        }
        for(auto it:mp){
            if(it.first==0){
                ans+=it.second;
            }
            else if(it.first+1>=it.second){
                ans+=it.first+1;
            }
            else{
                ans+=(it.second);
                if(it.second%(it.first+1)){
                    ans+=(it.first+1)-((it.second)%(it.first+1));
                }
            }
        }
        return ans;
    }
};