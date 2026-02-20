using System;
using System.Collections.Generic;

public class Solution
{
    public string MakeLargestSpecial(string InputString)
    {
        List<string> SpecialSubstrings = new List<string>();
        int BalanceCount = 0;
        int StartIndex = 0;

        for (int Index = 0; Index < InputString.Length; Index++)
        {
            if (InputString[Index] == '1')
                BalanceCount++;
            else
                BalanceCount--;

            if (BalanceCount == 0)
            {
                string InnerSubstring = InputString.Substring(StartIndex + 1, Index - StartIndex - 1);
                InnerSubstring = MakeLargestSpecial(InnerSubstring);
                SpecialSubstrings.Add("1" + InnerSubstring + "0");
                StartIndex = Index + 1;
            }
        }

        SpecialSubstrings.Sort((First, Second) => string.Compare(Second, First, StringComparison.Ordinal));

        return string.Join("", SpecialSubstrings);
    }
}