package tree.code_2025_7_21;

public class BuildTreeWithPreOrderAndInOrder {
    public int i;
    public TreeNode buildTree(int[] preOrder, int[] inorder) {
        i = 0;
        return buildTree(inorder, 0, inorder.length - 1, preOrder);
    }
    public TreeNode buildTree(int[] inorder, int l, int r, int[] preOrder) {
        if (l == r) {
            return new TreeNode(preOrder[i++]);
        }
        TreeNode node = new TreeNode(preOrder[i]);
        // 在中序遍历序列中找根节点位置
        int rootIndex = -1;
        for (int j = l; j <=r ; j++) {
            if (preOrder[i] == inorder[j]) {
                rootIndex = j;
                break;
            }
        }
        i++;
        if (l <= rootIndex - 1) {
            TreeNode lTree = buildTree(inorder, l, rootIndex - 1, preOrder);
            node.left = lTree;
        }
        if(rootIndex + 1 <= r) {
            TreeNode rTree = buildTree(inorder, rootIndex + 1, r, preOrder);
            node.right = rTree;
        }
        return node;
    }
}
