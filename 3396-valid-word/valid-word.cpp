class Solution {
public:
    bool isValid(string& word) {
        const int n=word.size();
        if (n<3) return 0;
        unsigned char v[2]={0};
        constexpr unsigned vowels=1|(1<<('e'-'a'))|(1<<('o'-'a'))|(1<<('i'-'a'))|(1<<('u'-'a'));
     //   cout<<vowels<<endl;
        for(char c: word){
            if (!isalpha(c) && !isdigit(c)) return 0;
            if (isalpha(c)){
                unsigned i=(c>'Z')?c-'a':c-'A';
                v[(vowels>>i)&1]++;
            }

        }
    //    cout<<v[0]<<","<<v[1]<<endl;
        return v[0]>0&&v[1]>0;
    }
};