import java.io.*;
import java.util.*;

class BSTNode<T>
{
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T>
{
    public BSTNode<T> Node;
    public boolean NodeHasKey;
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }

    public BSTFind(BSTNode<T> node, boolean nodeHasKey) {
        Node = node;
        NodeHasKey = nodeHasKey;
    }

    public BSTFind(BSTNode<T> node, boolean nodeHasKey, boolean toLeft) {
        Node = node;
        NodeHasKey = nodeHasKey;
        ToLeft = toLeft;
    }
}

class BST<T>
{
    BSTNode<T> Root;

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        BSTFind<T> foundNode = new BSTFind<>();

        if(Root != null) {
            foundNode = recursionFindNodeByKey(key, Root);
        }

        return foundNode;
    }

    private BSTFind<T> recursionFindNodeByKey(int key, BSTNode<T> node)
    {
        if(node.NodeKey == key) {
            return new BSTFind<>(node, true);
        }
        if(node.NodeKey > key && node.LeftChild == null) {
            return new BSTFind<>(node, false, true);
        }
        if(node.NodeKey < key && node.RightChild == null) {
            return  new BSTFind<>(node, false,false);
        }

        BSTFind<T> foundNode = null;

        if(node.NodeKey > key) {
            foundNode = recursionFindNodeByKey(key, node.LeftChild);
        }

        if(node.NodeKey < key) {
            foundNode = recursionFindNodeByKey(key, node.RightChild);
        }

        return foundNode;
    }

    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> foundNode = FindNodeByKey(key);

        if(foundNode.Node == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }

        if(foundNode.NodeHasKey) {
            return false;
        }

        if(foundNode.ToLeft) {
            foundNode.Node.LeftChild = new BSTNode<>(key, val, foundNode.Node);
            return true;
        }

        foundNode.Node.RightChild = new BSTNode<>(key, val, foundNode.Node);
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if(FindMax) {
            return findMax(FromNode);
        }
        return findMin(FromNode);
    }

    private BSTNode<T> findMax(BSTNode<T> node)
    {
        if(node.RightChild == null) {
            return node;
        }
        return findMax(node.RightChild);
    }

    private BSTNode<T> findMin(BSTNode<T> node) {
        if(node.LeftChild == null) {
            return node;
        }
        return findMin(node.LeftChild);
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> foundNode = FindNodeByKey(key);

        if(! foundNode.NodeHasKey) {
            return false;
        }

        // case, when node doesn't have children (node is leaf)
        boolean isLeftLeaf = foundNode.Node.LeftChild == null && foundNode.Node.RightChild == null && foundNode.Node.NodeKey == foundNode.Node.Parent.LeftChild.NodeKey;
        boolean isRightLeaf = foundNode.Node.LeftChild == null && foundNode.Node.RightChild == null && foundNode.Node.NodeKey == foundNode.Node.Parent.RightChild.NodeKey;

        if(isLeftLeaf) {
            foundNode.Node.Parent.LeftChild = null;
            foundNode.Node.Parent = null;
            return true;
        }

        if(isRightLeaf) {
            foundNode.Node.Parent.RightChild = null;
            foundNode.Node.Parent = null;
            return true;
        }

        // case, when foundNode hasn't right child
        boolean foundNodeHasRightChild = foundNode.Node.RightChild != null;
        if(! foundNodeHasRightChild) {
            BSTNode<T> maxNodeFromFoundNode = findMax(foundNode.Node.LeftChild);

            replaceNodeKey(foundNode.Node, maxNodeFromFoundNode);
            // if maxNodeFromFoundNode if leaf
            if(maxNodeFromFoundNode.LeftChild == null) {
                maxNodeFromFoundNode.Parent.RightChild = null;
                maxNodeFromFoundNode.Parent = null;
                return true;
            }

            // if maxNodeFromFoundNode has only left child
            replaceNodeKey(maxNodeFromFoundNode, maxNodeFromFoundNode.LeftChild);
            maxNodeFromFoundNode.LeftChild = null;

            return true;
        }

        // case, when foundNode has right child
        BSTNode<T> minNodeFromFoundNode = findMin(foundNode.Node.RightChild);

        replaceNodeKey(foundNode.Node, minNodeFromFoundNode);

        // if minNodeFromFoundNode if leaf
        if(minNodeFromFoundNode.RightChild == null) {
            minNodeFromFoundNode.Parent.LeftChild = null;
            minNodeFromFoundNode.Parent = null;
            return true;
        }

        // if minNodeFromFoundNode has only right child
        replaceNodeKey(minNodeFromFoundNode, minNodeFromFoundNode.RightChild);
        minNodeFromFoundNode.RightChild = null;

        return true;
    }

    private void replaceNodeKey(BSTNode<T> deleteNode, BSTNode<T> newNode) {
        deleteNode.NodeKey = newNode.NodeKey;
        deleteNode.NodeValue = newNode.NodeValue;
    }

    public int Count()
    {
        if(Root == null){
            return 0;
        }
        return recursionCount(Root);
    }

    private int recursionCount(BSTNode<T> node)
    {
        if(node.RightChild != null && node.LeftChild != null) {
            return 1 + recursionCount(node.LeftChild) + recursionCount(node.RightChild);
        }
        if(node.RightChild != null) {
            return 1 + recursionCount(node.RightChild);
        }
        if(node.LeftChild != null) {
            return 1 + recursionCount(node.LeftChild);
        }
        return 1;
    }

}