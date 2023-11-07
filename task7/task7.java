import java.util.*;

class Heap
{
    public int [] HeapArray;

    public Heap() { HeapArray = null; }

    public void MakeHeap(int[] a, int depth)
    {
        int heap_size = (int) Math.pow(2, depth + 1) - 1;
        HeapArray = new int[heap_size];
        for(int i = 0; i < a.length; i ++) {
            Add(a[i]);
        }
    }

    public int GetMax()
    {
        if(HeapArray[0] == 0) {
            return -1;
        }

        int maxKey = HeapArray[0];

        int indexOfLastExistNode = -1;
        for(int i = HeapArray.length - 1; i >= 0; i --) {
            if(HeapArray[i] != 0) {
                indexOfLastExistNode = i;
                break;
            }
        }

        if(indexOfLastExistNode == 0) {
            HeapArray[0] = 0;
            return maxKey;
        }

        HeapArray[0] = HeapArray[indexOfLastExistNode];
        HeapArray[indexOfLastExistNode] = 0;

        siftDown(0);

        return maxKey;
    }

    private void siftDown(int index) {
        if(index >= HeapArray.length) {
            return;
        }

        int indexOfMaxChild = getIndexOfMaxKeyChild(index);

        if(indexOfMaxChild == -1) {
            return;
        }

        int copyKey = HeapArray[indexOfMaxChild];
        HeapArray[indexOfMaxChild] = HeapArray[index];
        HeapArray[index] = copyKey;

        siftDown(indexOfMaxChild);
    }

    private int getIndexOfMaxKeyChild(int indexParent) {
        boolean existLeftChild = 2 * indexParent + 1 < HeapArray.length && HeapArray[2 * indexParent + 1] != 0;
        boolean existRightChild = 2 * indexParent + 2 < HeapArray.length && HeapArray[2 * indexParent + 2] != 0;

        if(! existLeftChild && ! existRightChild) {
            return -1;
        }

        if(existLeftChild && existRightChild && HeapArray[indexParent] > HeapArray[2 * indexParent + 1] &&  HeapArray[indexParent] > HeapArray[2 * indexParent + 2]) {
            return -1;
        }

        if(existLeftChild && existRightChild && HeapArray[2 * indexParent + 1] > HeapArray[2 * indexParent + 2]) {
            return 2 * indexParent + 1;
        }
        if(existLeftChild && existRightChild && HeapArray[2 * indexParent + 2] > HeapArray[2 * indexParent + 1]) {
            return  2 * indexParent + 2;
        }
        if(existLeftChild && HeapArray[indexParent] > HeapArray[2 * indexParent + 1]) {
            return 2 * indexParent + 1;
        }
        if(existLeftChild && HeapArray[indexParent] > HeapArray[2 * indexParent + 2]) {
            return 2 * indexParent + 2;
        }

        return -1;
    }

    public boolean Add(int key)
    {
        int firstFreeIndex = -1;
        for(int i = 0; i < HeapArray.length; i ++) {
            if(HeapArray[i] == 0) {
                firstFreeIndex = i;
                HeapArray[i] = key;
                break;
            }
        }

        if(firstFreeIndex == -1) {
            return false;
        }

        if(firstFreeIndex == 0) {
            return true;
        }

        recursionAdd(firstFreeIndex);

        return true;
    }

    private void recursionAdd(int index)
    {
        int parentIndex = (index - 1) / 2;

        if(HeapArray[parentIndex] > HeapArray[index]) {
            return;
        }

        int copyKey = HeapArray[index];
        HeapArray[index] = HeapArray[parentIndex];
        HeapArray[parentIndex] = copyKey;

        if(parentIndex == 0) {
            return;
        }

        recursionAdd(parentIndex);
    }
}