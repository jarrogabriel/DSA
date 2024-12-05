package sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] list = new int[]{98, 26, 37, 45};
        System.out.printf("List before sorting \n%s \n", Arrays.toString(list));
        bubbleSort(list);
        System.out.printf("List after Bubble Sort %s \n", Arrays.toString(list));
    }

    public static void bubbleSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            boolean swapped = false;
            for (int j = 1; j < list.length - i; j++) {
                System.out.printf("Is %d greater than %d? \n", list[j - 1], list[j]);
                if (list[j] < list[j - 1]) {
                    System.out.printf("It is, swap their places in array \n%s \n", Arrays.toString(list));
                    swapped = true;
                    int temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                    System.out.printf("%s \n", Arrays.toString(list));
                }
                if (!swapped)
                    System.out.println("No");
            }

            System.out.printf("%d is already at the correct position, we're going to check from %d to %d now \n", list[list.length - i - 1], list[0], list[list.length - (i + 1) - 1]);

            if (!swapped) {
                System.out.printf("Since there were no swaps last time, list is already ordered, broke the proccess at %dº validation \n", i + 1);
                break;
            }
        }
    }
}
