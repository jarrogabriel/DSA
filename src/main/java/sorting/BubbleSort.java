package sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] list = new int[]{1, 2, 3, 4};
        bubbleSort(list);
        System.out.println(Arrays.toString(list));
    }

    public static void bubbleSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            boolean swapped = false;
            for (int j = 1; j < list.length - i; j++) {
                if (list[j] < list[j - 1]) {
                    swapped = true;
                    int temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                }
            }
            if (!swapped) {
                System.out.printf("Breaked proccess at %dst validation, list already ordered%n", i + 1);
                break;
            }
        }
    }
}
