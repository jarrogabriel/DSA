package challenges.HR_HeightBinaryTree;


public class HeightBinaryTree {

    public static void main(String[] args) {
        Node root = null;
        int[] values = {3, 5, 2, 1, 4, 6, 7};
        for (int value : values) {
            root = insert(root, value);
        }
        int height = height(root);
        System.out.println(height);
    }

    public static int height(Node root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
}
