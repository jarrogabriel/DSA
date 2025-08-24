package challenges.HackerRankTests.HR_TreeLevelOrderTraversal;


import challenges.HackerRank.HR_TreeLevelOrderTraversal.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static challenges.HackerRank.HR_TreeLevelOrderTraversal.TreeLevelOrderTraversal.levelOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeLevelOrderTraversalTest {


    @Test
    void testLevelOrderTraversal() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        List<Integer> result = levelOrder(root);
        assertEquals(List.of(1, 2, 3, 4, 5), result);
    }


    @Test
    void testSingleElementTree() {
        Node root = new Node(42);
        List<Integer> result = levelOrder(root);
        assertEquals(List.of(42), result);
    }

}
