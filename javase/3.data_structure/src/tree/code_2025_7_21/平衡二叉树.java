package tree.code_2025_7_21;

public class 平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        if (isBalanced2(root) >= 0) {
            return true;
        }
        return false;
    }

    public int isBalanced2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int l = isBalanced2(root.left);
        int r = isBalanced2(root.right);
        if (l >= 0 && r >= 0) {
            if (Math.abs(l - r) <= 1) {
                return Math.max(l , r) + 1;
            }
        }
        return -1;
    }

}
