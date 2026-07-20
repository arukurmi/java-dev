package Interview;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class interview {

    public static void main(String[] args) {
            int left = 0;
            int[] fruits = {1,1,3,2,3,4,2,2,4,2,3};
            int n = fruits.length;
            Vector<Integer> v = new Vector<Integer>(10, 5);
            System.out.print(v);
            int maxFruits=0;
            HashMap<Integer,Integer> map = new HashMap<>();

            for(int right = 0; right<n; right++){
                map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
                if(map.size() > 2) {
                    while(map.size() > 2){
                        map.put(fruits[left], map.get(fruits[left]) - 1);
                        if(map.get(fruits[left]) == 0) map.remove(fruits[left]);
                        left++;
                    }
                }
                maxFruits = Math.max(maxFruits, right-left+1);
            }
            System.out.print(maxFruits);
//            return maxFruits;
    }
}
