package tree.code_2025_7_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrder {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);
        cur = cur.left;
        while (!stack.isEmpty()) {
            // cur!= null就要进栈，然后更新cur
            // cur为null要判断是否要出栈顶元素了，或者是回到父亲节点继续遍历右孩子
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                if (stack.peek().right == null) {
                    TreeNode popNode = stack.pop();
                    res.add(popNode.val);
                    while (!stack.isEmpty() && popNode == stack.peek().right) {
                        popNode = stack.pop();
                        res.add(popNode.val);
                    }
                    // 更新cur
                    if (!stack.isEmpty()) {
                        cur = stack.peek().right;
                    }
                } else {
                    cur = stack.peek().right;
                }
            }
        }
        return res;
    }
}
