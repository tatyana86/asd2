import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    SimpleTreeNode<Integer> root = new SimpleTreeNode<>(null, null);
    SimpleTree<Integer> simpleTree = new SimpleTree<>(root);

    SimpleTreeNode<Integer> fullRoot = new SimpleTreeNode<>(9, null);
    SimpleTree<Integer> fullSimpleTree = new SimpleTree<>(fullRoot);

    SimpleTreeNode<Integer> node4_firstLevel = new SimpleTreeNode<>(4, fullRoot);
    SimpleTreeNode<Integer> node17_firstLevel = new SimpleTreeNode<>(17, fullRoot);
    SimpleTreeNode<Integer> node3_secondLevel = new SimpleTreeNode<>(3, node4_firstLevel);
    SimpleTreeNode<Integer> node5_secondLevel = new SimpleTreeNode<>(5, node4_firstLevel);
    SimpleTreeNode<Integer> node7_secondLevel = new SimpleTreeNode<>(7, node4_firstLevel);
    SimpleTreeNode<Integer> node22_secondLevel = new SimpleTreeNode<>(22, node17_firstLevel);

    @BeforeEach
    void fillTree() {
        fullSimpleTree.AddChild(fullRoot, node4_firstLevel);
        fullSimpleTree.AddChild(fullRoot, node17_firstLevel);
        fullSimpleTree.AddChild(node4_firstLevel, node3_secondLevel);
        fullSimpleTree.AddChild(node4_firstLevel, node5_secondLevel);
        fullSimpleTree.AddChild(node4_firstLevel, node7_secondLevel);
        fullSimpleTree.AddChild(node17_firstLevel, node22_secondLevel);
    }

    @Test
    public void testSimpleTree() {
        assertEquals(0, simpleTree.Count());
        assertEquals(0, simpleTree.LeafCount());
    }

    @Test
    public void addingNodeInTree() {
        assertEquals(7, fullSimpleTree.Count());
        assertEquals(4, fullSimpleTree.LeafCount());
    }

    @Test
    public void deleteNodeFromTree() {
        fullSimpleTree.DeleteNode(node3_secondLevel);
        assertEquals(6, fullSimpleTree.Count());
        assertEquals(3, fullSimpleTree.LeafCount());

        fullSimpleTree.DeleteNode(node4_firstLevel);
        assertEquals(3, fullSimpleTree.Count());
        assertEquals(1, fullSimpleTree.LeafCount());

        fullSimpleTree.DeleteNode(node22_secondLevel);
    }

    @Test
    public void deleteTwoNodeTree() {
        root.NodeValue = 12;
        simpleTree.AddChild(root, node4_firstLevel);
        simpleTree.DeleteNode(node4_firstLevel);

        assertEquals(1, simpleTree.Count());
        assertEquals(1, simpleTree.LeafCount());
        assertNull(node4_firstLevel.Parent);
    }

    @Test
    public void nodesByValue() {
        SimpleTreeNode<Integer> node5_firstLevel = new SimpleTreeNode<>(5, fullRoot);
        fullSimpleTree.AddChild(fullRoot, node5_firstLevel);

        List<SimpleTreeNode<Integer>> nodesByValue = fullSimpleTree.FindNodesByValue(5);

        assertTrue(nodesByValue.contains(node5_firstLevel));
        assertTrue(nodesByValue.contains(node5_secondLevel));
        assertEquals(2, nodesByValue.size());
    }

    @Test
    public void moveNode() {
        fullSimpleTree.MoveNode(node4_firstLevel, node17_firstLevel);
        assertEquals(1, fullRoot.Children.size());
        assertEquals(2, node17_firstLevel.Children.size());
    }

    @Test
    public void getNodeLevel() {
        assertEquals(0, fullSimpleTree.recursionGetNodeLevel(fullRoot));
        assertEquals(1, fullSimpleTree.recursionGetNodeLevel(node4_firstLevel));
        assertEquals(1, fullSimpleTree.recursionGetNodeLevel(node17_firstLevel));
        assertEquals(2, fullSimpleTree.recursionGetNodeLevel(node3_secondLevel));
        assertEquals(2, fullSimpleTree.recursionGetNodeLevel(node5_secondLevel));
        assertEquals(2, fullSimpleTree.recursionGetNodeLevel(node7_secondLevel));
        assertEquals(2, fullSimpleTree.recursionGetNodeLevel(node22_secondLevel));
    }

    @Test
    public void printLevels() {
        fullSimpleTree.printNodesLevel();
    }

    @Test
    public void findExistRootNode() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(5, null);
        SimpleTree<Integer> simpleTree = new SimpleTree<>(root);

        List<SimpleTreeNode<Integer>> nodesByValue9 = simpleTree.FindNodesByValue(9);
        assertEquals(0, nodesByValue9.size());

        List<SimpleTreeNode<Integer>> nodesByValue5 = simpleTree.FindNodesByValue(5);
        assertEquals(1, nodesByValue5.size());
    }

    @Test
    public void findExistNodes() {
        List<SimpleTreeNode<Integer>> nodesByValue9 = fullSimpleTree.FindNodesByValue(9);
        List<SimpleTreeNode<Integer>> nodesByValue4 = fullSimpleTree.FindNodesByValue(4);
        List<SimpleTreeNode<Integer>> nodesByValue3 = fullSimpleTree.FindNodesByValue(3);
        List<SimpleTreeNode<Integer>> nodesByValue5 = fullSimpleTree.FindNodesByValue(5);
        List<SimpleTreeNode<Integer>> nodesByValue7 = fullSimpleTree.FindNodesByValue(7);
        List<SimpleTreeNode<Integer>> nodesByValue17 = fullSimpleTree.FindNodesByValue(17);
        List<SimpleTreeNode<Integer>> nodesByValue22 = fullSimpleTree.FindNodesByValue(22);
        List<SimpleTreeNode<Integer>> nodesByValue1 = fullSimpleTree.FindNodesByValue(1);

        assertEquals(1, nodesByValue9.size());
        assertEquals(1, nodesByValue4.size());
        assertEquals(1, nodesByValue3.size());
        assertEquals(1, nodesByValue5.size());
        assertEquals(1, nodesByValue7.size());
        assertEquals(1, nodesByValue17.size());
        assertEquals(1, nodesByValue22.size());
        assertEquals(0, nodesByValue1.size());
    }
}