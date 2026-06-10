package Practice.basics;

public class SyntaxPractice {

    public static boolean isPrime(int num) {
        for(int i=2; i<num/i; i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    public static int maxInArr(int[] arr) {
        int min = Integer.MIN_VALUE;
        int res = arr[0];
        for(int num : arr) {
            if(res < num) res = num;
        }
        return res;
    }

    public static String reverseString(String s) {
        int n = s.length();
        char[] charArr = s.toCharArray();
        for(int i=0; i<n/2; i++){
            char temp = charArr[i];
            charArr[i] = charArr[n-i-1];
            charArr[n-i-1] = temp;
        }
        return new String(charArr);
    }

    public static int countVowels(String str) {
        int res = 0;

        for(int i=0; i<str.length(); i++) {
            char curr = str.charAt(i);
            if(curr == 'a' || curr == 'e' || curr == 'i' || curr == 'o' || curr == 'u') {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,1,9,2,10};
        String surajName = "Suraj Bhan Mundo";

        System.out.println(isPrime(14));
        System.out.println(maxInArr(arr));
        System.out.println(reverseString(surajName));
        System.out.println(countVowels(surajName));
    }

}
