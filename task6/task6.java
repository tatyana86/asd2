import java.util.*;

class BSTNode
{
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int     Level;

    public BSTNode(int key, BSTNode parent)
    {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        if(a.length == 0) {
            return;
        }

        Arrays.sort(a);

        Root = recursionGenerateTree(null, 0, a.length - 1, a, 0);
    }

    private BSTNode recursionGenerateTree(BSTNode currentParent, int startIndex, int endIndex, int[] sortedArray, int level) {
        if(startIndex > endIndex) {
            return null;
        }

        int middleIndex = (startIndex + endIndex) / 2;

        BSTNode newNode = new BSTNode(sortedArray[middleIndex], currentParent);
        newNode.Level = level;
        newNode.LeftChild = recursionGenerateTree(newNode, startIndex, middleIndex - 1, sortedArray, level + 1);
        newNode.RightChild = recursionGenerateTree(newNode,middleIndex + 1, endIndex, sortedArray, level + 1);

        if(newNode.LeftChild != null) {
            assert newNode.LeftChild.NodeKey < newNode.NodeKey : "Tree is not correct";
        }

        if(newNode.RightChild != null) {
            assert newNode.RightChild.NodeKey > newNode.NodeKey : "Tree is not correct";
        }

        return newNode;
    }

    public boolean IsBalanced(BSTNode root_node) {
        return getTreeDepth(root_node) != -1;
    }

    private int getTreeDepth(BSTNode node) {
        if(node == null) {
            return 0;
        }

        int leftDepth = getTreeDepth(node.LeftChild);
        int rightDepth = getTreeDepth(node.RightChild);

        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }
}