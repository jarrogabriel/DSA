package challenges.HackerRank.HR_TreeLevelOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrderTraversal {

    public static List<Integer> levelOrder(Node root) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        List<Integer> answer = new ArrayList<>();
        while (!nodes.isEmpty()) {
            Node curr = nodes.poll();
            if (curr != null) {
                answer.add(curr.data);

                if (curr.left != null) {
                    nodes.add(curr.left);
                }
                if (curr.right != null) {
                    nodes.add(curr.right);
                }
            }
        }
        return answer;

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


    public static void main(String[] args) {
        Node root = null;
        int[] values = {1, 2, 5, 3, 6, 4};
        for (int value : values) {
            root = insert(root, value);
        }
        System.out.print(levelOrder(root));
    }
}
