package tree.code_2025_7_21;

import java.util.*;

public class 二叉树的层序遍历II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int tmpsz = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < tmpsz; i++) {
                TreeNode tmpNode = queue.poll();
                list.add(tmpNode.val);
                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }
}
