package tree.code_2025_7_21;

public class BuildTreeWithPostOrderAndInOrder {
    public static void main(String[] args) {
        BuildTreeWithPostOrderAndInOrder buildTreeWithPostOrderAndInOrder = new BuildTreeWithPostOrderAndInOrder();
        int[] inorder = {1,2};
        int[] pst = {2, 1};
        buildTreeWithPostOrderAndInOrder.buildTree(inorder, pst);
    }
    public int i;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        i = postorder .length - 1;
        return buildTree(inorder, 0, inorder.length - 1, postorder);
    }
    public TreeNode buildTree(int[] inorder, int l, int r, int[] postorder) {
        if (l == r) {
            return new TreeNode(postorder[i--]);
        }
        TreeNode node = new TreeNode(postorder[i]);
        // 在中序遍历序列中找根节点位置
        int rootIndex = -1;
        for (int j = l; j <=r ; j++) {
            if (postorder[i] == inorder[j]) {
                rootIndex = j;
                break;
            }
        }
        i--;
        if(rootIndex + 1 <= r) {
            TreeNode rTree = buildTree(inorder, rootIndex + 1, r, postorder);
            node.right = rTree;
        }
        if (l <= rootIndex - 1) {
            TreeNode lTree = buildTree(inorder, l, rootIndex - 1, postorder);
            node.left = lTree;
        }
        return node;
    }
}
