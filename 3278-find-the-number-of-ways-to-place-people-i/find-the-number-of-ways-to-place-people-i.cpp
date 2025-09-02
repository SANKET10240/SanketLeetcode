class Solution {
public:
    static bool comp(vector<int>& a, vector<int>& b) {
    if (a[0] == b[0]) {
        return a[1] > b[1];
    }
    return a[0] < b[0];
}
    int numberOfPairs(vector<vector<int>>& points) {
        sort(points.begin(),points.end(),comp);
        int ans=0;
        int n=points.size();
        for(int i=0;i<n;i++){
            int x1=points[i][0], y1=points[i][1];
            int miny=INT_MIN;
            for(int j=i+1;j<n;j++){
                int x2=points[j][0], y2=points[j][1];
                if(y2<=y1){
                    if(y2>miny){
                        ans++;
                        miny=y2;
                    }
                }
            }
        }
        return ans;
    }
};