import java.util.ArrayList;

public class RedBlackTreeBackend<T extends Comparable<T>>
{

    protected RedBlackTreeBackend.Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree
    protected ArrayList<T> countryList = new ArrayList<>();

    protected static class Node<T> {
        public T data;
        public int blackHeight = 0;
        public RedBlackTreeBackend.Node<T> parent; // null for root node
        public RedBlackTreeBackend.Node<T> leftChild;
        public RedBlackTreeBackend.Node<T> rightChild;
        public Node(T data) { this.data = data; }


    }//end Node



    public void setRoot(Object t){
        Node<T> node = new Node(t);
        this.root = node;
    }
    public void setRootRight(Object t){
        Node<T> node = new Node(t);
        this.root.rightChild = node;
        node.parent = root;
    }

    public void setRootleft(Object t){
        Node<T> node = new Node(t);
        this.root.leftChild = node;
        node.parent = root;
    }


    public ArrayList<T> storeKeyValues(RedBlackTreeBackend.Node<T> root) {
        treeTravel(root);
        return countryList;
    }


    private void treeTravel(RedBlackTreeBackend.Node<T> node) {
        if (node != null) {
            treeTravel(node.leftChild);
            countryList.add(node.data);
            treeTravel(node.rightChild);
        }
    }

    public void insert(Object t, Object b){
        //does nothing for now gonna hard cde
    }




}
