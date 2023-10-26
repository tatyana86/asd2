import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    BSTNode<Integer> root = new BSTNode<>(8, 8, null);
    BST<Integer> testingBST = new BST<>(root);

    BSTNode<Integer> node4_firstLevel = new BSTNode<>(4, 4, root);
    BSTNode<Integer> node12_firstLevel = new BSTNode<>(12, 12, root);

    BSTNode<Integer> node2_secondLevel = new BSTNode<>(2, 2, node4_firstLevel);
    BSTNode<Integer> node6_secondLevel = new BSTNode<>(6, 6, node4_firstLevel);
    BSTNode<Integer> node1_thirdLevel = new BSTNode<>(1, 1, node2_secondLevel);
    BSTNode<Integer> node3_thirdLevel = new BSTNode<>(3, 3, node2_secondLevel);
    BSTNode<Integer> node5_thirdLevel = new BSTNode<>(5, 5, node6_secondLevel);
    BSTNode<Integer> node7_thirdLevel = new BSTNode<>(7, 7, node6_secondLevel);


    BSTNode<Integer> node10_secondLevel = new BSTNode<>(10, 10, node12_firstLevel);
    BSTNode<Integer> node14_secondLevel = new BSTNode<>(14, 14, node12_firstLevel);
    BSTNode<Integer> node9_thirdLevel = new BSTNode<>(9, 9, node10_secondLevel);
    BSTNode<Integer> node11_thirdLevel = new BSTNode<>(11, 11, node10_secondLevel);
    BSTNode<Integer> node13_thirdLevel = new BSTNode<>(13, 13, node14_secondLevel);
    BSTNode<Integer> node15_thirdLevel = new BSTNode<>(15, 15, node14_secondLevel);

    @BeforeEach
    void fillTree() {
        root.LeftChild = node4_firstLevel;
        root.RightChild = node12_firstLevel;

        node4_firstLevel.LeftChild = node2_secondLevel;
        node4_firstLevel.RightChild = node6_secondLevel;

        node2_secondLevel.LeftChild = node1_thirdLevel;
        node2_secondLevel.RightChild = node3_thirdLevel;

        node6_secondLevel.LeftChild = node5_thirdLevel;
        node6_secondLevel.RightChild = node7_thirdLevel;
    }

    @Test
    public void findNotExistKey() {
        BSTFind<Integer> foundNode = testingBST.FindNodeByKey(16);
        assertFalse(foundNode.NodeHasKey);
        assertFalse(foundNode.ToLeft);
    }

    @Test
    public void findExistKeyAsRightChild() {
        BSTFind<Integer> foundNode6 = testingBST.FindNodeByKey(6);
        assertEquals(node6_secondLevel, foundNode6.Node);
        assertTrue(foundNode6.NodeHasKey);

        BSTFind<Integer> foundNode3 = testingBST.FindNodeByKey(3);
        assertEquals(node3_thirdLevel, foundNode3.Node);
        assertTrue(foundNode3.NodeHasKey);
    }

    @Test
    public void findExistKeyAsLeftChild() {
        BSTFind<Integer> foundNode2 = testingBST.FindNodeByKey(2);
        assertEquals(node2_secondLevel, foundNode2.Node);
        assertTrue(foundNode2.NodeHasKey);

        BSTFind<Integer> foundNode5 = testingBST.FindNodeByKey(5);
        assertEquals(node5_thirdLevel, foundNode5.Node);
        assertTrue(foundNode5.NodeHasKey);
    }

    @Test
    public void addNodeAsLeftChild() {
        BSTFind<Integer> foundNode10 = testingBST.FindNodeByKey(10);
        assertFalse(foundNode10.NodeHasKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.AddKeyValue(10, 10 ));

        BSTFind<Integer> reFoundNode10 = testingBST.FindNodeByKey(10);
        assertTrue(reFoundNode10.NodeHasKey);
        assertEquals(node12_firstLevel, reFoundNode10.Node.Parent);
        assertEquals(10, testingBST.Count());
    }

    @Test
    public void addNodeAsRightChild() {
        BSTFind<Integer> foundNode14 = testingBST.FindNodeByKey(14);
        assertFalse(foundNode14.NodeHasKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.AddKeyValue(14, 14 ));

        BSTFind<Integer> reFoundNode14 = testingBST.FindNodeByKey(14);
        assertTrue(reFoundNode14.NodeHasKey);
        assertEquals(node12_firstLevel, reFoundNode14.Node.Parent);
        assertEquals(10, testingBST.Count());
    }

    @Test
    public void addNodeWithExistKey() {
        BSTFind<Integer> foundNode3 = testingBST.FindNodeByKey(3);
        assertEquals(9, testingBST.Count());
        assertTrue(foundNode3.NodeHasKey);
        assertFalse(testingBST.AddKeyValue(3, 3 ));
        assertEquals(9, testingBST.Count());
    }

    @Test
    public void findMaxNodeFromRoot() {
        assertEquals(node12_firstLevel, testingBST.FinMinMax(root, true));
    }

    @Test
    public void findMaxNodeFromNotRoot() {
        assertEquals(node7_thirdLevel, testingBST.FinMinMax(node4_firstLevel, true));
    }

    @Test
    public void findMinNodeFromRoot() {
        assertEquals(node1_thirdLevel, testingBST.FinMinMax(root, false));
    }

    @Test
    public void findMinNodeFromNotRoot() {
        assertEquals(node1_thirdLevel, testingBST.FinMinMax(node4_firstLevel, false));
    }

    @Test
    public void deleteNodeWithTwoChild() {
        BSTFind<Integer> foundNode4 = testingBST.FindNodeByKey(4);
        assertTrue(foundNode4.NodeHasKey);
        assertEquals(4, root.LeftChild.NodeKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.DeleteNodeByKey(4));
        assertEquals(5, root.LeftChild.NodeKey);
        assertNull(node6_secondLevel.LeftChild);

        BSTFind<Integer> reFoundNode4 = testingBST.FindNodeByKey(4);
        assertFalse(reFoundNode4.NodeHasKey);
        assertEquals(8, testingBST.Count());
    }

    @Test
    public void deleteNodeWithRightChild() {
        assertTrue(testingBST.AddKeyValue(14, 14 ));

        assertEquals(14, node12_firstLevel.RightChild.NodeKey);
        assertNull(node12_firstLevel.LeftChild);
        assertEquals(10, testingBST.Count());

        assertTrue(testingBST.DeleteNodeByKey(8));
        assertEquals(12, root.NodeKey);
        assertEquals(14, root.RightChild.NodeKey);

        BSTFind<Integer> reFoundNode8 = testingBST.FindNodeByKey(8);
        assertFalse(reFoundNode8.NodeHasKey);
        assertEquals(9, testingBST.Count());
    }

    @Test
    public void deleteLastLeftNode() {
        assertEquals(1, node2_secondLevel.LeftChild.NodeKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.DeleteNodeByKey(1));
        assertNull(node2_secondLevel.LeftChild);
        assertEquals(8, testingBST.Count());

        BSTFind<Integer> foundNode1 = testingBST.FindNodeByKey(1);
        assertFalse(foundNode1.NodeHasKey);
    }

    @Test
    public void deleteLastRightNode() {
        assertEquals(7, node6_secondLevel.RightChild.NodeKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.DeleteNodeByKey(7));
        assertNull(node6_secondLevel.RightChild);
        assertEquals(8, testingBST.Count());

        BSTFind<Integer> foundNode7 = testingBST.FindNodeByKey(7);
        assertFalse(foundNode7.NodeHasKey);
    }

    @Test
    public void deleteNodeAsOneLeftLeaf() {
        BSTFind<Integer> foundNode10 = testingBST.FindNodeByKey(10);
        assertFalse(foundNode10.NodeHasKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.AddKeyValue(10, 10));

        BSTFind<Integer> reFoundNode10 = testingBST.FindNodeByKey(10);
        assertTrue(reFoundNode10.NodeHasKey);
        assertEquals(node12_firstLevel, reFoundNode10.Node.Parent);
        assertEquals(10, testingBST.Count());

        assertTrue(testingBST.DeleteNodeByKey(10));
        assertEquals(9, testingBST.Count());
    }

    @Test
    public void deleteNodeAsOneRightLeaf() {
        BSTFind<Integer> foundNode14 = testingBST.FindNodeByKey(14);
        assertFalse(foundNode14.NodeHasKey);
        assertEquals(9, testingBST.Count());

        assertTrue(testingBST.AddKeyValue(14, 14));

        BSTFind<Integer> reFoundNode14 = testingBST.FindNodeByKey(14);
        assertTrue(reFoundNode14.NodeHasKey);
        assertEquals(node12_firstLevel, reFoundNode14.Node.Parent);
        assertEquals(10, testingBST.Count());

        assertTrue(testingBST.DeleteNodeByKey(14));
        assertEquals(9, testingBST.Count());
    }
}