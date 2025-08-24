package challenges.LeetCode.LC2357_MakeArrayZero;

import java.util.HashSet;
import java.util.Set;

public class MakeArrayZero {

    public static void main(String[] args) {
        int[] nums = {1, 0, 5, 3, 5};
        System.out.println(countOperations(nums));
    }

    public static int countOperations(int[] nums) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int num : nums) {
            if (num != 0) {
                uniqueNumbers.add(num);
            }
        }
        return uniqueNumbers.size();
    }
}
