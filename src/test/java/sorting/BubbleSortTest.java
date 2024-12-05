package sorting;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleSortTest {


    @Test
    void bubbleSortTest() {
        int[] list = {4, 3, 2, 1};
        int[] expectedOrderedList = {1, 2, 3, 4};
        BubbleSort.bubbleSort(list);
        assertArrayEquals(expectedOrderedList, list);
    }

    @Test
    void testBreakedSortProccess() {
        int[] orderedList = {1, 2, 3, 4};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        BubbleSort.bubbleSort(orderedList);
        String output = outputStream.toString();
        assertTrue(output.contains("Since there were no swaps last time, list is already ordered, broke the proccess at 1º validation "));
    }
}
