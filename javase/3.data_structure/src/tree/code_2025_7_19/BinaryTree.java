package tree.code_2025_7_19;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    static class TreeNode {
        public char val;
        public TreeNode left;//左孩子的引用
        public TreeNode right;//右孩子的引用

        public TreeNode(char val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode tree = binaryTree.createTree();
        binaryTree.preOrder(tree);
        System.out.println();
        binaryTree.inOrder(tree);
        System.out.println();
        binaryTree.postOrder(tree);
        System.out.println();
        System.out.println(binaryTree.size2(tree));
        TreeNode f = binaryTree.find(tree, 'f');
        System.out.println(f.val);
        binaryTree.getLeafNodeCount1(tree);
        System.out.println(BinaryTree.leafSize);
        System.out.println(binaryTree.getLeafNodeCount2(tree));
        System.out.println(binaryTree.isCompleteTree(tree));
        System.out.println(binaryTree.getHeight(tree));
        binaryTree.levelOrder(tree);
    }
    /**
     * 创建一棵二叉树 返回这棵树的根节点
     *
     * @return
     */
    public TreeNode createTree() {
        TreeNode n1 = new TreeNode('a');
        TreeNode n2 = new TreeNode('b');
        TreeNode n3 = new TreeNode('c');
        TreeNode n4 = new TreeNode('d');
        TreeNode n5 = new TreeNode('e');
        TreeNode n6 = new TreeNode('f');
        TreeNode n7 = new TreeNode('g');
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        return n1;
    }

    // 前序遍历
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历
    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        System.out.print(root.val + " ");
        preOrder(root.right);
    }

    // 后序遍历
    void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static int nodeSize;

    /**
     * 获取树中节点的个数：遍历思路
     */
    void size(TreeNode root) {
        if (root == null) {
            return;
        }
        nodeSize++;
        size(root.left);
        size(root.right);
    }

    /**
     * 获取节点的个数：子问题的思路
     *
     * @param root
     * @return
     */
    int size2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return size2(root.left) + size2(root.right) + 1;
    }


    /*
     获取叶子节点的个数：遍历思路
     */
    public static int leafSize = 0;

    void getLeafNodeCount1(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leafSize++;
        }
        getLeafNodeCount1(root.left);
        getLeafNodeCount1(root.right);
    }

    /*
     获取叶子节点的个数：子问题
     */
    int getLeafNodeCount2(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root == null) {
            return 0;
        }
        return getLeafNodeCount2(root.left) + getLeafNodeCount2(root.right);
    }

    /*
    获取第K层节点的个数
     */
    int getKLevelNodeCount(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curLevel = 1;
        while (curLevel != k) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                queue.offer(queue.peek().left);
                queue.offer(queue.peek().right);
                queue.poll();
            }
            curLevel++;
        }
        return queue.size();
    }

    /*
     获取二叉树的高度
     时间复杂度：O(N)
     */
    int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }


    // 检测值为value的元素是否存在
    TreeNode find(TreeNode root, char val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode nl = find(root.left, val);
        if (nl != null) {
            return nl;
        }
        TreeNode nr = find(root.right, val);
        if (nr != null) {
            return nr;
        }
        return null;
    }

    //层序遍历
    void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            if (n != null) {
                System.out.print(n.val + " ");
                queue.offer(n.left);
                queue.offer(n.right);
            }
        }
        System.out.println();
    }

    // 判断一棵树是不是完全二叉树
    boolean isCompleteTree(TreeNode root) {
        // 一颗完全二叉树的层序遍历队列最后全是Null，当遍历到null的时候判断队列中是不是只剩下null即可
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            if (n == null) {
                while (!queue.isEmpty()) {
                    if (queue.peek() == null) {
                        queue.poll();
                    } else {
                        return false;
                    }
                }
            } else {
                queue.offer(n.left);
                queue.offer(n.right);
            }
        }
        return true;
    }

}
