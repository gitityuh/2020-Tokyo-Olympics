import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmEngineerTests {
    int red = 0;
    int black = 1;

    AlgoEngrCountry America = new AlgoEngrCountry("America", 10);
    AlgoEngrCountry Australia = new AlgoEngrCountry("Australia", 5);
    AlgoEngrCountry China = new AlgoEngrCountry("China", 20);
    AlgoEngrCountry Denmark = new AlgoEngrCountry("Denmark", 20);
    AlgoEngrCountry Ethiopia = new AlgoEngrCountry("Ethiopia", 12);
    AlgoEngrCountry France = new AlgoEngrCountry("France", 16);
    AlgoEngrCountry Germany = new AlgoEngrCountry("Germany", 25);
    AlgoEngrCountry Mexico = new AlgoEngrCountry("Mexico", 30);
    AlgoEngrCountry Netherlands = new AlgoEngrCountry("Netherlands", 22);
    AlgoEngrCountry Poland = new AlgoEngrCountry("Poland", 10);

    /**
     * This test focuses on simple testing simple inserts with single rotations
     */
    @Test
    public void test1() {
        //simple insert
        RedBlackTree RBT = new RedBlackTree();

        RBT.insert(America, 10);
        RBT.insert(Australia, 5);
        RBT.insert(China, 15);

        assertEquals(red, RBT.root.leftChild.blackHeight, RBT.root.rightChild.blackHeight);

        //Addition of a node with red uncle on right side, no recall
        RBT.insert(Denmark, 20);

        String result = RBT.toLevelOrderString();

        assertEquals("[ America: 10, Australia: 5, China: 15, Denmark: 20 ]", result);
        assertEquals(black, RBT.root.leftChild.blackHeight, RBT.root.rightChild.blackHeight);
        assertEquals(red, RBT.root.rightChild.rightChild.blackHeight);

        //Right Addition with a red uncle that involves a recall to the method
        {
            RedBlackTree RBT2 = new RedBlackTree();
            RBT2.insert(America, 10);
            RBT2.insert(China, 15);
            RBT2.insert(Australia, 5);
            RBT2.insert(Ethiopia, 12);
            RBT2.insert(Denmark, 20);
            RBT2.insert(France, 16);
            RBT2.insert(Germany, 25);

            assertEquals(black, RBT2.root.leftChild.blackHeight);
            assertEquals(black, RBT2.root.rightChild.leftChild.blackHeight);
            assertEquals(black, RBT2.root.rightChild.rightChild.blackHeight);

            System.out.println(RBT2.toLevelOrderString());


            RBT2.insert(Mexico, 30);

            result = RBT2.toLevelOrderString();

            assertEquals("[ China: 15, America: 10, Denmark: 20, Australia: 5, Ethiopia: 12, France: 16, Germany: 25, Mexico: 30 ]", result);
            assertEquals(red, RBT2.root.rightChild.blackHeight);
            assertEquals(black, RBT2.root.rightChild.leftChild.blackHeight);
            assertEquals(black, RBT2.root.rightChild.rightChild.blackHeight);
            assertEquals(red, RBT2.root.rightChild.rightChild.rightChild.blackHeight);

            assertEquals(red, RBT2.root.leftChild.blackHeight);
            assertEquals(black, RBT2.root.leftChild.leftChild.blackHeight);
            assertEquals(black, RBT2.root.leftChild.rightChild.blackHeight);
        }

    }

    /**
     * The method tests a double rotation with re-calls to the insert helper method,
     * a complicated insert case.
     */
    @Test
    public void test2() {
        RedBlackTree RBT2 = new RedBlackTree();
        RBT2.insert(America, 10);
        RBT2.insert(China, 15);
        RBT2.insert(Australia, 5);
        RBT2.insert(Ethiopia, 12);
        RBT2.insert(Denmark, 20);
        RBT2.insert(France, 16);
        RBT2.insert(Germany, 25);

        //new node > 25
        RBT2.insert(Netherlands, 22);

        String result = RBT2.toLevelOrderString();
        assertEquals("[ China: 15, America: 10, Denmark: 20, Australia: 5, Ethiopia: 12, France: 16, Germany: 25, Netherlands: 22 ]", result);
        assertEquals(red, RBT2.root.rightChild.blackHeight);
        assertEquals(black, RBT2.root.rightChild.leftChild.blackHeight);
        assertEquals(black, RBT2.root.rightChild.rightChild.blackHeight);
        assertEquals(red, RBT2.root.rightChild.rightChild.leftChild.blackHeight);

        assertEquals(red, RBT2.root.leftChild.blackHeight);
        assertEquals(black, RBT2.root.leftChild.leftChild.blackHeight);
        assertEquals(black, RBT2.root.leftChild.rightChild.blackHeight);

    }

    /**
     * This method focuses on the remove node method.
     *
     * NOTE: Nodes are only accessed from pointer references as after implementation
     * we decided as a group we did not need to remove node by data
     */
    @Test
    public void test3() {
        {
            RedBlackTree RBT = new RedBlackTree();

            //remove red leaf node
            RBT.insert(America, 10);
            RBT.insert(France, 16);
            RBT.insert(Australia, 5);
            RBT.insert(Ethiopia, 12);

            assertEquals(0, RBT.root.rightChild.leftChild.blackHeight);

            RBT.remove(RBT.root.rightChild.leftChild);

            assertEquals("[ America: 10, Australia: 5, France: 16 ]", RBT.toLevelOrderString());
            assertEquals(1, RBT.root.rightChild.blackHeight);
            assertEquals(1, RBT.root.leftChild.blackHeight);

            //remove interior black node
            RBT.insert(Ethiopia, 12);
            RBT.insert(Denmark, 18);
            assertEquals(1, RBT.root.rightChild.blackHeight);

            RBT.remove(RBT.root.rightChild);


            assertEquals("[ America: 10, Australia: 5, Ethiopia: 12, Denmark: 18 ]", RBT.toLevelOrderString());
            assertEquals(1, RBT.root.rightChild.blackHeight);
            assertEquals(0, RBT.root.rightChild.rightChild.blackHeight);

            //red uncle, double black recall with red uncle
            RBT.insert(Germany, 11);
            RBT.insert(France, 16);
            RBT.insert(Denmark, 20);

            String expected = "[ America: 10, Australia: 5, Ethiopia: 12, Germany: 11, Denmark: 18, France: 16, Denmark: 20 ]";

            assertEquals(expected, RBT.toLevelOrderString());
            assertEquals(0, RBT.root.rightChild.rightChild.rightChild.blackHeight);

            RBT.insert(Mexico, 30);

            expected = "[ Ethiopia: 12, America: 10, Denmark: 18,  Australia: 5,  Germany: 11, France: 16, Denmark: 20, Mexico: 30 ]";

            assertEquals("[ Ethiopia: 12, America: 10, Denmark: 18, Australia: 5, Germany: 11, France: 16, Denmark: 20, Mexico: 30 ]", RBT.toLevelOrderString());
            assertEquals(0, RBT.root.rightChild.rightChild.rightChild.blackHeight);
            assertEquals(1, RBT.root.rightChild.leftChild.blackHeight);

        }
        {

            //test remove black sib, null uncle, interior node
            RedBlackTree RBT = new RedBlackTree();

            RBT.insert(America, 10);
            RBT.insert(Australia, 5);
            RBT.insert(Mexico, 30);
            RBT.insert(Denmark, 1);
            RBT.insert(France, 7);
            RBT.insert(Germany, 25);
            RBT.insert(China, 40);
            RBT.insert(Netherlands, 20);
            RBT.insert(Poland, 28);


            RBT.remove(RBT.root.rightChild);

            String expected = "[ America: 10, Australia: 5, Poland: 28, Denmark: 1, France: 7, Germany: 25, China: 40, Netherlands: 20 ]";


            assertEquals(expected, RBT.toLevelOrderString());
            assertEquals(1, RBT.root.rightChild.leftChild.blackHeight);
            assertEquals(0, RBT.root.rightChild.leftChild.leftChild.blackHeight);
        }
    }

    /**
     * This test focuses on inserting countries with key collisions
     */
    @Test
    public void test4() {

        //test remove black sib, null uncle, interior node
        RedBlackTree RBT = new RedBlackTree();

        RBT.insert(America, 10);
        RBT.insert(Australia,5);
        RBT.insert(Mexico,30);
        RBT.insert(Denmark,10);



        assertEquals("[ America: 10, Australia: 5, Mexico: 30, Denmark: 10 ]", RBT.toLevelOrderString());
        assertEquals("[ Australia, America, Denmark, Mexico ]", RBT.toInOrderString());
        assertEquals(0, RBT.root.rightChild.leftChild.blackHeight);
        assertEquals(1, RBT.root.leftChild.blackHeight);
    }
    /*
    @Test
    void test4() {
        //Single right rotation about root
        int red = 0;
        int black = 1;

        AlgoEngrCountry france = new AlgoEngrCountry(4);
        AlgoEngrCountry USA = new AlgoEngrCountry(10);
        AlgoEngrCountry sweeden = new AlgoEngrCountry(6);


        RedBlackTree RBT = new RedBlackTree();

        RBT.insert(USA.getGoldMedals());
        RBT.insert(sweeden.getGoldMedals());
        RBT.insert(france.getGoldMedals());

        String result = RBT.toLevelOrderString();

        assertEquals("[ 6, 4, 8 ]", result);
        assertEquals(red, RBT.root.leftChild.blackHeight);
        assertEquals(red, RBT.root.rightChild.blackHeight);

        //Single right rotation about GrandParent
        RBT.insert(10);
        RBT.insert(12);

        result = RBT.toLevelOrderString();

        assertEquals("[ 6, 4, 10, 8, 12 ]", result);
        assertEquals(black, RBT.root.leftChild.blackHeight);
        assertEquals(black, RBT.root.rightChild.blackHeight);
        assertEquals(red, RBT.root.rightChild.leftChild.blackHeight);
        assertEquals(red, RBT.root.rightChild.rightChild.blackHeight);

        //Left Right Rotation
        RBT.insert(2);
        RBT.insert(3);

        result = RBT.toLevelOrderString();
        assertEquals("[ 6, 3, 10, 2, 4, 8, 12 ]", result);
        assertEquals(black, RBT.root.leftChild.leftChild.blackHeight);
        assertEquals(black, RBT.root.leftChild.rightChild.blackHeight);
        assertEquals(red, RBT.root.leftChild.blackHeight);
    }
    void test5() {

        int black = 1;
        int red = 0;
        //single rotate around root node test
        {
            RedBlackTree RBT = new RedBlackTree();
            RBT.insert(5);
            RBT.insert(8);

            assertEquals(5, RBT.root.data);
            assertEquals(black, RBT.root.blackHeight);
            assertEquals(red, RBT.root.rightChild.blackHeight);

            RBT.insert(10);

            assertEquals(8, RBT.root.data);
            assertEquals(red, RBT.root.leftChild.blackHeight);
            assertEquals(red, RBT.root.rightChild.blackHeight);
        }

        //double rotate around root test
        {
            RedBlackTree RBT = new RedBlackTree();
            RBT.insert(5);
            RBT.insert(8);

            RBT.insert(7);
            assertEquals(7, RBT.root.data);
            assertEquals(red, RBT.root.leftChild.blackHeight);
            assertEquals(red, RBT.root.rightChild.blackHeight);
        }
        //adding to a non-root black parent
        {
            RedBlackTree RBT = new RedBlackTree();
            RBT.insert(5);
            RBT.insert(8);
            RBT.insert(10);

            //adding to red child
            RBT.insert(12);
            assertEquals(black, RBT.root.leftChild.blackHeight);
            assertEquals(black, RBT.root.rightChild.blackHeight);
            assertEquals(red, RBT.root.rightChild.rightChild.blackHeight);

            //adding to black child
            RBT.insert(2);
            assertEquals(red, RBT.root.leftChild.leftChild.blackHeight);

        }*/

    }



