import java.util.*;

public class ArrayCombination {
    public static int[][] findCombinations(int[] nums, int target) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        int n = nums.length;

        // Find all combinations that sum up to the target
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                List<Integer> pair = Arrays.asList(nums[i], nums[j]);
                List<List<Integer>> combinations = map.getOrDefault(sum, new ArrayList<>());
                combinations.add(pair);
                map.put(sum, combinations);
            }
        }

        // Convert the map into a 2D array
        int[][] result = new int[map.get(target).size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = map.get(target).get(i).get(0);
            result[i][1] = map.get(target).get(i).get(1);
        }

        return result;
    }

    public static int[] mergeArray(int[][] combinations) {
        List<Integer> mergedList = new ArrayList<>();

        // Merge the combinations into a single array
        for (int[] combination : combinations) {
            for (int num : combination) {
                mergedList.add(num);
            }
        }

        // Convert the list into an array
        int[] mergedArray = new int[mergedList.size()];
        for (int i = 0; i < mergedArray.length; i++) {
            mergedArray[i] = mergedList.get(i);
        }

        // Sort the array in ascending order
        Arrays.sort(mergedArray);

        return mergedArray;
    }

    public static int[][] findDoubleCombinations(int[] nums, int target) {
        int doubleTarget = 2 * target;
        return findCombinations(nums, doubleTarget);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        int[][] combinations = findCombinations(nums, target);
        System.out.println("First Combination for \"" + target + "\":");
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }

        int[] mergedArray = mergeArray(combinations);
        System.out.println("Merge Into a Single Array:");
        System.out.println(Arrays.toString(mergedArray));

        int[][] doubleCombinations = findDoubleCombinations(mergedArray, target);
        System.out.println("Second Combination for \"" + (2 * target) + "\":");
        for (int[] combination : doubleCombinations) {
            System.out.println(Arrays.toString(combination));
        }
    }
}
