class Solution {
public:
    bool checkValidString(string s) {
        int mini=0;
        int maxi=0;
        for(int i=0;i<s.length();i++)
        {
            if(s[i]=='(')
            {
                mini+=1;
                maxi+=1;
            }
            else if(s[i]==')')
            {
                mini-=1;
                maxi-=1;
            }
            else//for * case we can do -1(consider as ')'),
            //0(conside as empty string),1(consider as '(') out of these all we take the range as mini-1,maxi+1 
            {
                mini-=1;
                maxi+=1;
            }
            if(mini<0)
            {
                mini=0;
            }
            if(maxi<0)
            {
                return false;
            }
        }
        return mini==0;
    }
};