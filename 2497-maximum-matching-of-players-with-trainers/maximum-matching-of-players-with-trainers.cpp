const int N=256, mask=255, bshift=8;
int freq[N];

void radix_sort(int* nums, int n) {
    int* output = (int*)alloca(n * sizeof(int));  // buffer
    unsigned M=*max_element(nums, nums+n);
    const int Mround = max(1, int(31-countl_zero(M)+bshift)/bshift);
    int* in=nums;
    int* out=output;
    bool swapped=0;

    for (int round=0; round<Mround; round++) {
        const int shift=round*bshift;
        memset(freq, 0, sizeof(freq));

        for (int i = 0; i < n; i++)
            freq[(in[i] >> shift) & mask]++;

        partial_sum(freq, freq+N, freq);

        for (int i = n - 1; i >= 0; i--) {
            int val = in[i];
            int x = (val >> shift) & mask;
            out[--freq[x]] = val;
        }

        swap(in, out);
        swapped = !swapped;
    }

    // If needed, copy back
    if (swapped)
        memcpy(nums, in, n * sizeof(int));
}

class Solution {
public:
    static int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) 
    {
        const int n=players.size(), m=trainers.size();
        int* p=players.data(), *t=trainers.data();
        radix_sort(p, n);
        radix_sort(t, m);
        int match=0;
        for (int i=0, j=0; i<n && j<m; i++){
            const int pi=p[i];
            while(j<m && t[j]<pi) j++;
            if (j<m) {
                j++;
                match++;
            }
        } 
        return match;
    }
};