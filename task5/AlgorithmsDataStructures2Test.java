import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {

    int[] testArray = {12, 4, 9, 3, 1, 7, 13, 11, 8, 2, 10, 6, 15, 14, 5};
    int[] equalArray = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

    @Test
    public void simpleTest() {
        int[] answer = AlgorithmsDataStructures2.GenerateBBSTArray(testArray);

        for(int i = 0; i < testArray.length; i ++) {
            assertEquals(equalArray[i], answer[i]);
        }
    }

    @Test
    public void testWithEmptyArray() {
        int[] emptyArray = new int[0];
        int[] answer = AlgorithmsDataStructures2.GenerateBBSTArray(emptyArray);

        assertNull(answer);
    }

}