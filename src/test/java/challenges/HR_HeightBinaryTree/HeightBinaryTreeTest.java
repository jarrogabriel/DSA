package challenges.HR_HeightBinaryTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeightBinaryTreeTest {
    @Test
    void testEmptyTree() {
        Node root = null;
        assertEquals(-1, HeightBinaryTree.height(root));
    }

    @Test
    void testSingleNodeTree() {
        Node root = new Node(10);
        assertEquals(0, HeightBinaryTree.height(root));
    }

    @Test
    void testBalancedTree() {
        Node root = null;
        int[] values = {3, 5, 2, 1, 4, 6, 7};
        for (int value : values) {
            root = HeightBinaryTree.insert(root, value);
        }
        assertEquals(3, HeightBinaryTree.height(root));
    }

    @Test
    void testUnbalancedTree() {
        Node root = null;
        int[] values = {1, 2, 3, 4, 5};
        for (int value : values) {
            root = HeightBinaryTree.insert(root, value);
        }
        assertEquals(4, HeightBinaryTree.height(root));
    }
}
