package sorting;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] list = {5710, 5710, 9349, 9349, 4070, 86, 1621, 2050, 1904};
        radixSort(list);
    }

    public static void radixSort(int[] list) {
        int max = Arrays.stream(list).max().getAsInt();

        //log purposes only
        int maxNumberSize = Integer.toString(max).length();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            System.out.printf("Sorting list by %dº digit \n %s \n \n", maxNumberSize - (int) Math.log10(exp), Arrays.toString(list));
            countDigit(list, exp);
            System.out.printf("Array after being sorted by %d \n %s \n  \n", exp,Arrays.toString(list));
        }
        System.out.printf("Array after RadixSort \n %s", Arrays.toString(list));
    }


    private static void countDigit(int[] list, int exp) {

        int[] output = new int[list.length];
        int[] count = new int[10];

        for (int i = 0; i < list.length; i++) {
            int digit = (list[i] / exp) % 10;
            count[digit]++;
            System.out.printf("Found %d -> add to count digit %d -> Count Digit %d = %d \n", digit, digit, digit, count[digit]);
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = list.length - 1; i >= 0; i--) {
            int digit = (list[i] / exp) % 10;
            output[count[digit] - 1] = list[i];
            count[digit]--;
            System.out.printf("Number %d with digit %d goes into where number %d slot is reserved in output array \n", list[i],digit, digit);
            System.out.printf("Output array %s \n \n", Arrays.toString(output));
        }
        System.arraycopy(output, 0, list, 0, list.length);
    }
}
