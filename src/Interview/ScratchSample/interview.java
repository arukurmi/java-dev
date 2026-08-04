package Interview;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class interview {
    /*
        Input:
        foorgeeksskeegfor
        Output:
        geeksskeeg
        Input:
        BBABCBAB
        Output:
        BABCBAB
     */

    // Time: O(n^2)
    // Space: O(1)
    public static String findLongestPalindromicSubstring(String inputString) {
        String longestPalindrome = "";
        int lenthOfInputString = inputString.length();

        for(int itr=0; itr<lenthOfInputString; itr++){
            int leftPtr = itr;
            int rightPtr = itr+1;
            if(rightPtr+1 < lenthOfInputString && inputString.charAt(leftPtr) == inputString.charAt(rightPtr+1)) rightPtr++;
            while (leftPtr >= 0 && rightPtr < lenthOfInputString && inputString.charAt(leftPtr) == inputString.charAt(rightPtr)) {
                leftPtr--;
                rightPtr++;
            }
            leftPtr++;
            rightPtr--;
            if (longestPalindrome.length() < rightPtr + 1 - leftPtr) {
                longestPalindrome = inputString.substring(leftPtr, rightPtr + 1);
            }
        }

        return longestPalindrome;
    }

    public static void main(String[] args) {
        String inputString = "BBABCBAB";
        String res = findLongestPalindromicSubstring(inputString);
        System.out.println(res);
    }
}
