    public void invertTree() {
        recursionInvertTree(Root);
    }

    private void recursionInvertTree(BSTNode<T> node) {
        if(node != null) {
            BSTNode<T> copyOfLeftChild = node.LeftChild;
            node.LeftChild = node.RightChild;
            node.RightChild = copyOfLeftChild;

            recursionInvertTree(node.LeftChild);
            recursionInvertTree(node.RightChild);
        }
    }