package sort.code_2025_7_28;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(7);
        binarySearchTree.insert(2);
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(3);
        binarySearchTree.insert(1);
        binarySearchTree.insert(5);
        binarySearchTree.inorder(binarySearchTree.root);
        System.out.println();

        // 删除key为4的节点
        binarySearchTree.remove(4);
        binarySearchTree.inorder(binarySearchTree.root);
        System.out.println();

        // 删除key为7的节点
        binarySearchTree.remove(7);
        binarySearchTree.inorder(binarySearchTree.root);
        System.out.println();
    }

    static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int key) {
            this.key = key;
        }
    }

    public TreeNode root;

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.key + "\t");
        inorder(root.right);
    }

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * 插入一个元素
     * @param key
     */
    public boolean insert(int key) {
        TreeNode parent = null;
        TreeNode cur = root;
        while (cur != null) {
            parent = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        // 说明一个节点都没有，直接变成根节点
        if (parent == null) {
            root = new TreeNode(key);
        } else {
            if (key < parent.key) {
                parent.left = new TreeNode(key);
            } else {
                parent.right = new TreeNode(key);
            }
        }
        return true;
    }
    //查找key是否存在
    public TreeNode search(int key) {
        TreeNode cur = null;
        while (cur != null) {
            if (cur.key == key) {
                return cur;
            } else if (cur.key > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }
    //删除key的值
    public boolean remove(int key) {
        TreeNode cur = root;
        TreeNode parent = null;
        // 找目标节点
        while (cur != null) {
            if (key < cur.key) {
                parent = cur;
                cur = cur.left;
            } else if (key > cur.key){
                parent = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        // 要删除的节点不存在就直接返回false
        if (cur == null) {
            return false;
        }
        //1.要删除的节点有左子树和右子树
        if (cur.left != null && cur.right != null) {
            TreeNode tp = cur; // t节点的父亲节点
            TreeNode t = cur.left;  // t节点是要真正被删除的节点
            // 1.1左边找到最大节点的值
            while (t.right != null) {
                tp = t;
                t = t.right;
            }
            // 1.2交换关键字
            cur.key = t.key;
            // 1.3删除交换过的节点
            if (tp == cur) {
                tp.left = t.left;
            } else {
                tp.right = t.left;
            }
        }
        //2.要删除的节点只有左子树或者右子树
        if (cur.left != null && cur.right == null) {
            // 要删除的节点是根节点
            if (parent == null) {
                root = cur.left;
            }
            // 要删除的节点不是根节点
            if (parent != null && parent.left == cur) {
                parent.left = cur.left;
            }
            if (parent != null && parent.right == cur) {
                parent.right = cur.left;
            }
        }
        if (cur.left == null && cur.right != null) {
            // 要删除的节点是根节点
            if (parent == null) {
                root = cur.right;
            }
            // 要删除的节点不是根节点
            if (parent != null && parent.left == cur) {
                parent.left = cur.right;
            }
            if (parent != null && parent.right == cur) {
                parent.right = cur.right;
            }
        }
        // 3.要删除的节点是叶子节点
        if (cur.left == null && cur.right == null) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.left == cur) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
        return false;
    }


}
