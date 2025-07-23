package tree.code_2025_7_23;

public class TreeStr {

    public String tree2str(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        tree2str(root, stringBuilder);
        return stringBuilder.toString();
    }
    public void tree2str(TreeNode root, StringBuilder str) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            str.append(root.val);
            return;
        }
        str.append(root.val);
        if (root.left == null) {
            str.append("()");
        } else {
            str.append('(');
            tree2str(root.left, str);
            str.append(')');
        }
        if (root.right != null) {
            str.append('(');
            tree2str(root.right, str);
            str.append(')');
        }
    }
}
