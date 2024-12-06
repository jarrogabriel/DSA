package sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] list = {4, 7, 3, 1, 2};
        System.out.printf("List before Selection Sort \n%s\n", Arrays.toString(list));
        selectionSort(list);
        System.out.printf("List after Selection Sort\n%s \n", Arrays.toString(list));
    }

    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.printf("Select %d as the %dº lowest number.\n", list[i], i+1);
            int lowestIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                System.out.printf("Is %d greater than %d?\n", list[lowestIndex], list[j]);
                if (list[j] < list[lowestIndex]) {
                    System.out.printf("It is! Select number %d index as the new lowest number index.\n", list[j]);
                    lowestIndex = j;
                }
                else {
                    System.out.print("No.\n");
                }
            }
            System.out.printf("%d, the lowest number, is at the %dº lowest number correct place?\n%s\n", list[lowestIndex], i+1, Arrays.toString(list));
            if (lowestIndex != i) {
                System.out.printf("No, put %d in the %dº lowest number position\n", list[lowestIndex], i+1);
                int temp = list[i];
                list[i] = list[lowestIndex];
                list[lowestIndex] = temp;
                System.out.printf("%s\n\n", Arrays.toString(list));
            }
            else {
                System.out.print("It is already in place!\n\n");
            }
        }
    }
}
