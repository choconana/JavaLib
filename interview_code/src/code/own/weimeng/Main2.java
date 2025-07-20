package code.own.weimeng;

import java.util.ArrayList;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/10/11 16:39
 */
public class Main2 {
    private static ArrayList<Long> postorder = new ArrayList<>();
    public static void main(String[] args) {
        long[] preorderData = new long[]{3,9,20,15,7};
        long[] inorderData = new long[]{9,3,15,20,7};
        long[] postorder1 = buildPostOrder(preorderData, inorderData);
        for (long n : postorder1) {
            System.out.printf(n + ",");
        }
    }
    public static long[] buildPostOrder (long[] preorderData, long[] inorderData) {
        // write code here
        TreeNode root = buildTree(preorderData, 0, preorderData.length - 1, inorderData, 0, inorderData.length - 1);
        postOrderTraverse(root);
        long[] res = new long[postorder.size()];
        for (int i = 0; i < postorder.size(); i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    private static void postOrderTraverse(TreeNode root) {
        if (root == null) return;
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        postorder.add(root.val);

    }

    private static TreeNode buildTree(long[] preorderData, int prestart, int preend, long[] inorderData, int instart, int inend) {
        if (prestart > preend || instart > inend) return null;
        long rootval = preorderData[prestart];
        TreeNode root = new TreeNode(rootval);
        for (int i = instart; i <= inend; i++) {
            if (inorderData[i] == rootval) {
                root.left = buildTree(preorderData, prestart + 1, prestart + i -instart,
                        inorderData, instart, i - 1);
                root.right = buildTree(preorderData, prestart + i - instart + 1, preend,
                        inorderData, i + 1, inend);
            }
        }
        return root;
    }
}

class TreeNode {
    long val;
    TreeNode left;
    TreeNode right;
    TreeNode(long val) {
        this.val = val;
    }
}
