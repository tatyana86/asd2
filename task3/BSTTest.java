import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public void wideAllNodes() {
        ArrayList<BSTNode> test = testingBST.WideAllNodes();
        List<Integer> expectedKeys = Arrays.asList(8, 4, 12, 2, 6, 1, 3, 5, 7);
        List<Integer> actualKeys = new ArrayList<>();

        for(BSTNode node : test) {
            actualKeys.add(node.NodeKey);
            System.out.print(node.NodeKey + " ");
        }

        assertEquals(expectedKeys, actualKeys);
    }

    final static int IN_ORDER = 0;
    final static int POST_ORDER = 1;
    final static int PRE_ORDER = 2;

    @Test
    public void deepAllNodesIN() {
        ArrayList<BSTNode> test = testingBST.DeepAllNodes(IN_ORDER);
        List<Integer> expectedKeys = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 12);
        List<Integer> actualKeys = new ArrayList<>();

        for(BSTNode node : test) {
            actualKeys.add(node.NodeKey);
            //System.out.print(node.NodeKey + " ");
        }

        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void deepAllNodesPOST() {
        ArrayList<BSTNode> test = testingBST.DeepAllNodes(POST_ORDER);
        List<Integer> expectedKeys = Arrays.asList(1, 3, 2, 5, 7, 6, 4, 12, 8);
        List<Integer> actualKeys = new ArrayList<>();

        for(BSTNode node : test) {
            actualKeys.add(node.NodeKey);
        }

        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void deepAllNodesPRE() {
        ArrayList<BSTNode> test = testingBST.DeepAllNodes(PRE_ORDER);
        List<Integer> expectedKeys = Arrays.asList(8, 4, 2, 1, 3, 6, 5, 7, 12);
        List<Integer> actualKeys = new ArrayList<>();

        for(BSTNode node : test) {
            actualKeys.add(node.NodeKey);
        }

        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void invertTreeAndGetWideNodes() {
        testingBST.invertTree();

        ArrayList<BSTNode> test = testingBST.WideAllNodes();
        List<Integer> expectedKeys = Arrays.asList(8, 12, 4, 6, 2, 7, 5, 3, 1);
        List<Integer> actualKeys = new ArrayList<>();

        for(BSTNode node : test) {
            actualKeys.add(node.NodeKey);
        }

        assertEquals(expectedKeys, actualKeys);
    }
}