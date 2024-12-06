package sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SelectionSortTest {


    @Test
    void selectionSortTest() {
        int[] list = {4, 7, 3, 1, 2};
        SelectionSort.selectionSort(list);
        int[] expectedList = {1, 2, 3, 4, 7};
        assertArrayEquals(expectedList, list);
    }
}
