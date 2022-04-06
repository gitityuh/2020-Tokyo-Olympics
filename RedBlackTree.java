//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

import java.util.*;

//import org.junit.jupiter.api.Test;
/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Country, K extends Comparable<K>> implements SortedCollectionInterface<T, K>, IRedBlackTrees<T, K> {

    protected ArrayList<Country> countryList = new ArrayList<>();
    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T, K> {
        public Country data;
        public K key;
        public int blackHeight = 0;
        public Node<T, K> parent; // null for root node
        public Node<T, K> leftChild;
        public Node<T, K> rightChild;
        public Node(Country data, K key) { this.data = data; this.key = key;}
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

    }

    protected Node<T, K> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    @Override
    public boolean insert(T data, K key) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T, K> newNode = new Node<>(data, key);
        if(root == null) { root = newNode; size++; this.root.blackHeight = 1; return true; } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
            else {throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");}

            this.root.blackHeight = 1;
            return returnValue;
        }
    }

    protected Node<T, K> getSib(Node<T, K> currNode){
        boolean NILNode = false;

        if (currNode.data == null) {
            NILNode = true;
        }

        if (currNode.equals(this.root)) {
            return null;
        }

        if (currNode.parent.leftChild != null && NILNode) {
            if (currNode.parent.leftChild.data == null) {
                return currNode.parent.rightChild;
            }
        }
        else if (currNode.parent.leftChild.data.equals(currNode.data)) {
            return currNode.parent.rightChild;
        }

        if (currNode.parent.rightChild != null && NILNode) {
            if (currNode.parent.rightChild.data == null) {
                return currNode.parent.leftChild;
            }
        }
        else if (currNode.parent.rightChild.data.equals(currNode.data)) {
            return currNode.parent.leftChild;
        }
        return null;
    }


    public void remove(Node <T, K> rmNode) throws NullPointerException , IllegalArgumentException {
        if(rmNode == null) {throw new NullPointerException(
                "This RedBlackTree does not store null references.");}

    	/*if (this.contains(rmNode.data) == false) {
    		throw new IllegalArgumentException("Node is not in tree.");
    	}*/
        System.out.println("remove called");

        //case 0: internal node
        if (rmNode.leftChild != null && rmNode.rightChild != null) {

            Node currNode = rmNode.leftChild;

            if (currNode.rightChild != null) {
                while (currNode.rightChild != null) {
                    currNode = currNode.rightChild;
                }

                currNode.parent.rightChild = null;
            }

            else {
                currNode.parent.leftChild = null;
            }

            int color = currNode.blackHeight;

            if (rmNode.equals(this.root)) {
                this.root = currNode;
            }

            else if (rmNode.parent.leftChild.data.equals(rmNode.data)) {
                rmNode.parent.leftChild = currNode;
            }
            else {
                rmNode.parent.rightChild = currNode;
            }


            currNode.parent = rmNode.parent;

            currNode.rightChild = rmNode.rightChild;
            currNode.leftChild = rmNode.leftChild;

            if (rmNode.leftChild != null) {
                rmNode.leftChild.parent = currNode;
            }

            if (rmNode.rightChild != null) {
                rmNode.rightChild.parent = currNode;
            }


            if (color == 1) {
                currNode.blackHeight = 2;
                remove(currNode);
            }
            else {
                currNode.blackHeight = 1;
            }

        }

        //case 1: if node is red leaf or 1 sib node
        else if (rmNode.blackHeight == 0) {
            System.out.println("red leaf test");
            Node currNode = rmNode;

            if (currNode.rightChild != null) {
                currNode = currNode.rightChild;

                if (currNode.leftChild != null) {
                    while (currNode.leftChild != null) {
                        currNode = currNode.leftChild;
                    }
                    currNode.parent.leftChild = null;
                }
                else {
                    currNode.parent.rightChild = null;
                }
                rmNode.parent.rightChild = currNode;

            }

            else if (currNode.leftChild != null) {
                currNode = currNode.leftChild;

                if (currNode.rightChild != null) {
                    while (currNode.rightChild != null) {
                        currNode = currNode.rightChild;
                    }
                    currNode.parent.rightChild = null;
                }
                else {
                    currNode.parent.leftChild = null;
                }

                rmNode.parent.leftChild = currNode;

            }

            if (!currNode.data.equals(rmNode.data)) {

                currNode.parent = rmNode.parent;

                currNode.rightChild = rmNode.rightChild;
                currNode.leftChild = rmNode.leftChild;

                if (rmNode.leftChild != null) {
                    rmNode.leftChild.parent = currNode;
                }

                if (rmNode.rightChild != null) {
                    rmNode.rightChild.parent = currNode;
                }
            }
            else if (currNode.data.equals(rmNode.data) && currNode.parent.leftChild != null &&
                    currNode.data.equals(currNode.parent.leftChild.data)) {
                rmNode.parent.leftChild = null;

            }
            else if (currNode.data.equals(rmNode.data) && currNode.parent.rightChild != null
                    && currNode.data.equals(currNode.parent.rightChild.data)) {
                rmNode.parent.rightChild = null;

            }

            currNode.blackHeight = 0;


        }

        //black leaf node
        if (rmNode.blackHeight == 1 && rmNode.leftChild == null && rmNode.rightChild == null) {
            rmNode.data = null;
            rmNode.blackHeight = 2;
            remove(rmNode);
        }


        else if (rmNode.blackHeight == 2) {

            System.out.println("testDB");
            //case 2: DB is root
            if (rmNode.blackHeight == 2 && root.data.equals(rmNode.data)) {
                rmNode.blackHeight = 1;
                this.root.blackHeight = 1;
                this.root = rmNode;

            }

            //case 3: DB with red sibling, black or null children
            if ((getSib(rmNode) == null) || ((getSib(rmNode) != null && getSib(rmNode).blackHeight == 1))) {


                if (getSib(rmNode) == null){
                    if (rmNode.rightChild != null && rmNode.rightChild.data != null) {
                        rotate(rmNode.rightChild, rmNode);
                        return;
                    }
                    else if (rmNode.leftChild != null && rmNode.leftChild.data != null) {
                        rotate(rmNode.leftChild, rmNode);
                        return;
                    }
                }
                if ((getSib(rmNode).leftChild == null || getSib(rmNode).leftChild.blackHeight == 1) &&
                        (getSib(rmNode).rightChild == null || getSib(rmNode).rightChild.blackHeight==1)) {

                    //make sib red
                    getSib(rmNode).blackHeight = 0;

                    //make parent black or double black
                    rmNode.parent.blackHeight =+ 1;

                    //if node is null delete
                    if (rmNode.data == null) {
                        if (getSib(rmNode).data.equals(rmNode.parent.rightChild.data)){
                            rmNode.parent.leftChild = null;

                        }
                        else {
                            rmNode.parent.rightChild = null;

                        }
                    }

                    else {
                        rmNode.blackHeight = 1;
                        return;
                    }

                    //if parent is double black run again
                    if (rmNode.parent.blackHeight == 2) {
                        remove(rmNode.parent);
                        return;
                    }
                }
            }
            //case 4 if DB sib is red
            if (getSib(rmNode) != null && getSib(rmNode).blackHeight == 0) {
                System.out.println("test4");
                //swap parent and sib color
                int parentColor = rmNode.parent.blackHeight;
                int sibColor = getSib(rmNode).blackHeight;

                rmNode.parent.blackHeight = sibColor;
                getSib(rmNode).blackHeight = parentColor;


                //rotate
                rotate(getSib(rmNode), rmNode.parent);

                //check for new case
                remove(rmNode);
                return;
            }

            //case 5: DB sib is black, sib far child is black, sib near child is red

            ///ROOT CATCH
            if (getSib(rmNode) == null) {

            }

            if (getSib(rmNode) != null && getSib(rmNode).blackHeight == 1) {
                System.out.println("test5");

                //if DB is left side, sib is right
                if (rmNode.parent.rightChild != null &&
                        getSib(rmNode).equals(rmNode.parent.rightChild)) {

                    //if sib near child is red
                    if (getSib(rmNode).leftChild != null &&
                            getSib(rmNode).leftChild.blackHeight ==0) {

                        //if sib far child is black
                        if (getSib(rmNode).rightChild != null &&
                                getSib(rmNode).rightChild.blackHeight ==1) {

                            getSib(rmNode).blackHeight = 0;
                            getSib(rmNode).leftChild.blackHeight = 1;

                            rotate(getSib(rmNode).leftChild, getSib(rmNode));

                            remove(rmNode);

                        }
                    }
                }

                //if DB is right side, sib is left
                if (rmNode.parent.leftChild != null &&
                        getSib(rmNode).data.equals(rmNode.parent.leftChild.data)) {

                    //if sib near child is red
                    if (getSib(rmNode).rightChild != null &&
                            getSib(rmNode).rightChild.blackHeight ==0) {

                        //if sib far child is black
                        if (getSib(rmNode).leftChild != null &&
                                getSib(rmNode).leftChild.blackHeight ==1) {

                            getSib(rmNode).blackHeight = 0;
                            getSib(rmNode).rightChild.blackHeight = 1;

                            rotate(getSib(rmNode).rightChild, getSib(rmNode));

                            remove(rmNode);

                        }
                    }
                }
            }

            //case 6: DB sib is black, sib far child is red
            if (getSib(rmNode) != null && getSib(rmNode).blackHeight == 1) {
                System.out.println("test6");

                //if DB is left side, sib is right
                if (rmNode.parent.rightChild.data != null &&
                        getSib(rmNode).data.equals(rmNode.parent.rightChild.data)) {

                    //if sib near child is red
                    if (getSib(rmNode).rightChild.data != null &&
                            getSib(rmNode).rightChild.blackHeight ==0) {

                        int DBParentColor = rmNode.parent.blackHeight;
                        int SibColor = getSib(rmNode).blackHeight;

                        rmNode.parent.blackHeight = SibColor;
                        getSib(rmNode).blackHeight = DBParentColor;

                        getSib(rmNode).rightChild.blackHeight = 1;
                        rotate(getSib(rmNode), rmNode.parent);

                        rmNode.blackHeight = 1;



                        rmNode.parent.leftChild = null;
                    }
                }

                //if DB is right side, sib is left
                else if (rmNode.parent.leftChild.data != null &&
                        getSib(rmNode).data.equals(rmNode.parent.leftChild.data)) {

                    //if sib near child is red
                    if (getSib(rmNode).leftChild.data != null &&
                            getSib(rmNode).leftChild.blackHeight ==0) {

                        int DBParentColor = rmNode.parent.blackHeight;
                        int SibColor = getSib(rmNode).blackHeight;

                        rmNode.parent.blackHeight = SibColor;
                        getSib(rmNode).blackHeight = DBParentColor;

                        getSib(rmNode).leftChild.blackHeight = 1;
                        rotate(getSib(rmNode), rmNode.parent);

                        rmNode.blackHeight = 1;


                        rmNode.parent.rightChild = null;
                    }
                }
            }

        }


    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T, K> newNode, Node<T, K> subtree) {
        int compare = newNode.key.compareTo(subtree.key);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) {
            if (newNode.data.countryName.compareTo(subtree.data.countryName) < 0){
                    compare = -1;
            }
            else if (newNode.data.countryName.compareTo(subtree.data.countryName) < 0){
                compare = 1;
            }
            else {
                return false;
            }
        }
            // store newNode within left subtree of subtree
        if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }


    protected void enforceRBTreePropertiesAfterInsert(Node<T, K> newNode) {
        if (this.root.equals(newNode)) {
            newNode.blackHeight = 1;
            this.root = newNode;
            return;
        }

        //if parent is black
        if (newNode.parent.blackHeight == 1) {
            return;
        }

        //if parent is red
        else if (newNode.parent.blackHeight == 0) {

            //parent side is right
            if (! newNode.parent.isLeftChild()) {

                //if uncle is null or black
                if (newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.blackHeight == 1) {

                    //child side is left (RL)
                    if (newNode.isLeftChild()) {
                        newNode.rightChild = newNode.parent;
                        newNode.rightChild.leftChild = null;
                        newNode.parent = newNode.rightChild.parent;
                        newNode.rightChild.parent.rightChild = newNode;
                        newNode.rightChild.parent = newNode;
                        newNode.parent.blackHeight = 0;
                        newNode.blackHeight = 1;

                        rotate(newNode, newNode.parent);

                        if (newNode.parent == null) {
                            return;
                        }
                        enforceRBTreePropertiesAfterInsert(newNode.parent);
                        return;
                    }

                    //Parent is R child is R (RR)

                    //if uncle is null
                    if (newNode.parent.parent.leftChild == null) {
                        newNode.parent.blackHeight = 1;
                        newNode.parent.parent.blackHeight = 0;
                        newNode.blackHeight = 0;

                        rotate(newNode.parent, newNode.parent.parent);
                    }

                    //if uncle is black
                    else if (newNode.parent.parent.leftChild.blackHeight == 1) {
                        newNode.parent.blackHeight = 1;
                        newNode.parent.parent.blackHeight = 0;
                        rotate(newNode.parent, newNode.parent.parent);

                    }
                }
                //if uncle is red
                else if (newNode.parent.parent.leftChild.blackHeight == 0) {
                    newNode.parent.blackHeight = 1;
                    newNode.parent.parent.blackHeight = 0;
                    newNode.parent.parent.leftChild.blackHeight = 1;
                    newNode.blackHeight = 0;

                    enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
                    return;
                }

            }

            //parent side is left
            else if (newNode.parent.isLeftChild()) {

                //if uncle is null or black
                if (newNode.parent.parent.rightChild == null || newNode.parent.parent.rightChild.blackHeight == 1) {

                    //child side is left (LR)
                    if (! newNode.isLeftChild()) {

                        newNode.leftChild = newNode.parent;
                        newNode.leftChild.rightChild = null;
                        newNode.parent = newNode.leftChild.parent;
                        newNode.leftChild.parent.leftChild = newNode;
                        newNode.leftChild.parent = newNode;
                        newNode.parent.blackHeight = 0;
                        newNode.blackHeight = 1;

                        rotate(newNode, newNode.parent);

                        if (newNode.parent == null) {
                            return;
                        }
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return;
                    }

                    //Parent is L child is L (LL)

                    //if uncle is null
                    if (newNode.parent.parent.rightChild == null) {
                        newNode.parent.blackHeight = 1;
                        newNode.parent.parent.blackHeight = 0;
                        newNode.blackHeight = 0;

                        rotate(newNode.parent, newNode.parent.parent);
                    }

                    //if uncle is black
                    else if (newNode.parent.parent.rightChild.blackHeight == 1) {
                        newNode.parent.blackHeight = 1;
                        newNode.parent.parent.blackHeight = 0;
                        rotate(newNode.parent, newNode.parent.parent);

                    }


                }

                //if uncle is red
                else if (newNode.parent.parent.rightChild.blackHeight == 0) {
                    newNode.parent.blackHeight = 1;
                    newNode.parent.parent.rightChild.blackHeight = 1;
                    newNode.parent.parent.blackHeight = 0;
                    newNode.blackHeight = 0;

                    enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
                }
            }
        }
    }



    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T, K> child, Node<T, K> parent) throws IllegalArgumentException {
        if(child.isLeftChild()) {
            parent.leftChild = child.rightChild;

            if (child.rightChild != null) {
                child.rightChild.parent = parent;
            }
            if (parent.parent == null) {
                this.root = child;
            }
            else if (parent == parent.parent.rightChild) {
                parent.parent.rightChild = child;
            }
            else {
                parent.parent.leftChild = child;
            }
            child.rightChild = parent;
            parent.parent = child;
        }
        else {
            parent.rightChild = child.leftChild;

            if(child.leftChild != null) {
                child.leftChild.parent = parent;
            }

            child.parent = parent.parent;

            if(parent.parent == null) {
                this.root = child;
            }


            else if (parent.isLeftChild()) {
                parent.parent.leftChild = child;
            }
            else {
                parent.parent.rightChild = child;
            }
            child.leftChild = parent;
            parent.parent = child;
        }

    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data, K key) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, key, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, K key, Node<T, K> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        }
        else {
            int compare = key.compareTo(subtree.key);

            if (compare == 0) {
                compare = data.countryName.compareTo(subtree.data.countryName);
            }
            else if (compare < 0) {
                // go left in the tree
                return containsHelper(data, key, subtree.leftChild);
            }
            else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, key, subtree.rightChild);
            }
            else {
                // we found it :)
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the values in in-order (sorted) order.
     * @return iterator object that traverses the tree in in-order sequence
     */
    @Override
    public Iterator<T> iterator() {
        // use an anonymous class here that implements the Iterator interface
        // we create a new on-off object of this class everytime the iterator
        // method is called
        return new Iterator<T>() {
            // a stack and current reference store the progress of the traversal
            // so that we can return one value at a time with the Iterator
            Stack<Node<T, K>> stack = null;
            Node<T, K> current = root;

            /**
             * The next method is called for each value in the traversal sequence.
             * It returns one value at a time.
             * @return next value in the sequence of the traversal
             * @throws NoSuchElementException if there is no more elements in the sequence
             */
            public T next() {
                // if stack == null, we need to initialize the stack and current element
                if (stack == null) {
                    stack = new Stack<Node<T, K>>();
                    current = root;
                }
                // go left as far as possible in the sub tree we are in un8til we hit a null
                // leaf (current is null), pushing all the nodes we fund on our way onto the
                // stack to process later
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                // as long as the stack is not empty, we haven't finished the traversal yet;
                // take the next element from the stack and return it, then start to step down
                // its right subtree (set its right sub tree to current)
                if (!stack.isEmpty()) {
                    Node<T, K> processedNode = stack.pop();
                    current = processedNode.rightChild;
                    return (T) processedNode.data;
                } else {
                    // if the stack is empty, we are done with our traversal
                    throw new NoSuchElementException("There are no more elements in the tree");
                }

            }

            /**
             * Returns a boolean that indicates if the iterator has more elements (true),
             * or if the traversal has finished (false)
             * @return boolean indicating whether there are more elements / steps for the traversal
             */
            public boolean hasNext() {
                // return true if we either still have a current reference, or the stack
                // is not empty yet
                return !(current == null && (stack == null || stack.isEmpty()) );
            }

        };
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // use the inorder Iterator that we get by calling the iterator method above
        // to generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        Iterator<T> treeNodeIterator = this.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (treeNodeIterator.hasNext())
            sb.append(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            T data = treeNodeIterator.next();
            sb.append(", ");
            sb.append(data.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        LinkedList<Node<T, K>> q = new LinkedList<>();
        q.add(this.root);
        while(!q.isEmpty()) {
            Node<T, K> next = q.removeFirst();
            if(next.leftChild != null) q.add(next.leftChild);
            if(next.rightChild != null) q.add(next.rightChild);
            output += next.data.toString();
            if(!q.isEmpty()) output += ", ";
        }
        return output + " ]";
    }

    @Override
    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "/nin order: " + this.toInOrderString();
    }

    public ArrayList<T> storeKeyValues(RedBlackTree.Node<T, K> root) {
        countryList = new ArrayList<>();
        treeTravel(root);
        return (ArrayList<T>) countryList;
    }
    private void treeTravel(RedBlackTree.Node<T, K> node) {
        if (node != null) {
            treeTravel(node.leftChild);
            countryList.add(node.data);
            treeTravel(node.rightChild);
        }
    }




    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */

    public static void main(String[] args) {
        RedBlackTree RBT = new RedBlackTree();

        //remove red leaf node



        //RBT.insert(12);

        //System.out.println(RBT.root.rightChild.leftChild.blackHeight);
        System.out.println(RBT.toLevelOrderString());
        System.out.println(RBT.root.data);

		/*
		System.out.println(RBT.toLevelOrderString());
		System.out.print(RBT.root.leftChild.blackHeight);
		System.out.print(RBT.root.rightChild.blackHeight);
		//System.out.print(RBT.root.rightChild.rightChild.rightChild.blackHeight);
		System.out.print(RBT.root.rightChild.leftChild.blackHeight);

		RBT.remove(RBT.root);
		System.out.println(RBT.toLevelOrderString());
		System.out.print(RBT.root.leftChild.blackHeight);
		System.out.print(RBT.root.rightChild.blackHeight);*/

    }

}

