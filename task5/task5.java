import java.util.*;

class AlgorithmsDataStructures2
{
    public static int[] GenerateBBSTArray(int[] a)
    {
        if(a.length == 0) {
            return null;
        }

        Arrays.sort(a);

        int[] BBSTArray = new int[a.length];

        recursionGenerateBBSTArray(a, BBSTArray,0, a.length - 1, 0);

        return BBSTArray;
    }

    private static void recursionGenerateBBSTArray(int[] sortedArray, int [] BBSTArray, int startIndex, int endIndex, int BBSTIndex)
    {
        if(startIndex > endIndex) {
            return;
        }

        int middleIndex = (startIndex + endIndex) / 2;
        BBSTArray[BBSTIndex] = sortedArray[middleIndex];

        recursionGenerateBBSTArray(sortedArray, BBSTArray, startIndex, middleIndex - 1, 2 * BBSTIndex + 1);
        recursionGenerateBBSTArray(sortedArray, BBSTArray, middleIndex + 1, endIndex, 2 * BBSTIndex + 2);
    }

}