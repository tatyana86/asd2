import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        int tree_size = (int) Math.pow(2, depth + 1) - 1;
        Tree = new Integer[tree_size];
        for(int i = 0; i < tree_size; i ++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key)
    {
        if(Tree[0] != null) {
            return recursionFindKeyIndex(key, 0);
        }

        return 0;
    }

    private Integer recursionFindKeyIndex(int key, int index) {
        if(index >= Tree.length) {
            return null;
        }

        if(Tree[index] == null) {
            return -index;
        }

        if(Tree[index] == key) {
            return index;
        }

        if(Tree[index] > key) {
            return recursionFindKeyIndex(key, 2 * index + 1);
        }

        if(Tree[index] < key) {
            return recursionFindKeyIndex(key, 2 * index + 2);
        }

        return null;
    }

    public int AddKey(int key)
    {
        Integer foundIndex = FindKeyIndex(key);

        if(foundIndex == null) {
            return -1;
        }

        if(foundIndex >= 0) {
            return foundIndex;
        }
        if(foundIndex < 0) {
            return -foundIndex;
        }
        return -1;
    }
}