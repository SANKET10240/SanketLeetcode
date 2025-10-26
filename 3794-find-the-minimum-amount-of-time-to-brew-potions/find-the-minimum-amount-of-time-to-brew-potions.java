class Solution {
    public long minTime(int[] skill, int[] mana) {

       int n = skill.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + skill[i];
        }
        
        int[] suffix = new int[n];
        int len1 = 0;
        suffix[len1++] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (skill[i] > skill[suffix[len1 - 1]]) 
                suffix[len1++] = i;
        }

        int[] prefix = new int[n];
        int len2 = 0;
        prefix[len2++] = 0;
        for (int i = 1; i < n; i++) {
            if (skill[i] > skill[prefix[len2 - 1]]) {
                prefix[len2++] = i;
            }
        }

        int m = mana.length;
        long start = 0;
        for (int j = 1; j < m; j++) {
            int[] indices = mana[j - 1] < mana[j] ? prefix : suffix;
            int len = mana[j - 1] < mana[j] ? len2 : len1;
            long max = 0;
            for (int k = 0; k < len; k++) {
                int i = indices[k];
                max = Math.max(max, (long) mana[j - 1] * s[i + 1] - (long) mana[j] * s[i]);
            }
            start += max;
        }
        return start + (long) mana[m - 1] * s[n];   
    }
}