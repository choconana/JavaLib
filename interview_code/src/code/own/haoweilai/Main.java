package code.own.haoweilai;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class Main {
    public String notReCuPreOrder (TreeNode root) {
        // write code here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            res.add(current.val);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i));
            if (i < res.size() - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }
}
