/**
 * 功能描述:
 *
 * @author yaoyizhou
 * @date 2019/9/11 18:33
 * @desc
 */
public class Test06_1 {

    /**
     * 二叉树节点类
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public BinaryTreeNode getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode left) {
            this.left = left;
        }

        public BinaryTreeNode getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // 二叉树的先序序列
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        // 二叉树的中序序列
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = reconstructe(preOrder, inOrder);
        printPostOrder(root); // 后序打印二叉树
    }

    /**
     * 根据前序和中序遍历序列完成二叉树的重建
     *
     * @param preOrder 前序遍历序列
     * @param inOrder  中序遍历序列
     */
    public static BinaryTreeNode reconstructe(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length == 0 || inOrder.length == 0 || preOrder.length != inOrder.length) {
            return null;
        }

        // 二叉树的根节点
        BinaryTreeNode root = new BinaryTreeNode(preOrder[0]);
        root.setLeft(null);
        root.setRight(null);

        // 左子树的个数
        int leftNum = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (root.getValue() == inOrder[i]) {
                break;
            } else {
                leftNum++;
            }
        }
        // 右子树的个数
        int rightNum = inOrder.length - 1 - leftNum;

        // 重建左子树
        if (leftNum > 0) {
            //左子树的先序序列
            int[] leftPreOrder = new int[leftNum];
            //左子树的中序序列
            int[] leftInOrder = new int[leftNum];
            for (int i = 0; i < leftNum; i++) {
                leftPreOrder[i] = preOrder[i + 1];
                leftInOrder[i] = inOrder[i];
            }
            BinaryTreeNode leftRoot = reconstructe(leftPreOrder, leftInOrder); // 递归构建左子树
            root.setLeft(leftRoot);
        }

        // 重构右子树
        if (rightNum > 0) {
            //右子树的先序序列
            int[] rightPreOrder = new int[rightNum];
            //右子树的中序序列
            int[] rightInOrder = new int[rightNum];
            for (int i = 0; i < rightNum; i++) {
                rightPreOrder[i] = preOrder[leftNum + 1 + i];
                rightInOrder[i] = inOrder[leftNum + 1 + i];
            }
            BinaryTreeNode rightRoot = reconstructe(rightPreOrder, rightInOrder); // 递归构建右子树
            root.setRight(rightRoot);
        }
        return root;
    }

    /**
     * 后序遍历二叉树（递归实现）
     */
    public static void printPostOrder(BinaryTreeNode root) {
        if (root != null) {
            printPostOrder(root.getLeft());
            printPostOrder(root.getRight());
            System.out.println(root.getValue());
        }
    }

}
