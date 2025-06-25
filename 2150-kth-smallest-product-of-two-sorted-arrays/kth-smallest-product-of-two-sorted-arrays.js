/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number} k
 * @return {number}
 */
var kthSmallestProduct = function(nums1, nums2, k) {
    function separate(A) {
        const A1 = [];
        const A2 = [];
        for (const a of A) {
            if (a < 0) {
                A1.push(-a);
            } else {
                A2.push(a);
            }
        }
        A1.reverse();
        return [A1, A2];
    }

    function numProductNoGreaterThan(A, B, m) {
        let count = 0;
        let j = B.length - 1;
        for (const a of A) {
            while (j >= 0 && a * B[j] > m) {
                j--;
            }
            count += j + 1;
        }
        return count;
    }

    let [A1, A2] = separate(nums1);
    let [B1, B2] = separate(nums2);

    let negCount = A1.length * B2.length + A2.length * B1.length;
    let sign = 1;

    if (k > negCount) {
        k -= negCount;
    } else {
        k = negCount - k + 1;
        sign = -1;
        [B1, B2] = [B2, B1];
    }

    let l = 0;
    let r = 1e10;

    while (l < r) {
        let m = Math.floor((l + r) / 2);
        if (
            numProductNoGreaterThan(A1, B1, m) +
            numProductNoGreaterThan(A2, B2, m) >= k
        ) {
            r = m;
        } else {
            l = m + 1;
        }
    }

    return sign * l;
};