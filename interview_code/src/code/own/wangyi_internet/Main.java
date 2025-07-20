package code.own.wangyi_internet;

import java.util.Scanner;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        this.val = x;
    }
}

public class Main {

    private static final TreeNode first = null;
    public static int res = 0;
    public static int numofcherry(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left != null && root.right != null && root.left.left == null &&
                root.left.right == null && root.right.left == null && root.right.right == null) {
            return res+1;
        }
        else {
            return res + numofcherry(root.left) + numofcherry(root.right);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String[] num = a.split(" ");
        int m = Integer.parseInt(num[0]);
        int n = Integer.parseInt(num[1]);
        //构建二叉树
        TreeNode[] tree = new TreeNode[m];
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            String[] s1 = s.split(" ");
            int parent = Integer.parseInt(s1[0]) - 1;
            String relation = s1[1];
            int child = Integer.parseInt(s1[2]) - 1;
            if (tree[parent] == null) {
                tree[parent] = new TreeNode(parent);
            }
            if (tree[child] == null) {
                tree[child] = new TreeNode(child);
            }
            if ("left".equals(relation)) {
                tree[parent].left = tree[child];
            } else {
                tree[parent].right = tree[child];
            }
        }
        in.close();

        // 寻找根结点
        boolean[] used = new boolean[m];
        for (int i = 0; i < tree.length; i++) {
            if (tree[i].left != null) {
                used[tree[i].left.val] = true;
            }
            if (tree[i].right != null) {
                used[tree[i].right.val] = true;
            }
        }

        int root = 0;
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                root = i;
                break;
            }
        }
        System.out.println(numofcherry(tree[root]));
    }

}
