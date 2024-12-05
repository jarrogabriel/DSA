package sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RadixSortTest {


    @Test
    void radixSortTest() {
        int[] list = {5710, 5710, 9349, 9349, 4070, 86, 1621, 2050, 1904};
        int[] expectedSortedList = {86, 1621, 1904, 2050, 4070, 5710, 5710, 9349, 9349};
        RadixSort.radixSort(list);
        assertArrayEquals(expectedSortedList, list);
    }
}
