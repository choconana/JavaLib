package code.own.kingsoft;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/16 20:07
 */
public class Main2 {

    static TreeNode node1=new TreeNode(-1);
    static TreeNode node2=new TreeNode(-1);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        String[] strings = scanner.nextLine().split(" ");
        Integer[] nodes = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nodes[i] = Integer.parseInt(strings[i]);
        }
        node1 = new TreeNode(scanner.nextInt());
        node2 = new TreeNode(scanner.nextInt());
        TreeNode root = constructTree(nodes);
        TreeNode res = ancestor(root, node1, node2);

        System.out.println(res.val);
    }

    private static TreeNode ancestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) return root;
        TreeNode left = ancestor(root.left, node1, node2);
        TreeNode right = ancestor(root.right, node1, node2);
        if (left == null) return right;
        if (left == null) return left;
        return root;
    }

    private static TreeNode constructTree(Integer[] nodes) {
        if (nodes.length == 0) return new TreeNode(0);
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nodes[0]);
        queue.offer(root);
        TreeNode cur;
        int levelNodeNum = 2;
        int startIndex = 1;
        int len = nodes.length - 1;
        while (len > 0) {
            for (int i = startIndex; i < startIndex + levelNodeNum; i += 2) {
                if (i == nodes.length) return root;
                cur = queue.poll();
                if (nodes[i] != null) {
                    cur.left = new TreeNode(nodes[i]);
                    queue.offer(cur.left);
                }
                if (i + 1 == nodes.length) return root;
                if (nodes[i + 1] != null) {
                    cur.right = new TreeNode(nodes[i +1]);
                    queue.offer(cur.right);
                }
            }
            startIndex += levelNodeNum;
            len -= levelNodeNum;
            levelNodeNum = queue.size() * 2;
        }
        return root;
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
/*
3 5 6 -1 -1 2 7 -1 -1 4 -1 -1 1 9 -1 -1 8 -1 -1
5 1
 */