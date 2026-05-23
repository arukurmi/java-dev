package basics;
import java.util.*;

public class CollectionPractice {

    /*
     * Problem 1: Count Frequency of Elements
     *
     * Given an integer array, count how many times each number appears.
     *
     * Input:
     * int[] arr = {1, 2, 2, 3, 3, 3, 4};
     *
     * Expected Output:
     * {1=1, 2=2, 3=3, 4=1}
     *
     * Requirements:
     * - Use HashMap<Integer, Integer>
     * - Return the frequency map
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static Map<Integer, Integer> countFrequency(int[] arr)
     */

    public static Map<Integer, Integer> countFrequency(int[] arr){
        Map<Integer, Integer> res = new HashMap<>();
        for(int num : arr){
            res.compute(num, (key, freq) -> (freq != null) ? freq + 1 : 1);
        }
        return res;
    }


    /*
     * Problem 2: Check If Array Has Duplicate
     *
     * Given an integer array, return true if any number appears more than once.
     * Otherwise, return false.
     *
     * Input:
     * int[] arr = {1, 2, 3, 4, 2};
     *
     * Expected Output:
     * true
     *
     * Input:
     * int[] arr = {1, 2, 3, 4};
     *
     * Expected Output:
     * false
     *
     * Requirements:
     * - Use HashSet<Integer>
     * - Stop early when duplicate is found
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static boolean hasDuplicate(int[] arr)
     */
    public static boolean hasDuplicate(int[] arr){
        Set<Integer> s = new HashSet<>();

        for(int num: arr){
            if(s.contains(num)) return true;
            s.add(num);
        }
        return false;
    }

    /*
     * Problem 3: Return Only Even Numbers
     *
     * Given an integer array, return a list containing only even numbers.
     *
     * Input:
     * int[] arr = {1, 2, 3, 4, 5, 6};
     *
     * Expected Output:
     * [2, 4, 6]
     *
     * Requirements:
     * - Use ArrayList<Integer>
     * - Preserve original order
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static List<Integer> getEvenNumbers(int[] arr)
     */

    public static List<Integer> getEvenNumbers(int[] arr) {
        List<Integer> resultList = new ArrayList<>();
        for(int num: arr){
            if(num%2 == 0) resultList.add(num);
        }

        return resultList;
    }


    /*
     * Problem 4: First Non-Repeating Character
     *
     * Given a string, return the first character that appears exactly once.
     * If no such character exists, return '#'.
     *
     * Input:
     * String s = "leetcode";
     *
     * Expected Output:
     * l
     *
     * Input:
     * String s = "loveleetcode";
     *
     * Expected Output:
     * v
     *
     * Input:
     * String s = "aaab";
     *
     * Expected Output:
     * #
     *
     * Requirements:
     * - Use HashMap<Character, Integer>
     * - First pass: count frequency
     * - Second pass: find first character with frequency 1
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static char firstNonRepeatingChar(String s)
     */

    public static char firstNonRepeatingChar(String s){
        Map<Character, Integer> charMap = new HashMap<>();
        char[] charArr = s.toCharArray();
        for(char ch: charArr){
            charMap.compute(ch, (k, freq) -> (freq != null) ? freq + 1 : 1);
        }

        for(char ch: charArr){
            if(charMap.get(ch) == 1){
                return ch;
            }
        }
        return '#';
    }


    /*
     * Problem 5: Two Sum
     *
     * Given an integer array nums and an integer target, return indices of two numbers
     * such that they add up to target.
     *
     * Input:
     * int[] nums = {2, 7, 11, 15};
     * int target = 9;
     *
     * Expected Output:
     * [0, 1]
     *
     * Requirements:
     * - Use HashMap<Integer, Integer>
     * - Return int[]{-1, -1} if no answer exists
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static int[] twoSum(int[] nums, int target)
     */

    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> valueToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int needed = target - nums[i];

            if (valueToIndex.containsKey(needed)) {
                return new int[]{valueToIndex.get(needed), i};
            }

            valueToIndex.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }


    /*
     * Problem 6: Valid Parentheses
     *
     * Given a string containing only '(', ')', '{', '}', '[' and ']',
     * return true if the brackets are valid.
     *
     * A string is valid if:
     * - Every opening bracket has a matching closing bracket
     * - Brackets close in the correct order
     *
     * Input:
     * String s = "()[]{}";
     *
     * Expected Output:
     * true
     *
     * Input:
     * String s = "(]";
     *
     * Expected Output:
     * false
     *
     * Input:
     * String s = "([{}])";
     *
     * Expected Output:
     * true
     *
     * Requirements:
     * - Use Deque<Character> as a stack
     * - Do not use legacy Stack class
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static boolean isValidParentheses(String s)
     */
    public static boolean isValidParentheses(String s){
        Deque<Character> dq = new ArrayDeque<>();
        char[] charArr = s.toCharArray();

        for(char ch: charArr) {
            if(!dq.isEmpty()){
                if((dq.peek() == '{' && ch == '}') || (dq.peek() == '(' && ch == ')') || (dq.peek() == '[' && ch == ']'))
                    dq.remove();
            } else {
                dq.push(ch);
            }
        }

        return dq.isEmpty();
    }


    /*
     * Problem 7: Top K Frequent Elements
     *
     * Given an integer array and an integer k, return the k most frequent elements.
     *
     * Input:
     * int[] nums = {1, 1, 1, 2, 2, 3};
     * int k = 2;
     *
     * Expected Output:
     * [1, 2]
     *
     * Requirements:
     * - Use HashMap<Integer, Integer> for frequency
     * - Use PriorityQueue<Integer> or PriorityQueue<int[]>
     * - Return List<Integer>
     * - Time complexity target: O(n log k)
     *
     * Method signature:
     * public static List<Integer> topKFrequent(int[] nums, int k)
     */

    public static List<Integer> topKFrequent(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue <Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(freqMap.get(a) , freqMap.get(b)) );

        for(int num : freqMap.keySet()){
            minHeap.offer(num);
            if(minHeap.size() > k) minHeap.poll();
        }

        List<Integer> res = new ArrayList<>();
        while(!minHeap.isEmpty()){
            res.add(minHeap.poll());
        }
        Collections.reverse(res);
        return res;
    }

    /*
     * Problem 8: Group Words By Length
     *
     * Given an array of words, group them by their length.
     *
     * Input:
     * String[] words = {"hi", "java", "go", "node", "cpp"};
     *
     * Expected Output:
     * {
     *   2=[hi, go],
     *   3=[cpp],
     *   4=[java, node]
     * }
     *
     * Requirements:
     * - Use HashMap<Integer, List<String>>
     * - Preserve insertion order inside each list
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static Map<Integer, List<String>> groupWordsByLength(String[] words)
     */
    public static Map<Integer, List<String>> groupWordsByLength(String[] words){
        Map<Integer, List<String>> res = new HashMap<>();
        for(String str: words){
            res.computeIfAbsent(str.length(), k-> new ArrayList<>()).add(str);
        }
        return res;
    }


    /*
     * Problem 9: Remove Duplicates While Preserving Order
     *
     * Given an integer array, return a list with duplicates removed,
     * while keeping the first occurrence order.
     *
     * Input:
     * int[] arr = {4, 5, 4, 6, 5, 7};
     *
     * Expected Output:
     * [4, 5, 6, 7]
     *
     * Requirements:
     * - Use HashSet<Integer> to track seen numbers
     * - Use ArrayList<Integer> for result
     * - Preserve order
     * - Time complexity should be O(n)
     *
     * Method signature:
     * public static List<Integer> removeDuplicatesPreserveOrder(int[] arr)
     */

    public static List<Integer> removeDuplicatesPreserveOrder(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for(int num : arr){
            if(seen.add(num)) result.add(num);
        }
        return result;
    }


    /*
     * Problem 10: Main Method Testing
     *
     * In the main method:
     * - Create test inputs for all the above methods
     * - Print outputs clearly with labels
     * - Use Arrays.toString() when printing int[]
     * - Print maps/lists directly
     *
     * Example:
     * System.out.println("Frequency Map: " + countFrequency(arr));
     * System.out.println("Two Sum: " + Arrays.toString(twoSum(nums, target)));
     *
     * Requirements:
     * - Import java.util.*
     * - Import java.util.Arrays if not using java.util.*
     * - main method signature must be:
     *   public static void main(String[] args)
     */

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("        JAVA COLLECTION PRACTICE TEST RUNNER       ");
        System.out.println("==================================================");

        System.out.println();

        // Problem 1: Count Frequency
        int[] frequencyInput = {1, 2, 2, 3, 3, 3, 4};
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 1: Count Frequency of Elements");
        System.out.println("Input:    " + Arrays.toString(frequencyInput));
        System.out.println("Expected: {1=1, 2=2, 3=3, 4=1}");
        System.out.println("Actual:   " + countFrequency(frequencyInput));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 2: Has Duplicate - true case
        int[] duplicateInputTrue = {1, 2, 3, 4, 2};
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 2A: Check If Array Has Duplicate");
        System.out.println("Input:    " + Arrays.toString(duplicateInputTrue));
        System.out.println("Expected: true");
        System.out.println("Actual:   " + hasDuplicate(duplicateInputTrue));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 2: Has Duplicate - false case
        int[] duplicateInputFalse = {1, 2, 3, 4};
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 2B: Check If Array Has Duplicate");
        System.out.println("Input:    " + Arrays.toString(duplicateInputFalse));
        System.out.println("Expected: false");
        System.out.println("Actual:   " + hasDuplicate(duplicateInputFalse));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 3: Even Numbers
        int[] evenInput = {1, 2, 3, 4, 5, 6};
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 3: Return Only Even Numbers");
        System.out.println("Input:    " + Arrays.toString(evenInput));
        System.out.println("Expected: [2, 4, 6]");
        System.out.println("Actual:   " + getEvenNumbers(evenInput));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 4: First Non-Repeating Character
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 4A: First Non-Repeating Character");
        System.out.println("Input:    leetcode");
        System.out.println("Expected: l");
        System.out.println("Actual:   " + firstNonRepeatingChar("leetcode"));
        System.out.println("--------------------------------------------------");

        System.out.println();

        System.out.println("--------------------------------------------------");
        System.out.println("Problem 4B: First Non-Repeating Character");
        System.out.println("Input:    loveleetcode");
        System.out.println("Expected: v");
        System.out.println("Actual:   " + firstNonRepeatingChar("loveleetcode"));
        System.out.println("--------------------------------------------------");

        System.out.println();

        System.out.println("--------------------------------------------------");
        System.out.println("Problem 4C: First Non-Repeating Character");
        System.out.println("Input:    aabb");
        System.out.println("Expected: #");
        System.out.println("Actual:   " + firstNonRepeatingChar("aabb"));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 5: Two Sum
        int[] twoSumInput = {2, 7, 11, 15};
        int target = 9;
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 5: Two Sum");
        System.out.println("Input:    nums = " + Arrays.toString(twoSumInput) + ", target = " + target);
        System.out.println("Expected: [0, 1]");
        System.out.println("Actual:   " + Arrays.toString(twoSum(twoSumInput, target)));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 6: Valid Parentheses
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 6A: Valid Parentheses");
        System.out.println("Input:    ()[]{}");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + isValidParentheses("()[]{}"));
        System.out.println("--------------------------------------------------");

        System.out.println();

        System.out.println("--------------------------------------------------");
        System.out.println("Problem 6B: Valid Parentheses");
        System.out.println("Input:    (]");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + isValidParentheses("(]"));
        System.out.println("--------------------------------------------------");

        System.out.println();

        System.out.println("--------------------------------------------------");
        System.out.println("Problem 6C: Valid Parentheses");
        System.out.println("Input:    ([{}])");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + isValidParentheses("([{}])"));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 7: Top K Frequent
        int[] topKInput = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 7: Top K Frequent Elements");
        System.out.println("Input:    nums = " + Arrays.toString(topKInput) + ", k = " + k);
        System.out.println("Expected: [1, 2]");
        System.out.println("Actual:   " + topKFrequent(topKInput, k));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 8: Group Words By Length
        String[] words = {"hi", "java", "go", "node", "cpp"};
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 8: Group Words By Length");
        System.out.println("Input:    " + Arrays.toString(words));
        System.out.println("Expected: {2=[hi, go], 3=[cpp], 4=[java, node]}");
        System.out.println("Actual:   " + groupWordsByLength(words));
        System.out.println("--------------------------------------------------");

        System.out.println();

        // Problem 9: Remove Duplicates While Preserving Order
        int[] removeDuplicateInput = {4, 5, 4, 6, 5, 7};
        System.out.println("--------------------------------------------------");
        System.out.println("Problem 9: Remove Duplicates While Preserving Order");
        System.out.println("Input:    " + Arrays.toString(removeDuplicateInput));
        System.out.println("Expected: [4, 5, 6, 7]");
        System.out.println("Actual:   " + removeDuplicatesPreserveOrder(removeDuplicateInput));
        System.out.println("--------------------------------------------------");

        System.out.println();

        System.out.println("==================================================");
        System.out.println("              TEST RUN COMPLETED                  ");
        System.out.println("==================================================");
    }
}