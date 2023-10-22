import java.util.*;

class SimpleTreeNode<T>
{
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if(ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<SimpleTreeNode<T>>();
        }
        ParentNode.Children.add(NewChild);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        NodeToDelete.Parent.Children.remove(NodeToDelete);
        NodeToDelete.Parent = null;
        NodeToDelete.Children = null;
    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        if(Root.NodeValue == null) {
            return new ArrayList<>();
        }
        if(Root.Children == null) {
            List<SimpleTreeNode<T>> allTreeNodes = new ArrayList<SimpleTreeNode<T>>();
            allTreeNodes.add(Root);
            return allTreeNodes;
        }

        return recursionGetAllNodes(Root);
    }

    private List<SimpleTreeNode<T>> recursionGetAllNodes(SimpleTreeNode<T> nodeForSearchChildren) {

        List<SimpleTreeNode<T>> allTreeNodes = new ArrayList<SimpleTreeNode<T>>();
        List<SimpleTreeNode<T>> expand = new ArrayList<SimpleTreeNode<T>>();

        allTreeNodes.add(nodeForSearchChildren);
        expand.addAll(nodeForSearchChildren.Children);

        for(SimpleTreeNode node : expand) {
            if(node.Children != null) {
                allTreeNodes.addAll(recursionGetAllNodes(node));
            }
            if(node.Children == null) {
                allTreeNodes.add(node);
            }
        }

        return allTreeNodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        if(Root.NodeValue == null) {
            return new ArrayList<>();
        }

        if(Root.Children == null && Root.NodeValue == val) {
            List<SimpleTreeNode<T>> nodesByValue = new ArrayList<SimpleTreeNode<T>>();
            nodesByValue.add(Root);
            return nodesByValue;
        }

        if(Root.Children == null && Root.NodeValue != val) {
            List<SimpleTreeNode<T>> nodesByValue = new ArrayList<SimpleTreeNode<T>>();
            return nodesByValue;
        }

        return recursionFindNodesByValue(val, Root);
    }

    private List<SimpleTreeNode<T>> recursionFindNodesByValue(T val, SimpleTreeNode<T> nodeForCheck) {

        List<SimpleTreeNode<T>> nodesByValue = new ArrayList<SimpleTreeNode<T>>();
        List<SimpleTreeNode<T>> expand = new ArrayList<SimpleTreeNode<T>>();

        if(nodeForCheck == Root && nodeForCheck.NodeValue == val) {
            nodesByValue.add(nodeForCheck);
        }
        expand.addAll(nodeForCheck.Children);

        for(SimpleTreeNode node : expand) {
            if(node.Children != null) {
                nodesByValue.addAll(recursionFindNodesByValue(val, node));
            }
            if(node.NodeValue == val && node.Children == null) {
                nodesByValue.add(node);
            }
        }

        return nodesByValue;

    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count()
    {
        return GetAllNodes().size();
    }

    public int LeafCount()
    {
        if(Root.NodeValue == null) {
            return 0;
        }

        return recursionLeafCount(Root);
    }

    private int recursionLeafCount(SimpleTreeNode<T> nodeForSearchLeaf) {

        int count = 0;

        if(nodeForSearchLeaf.Children == null || nodeForSearchLeaf.Children.isEmpty()) {
            count ++;
            return count;
        }
        for(SimpleTreeNode nodeChild : nodeForSearchLeaf.Children) {
            count += recursionLeafCount(nodeChild);
        }

        return count;
    }

    public int recursionGetNodeLevel(SimpleTreeNode<T> node) {

        if(node.Parent == null) {
            return 0;
        }

        int level = 1;

        level += recursionGetNodeLevel(node.Parent);

        return level;

    }

    public void printNodesLevel() {
        List<SimpleTreeNode<T>> allNodes = GetAllNodes();

        for(SimpleTreeNode<T> node : allNodes) {
            System.out.println("Level of node with value " + node.NodeValue + " - " + recursionGetNodeLevel(node));
        }
    }

}