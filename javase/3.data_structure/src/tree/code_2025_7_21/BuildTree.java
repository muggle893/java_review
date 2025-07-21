package tree.code_2025_7_21;

import java.util.Scanner;

public class BuildTree {
    static class TreeNode {
        BuildTree.TreeNode left;
        BuildTree.TreeNode right;
        char val;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        TreeNode root = buildBinTree(s);
        inOrder(root);

    }
    public static int pointer = 0;
    public static TreeNode buildBinTree(String s) {
        if (s.charAt(pointer) == '#') {
            pointer++;
            return null;
        }
        TreeNode treeNode = new TreeNode(s.charAt(pointer));
        pointer++;
        TreeNode l = buildBinTree(s);
        treeNode.left = l;
        TreeNode r = buildBinTree(s);
        treeNode.right = r;
        return treeNode;
    }

    public static  void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
}
