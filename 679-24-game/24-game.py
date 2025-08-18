import itertools
import math

class Solution(object):
    def judgePoint24(self, cards):
        """
        :type cards: List[int]
        :rtype: bool
        """
        def isclose(a, b, rel_tol=1e-6):
            return abs(a - b) <= rel_tol

        def helper(nums):
            if len(nums) == 1:
                return isclose(nums[0], 24)
            for i in range(len(nums)):
                for j in range(len(nums)):
                    if i != j:
                        rest = [nums[k] for k in range(len(nums)) if k != i and k != j]
                        a, b = nums[i], nums[j]
                        results = [a + b, a - b, b - a, a * b]
                        if b != 0:
                            results.append(a * 1.0 / b)
                        if a != 0:
                            results.append(b * 1.0 / a)
                        for op in results:
                            if helper(rest + [op]):
                                return True
            return False
        
        return helper([float(x) for x in cards])