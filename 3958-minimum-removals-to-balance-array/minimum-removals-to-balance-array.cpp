const int N=1024, mask=1023, bshift=10;
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
            uint64_t val = in[i];
            uint64_t x = (val >> shift) & mask;
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
    using ll=long long;
    static int minRemoval(vector<int>& nums_, int k) {
        const int n=nums_.size();
        int* nums=nums_.data();
        if (n<=1024) sort(nums, nums+n);
        else radix_sort(nums, n);
        if ((ll)nums[0]*k>=nums[n-1]) return 0;
        int ans=n;
        for(int l=0, r=0; r<n; r++){
            const int x=nums[r];
            while(l<r && (ll)nums[l]*k<x) l++;
            ans=min(ans, n-(r-l+1));
        }
        return ans;
    }
};