package tree.code_2025_7_23;

import java.util.ArrayList;
import java.util.List;

public class SumFromRoot2Leaf {
    public int sumNumbers(TreeNode root) {
        sumNumbers2(root);
        return total;
    }
    public int total = 0;
    List<Integer> path = new ArrayList<>();
    public void sumNumbers2(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            int pathSum = 0;
            pathSum += path.get(0);
            for (int i = 1; i < path.size(); i++) {
                pathSum = pathSum * 10 + path.get(i);
            }
            pathSum = pathSum * 10 + root.val;
            total += pathSum;
            return;
        }
        path.add(root.val);
        sumNumbers2(root.left);
        sumNumbers2(root.right);
        path.remove(path.size() - 1);
    }
}
