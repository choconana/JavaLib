package code.own.tongcheng58;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/14 20:44
 */
public class Main2 {
    public ArrayList<ArrayList<Integer>> printNode (TreeNode node) {
        // write code here
        LinkedList<TreeNode> list = new LinkedList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList<>();
        if (node == null) return res;
        list.add(node);
        int num = 1;
        while (!list.isEmpty()) {
            TreeNode curNode = list.pollFirst();
            cur.add(curNode.val);
            num--;
            if (curNode.left != null) {
                list.add(curNode.left);
            }
            if (curNode.right != null) {
                list.add(curNode.right);
            }
            if (num == 0) {
                num = list.size();
                res.add(cur);
                cur = new ArrayList<>();
            }
        }

        return res;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
}
