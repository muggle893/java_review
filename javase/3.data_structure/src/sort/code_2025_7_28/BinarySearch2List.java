package sort.code_2025_7_28;

class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;

     }
 }
public class BinarySearch2List {
    TreeNode prev = null;
    TreeNode cur = null;
    TreeNode head = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        cur = pRootOfTree;
        Convert2(pRootOfTree);
        return head;
    }
    public void Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return;
        }
        Convert2(pRootOfTree.left);
        TreeNode tmp = pRootOfTree.right;
        if (prev == null) {
            head = pRootOfTree;
            prev = pRootOfTree;
            head.left = null;
            head.right = null;
        } else {
            prev.right = pRootOfTree;
            pRootOfTree.left = prev;
            pRootOfTree.right = null;
            prev = pRootOfTree;
        }
        Convert2(tmp);
    }
}
