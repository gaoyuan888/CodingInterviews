/**
 * ��������:
 *
 * @author yaoyizhou
 * @date 2019/9/11 18:33
 * @desc
 */
public class Test06_1 {

    /**
     * �������ڵ���
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
        // ����������������
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        // ����������������
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = reconstructe(preOrder, inOrder);
        printPostOrder(root); // �����ӡ������
    }

    /**
     * ����ǰ����������������ɶ��������ؽ�
     *
     * @param preOrder ǰ���������
     * @param inOrder  �����������
     */
    public static BinaryTreeNode reconstructe(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length == 0 || inOrder.length == 0 || preOrder.length != inOrder.length) {
            return null;
        }

        // �������ĸ��ڵ�
        BinaryTreeNode root = new BinaryTreeNode(preOrder[0]);
        root.setLeft(null);
        root.setRight(null);

        // �������ĸ���
        int leftNum = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (root.getValue() == inOrder[i]) {
                break;
            } else {
                leftNum++;
            }
        }
        // �������ĸ���
        int rightNum = inOrder.length - 1 - leftNum;

        // �ؽ�������
        if (leftNum > 0) {
            //����������������
            int[] leftPreOrder = new int[leftNum];
            //����������������
            int[] leftInOrder = new int[leftNum];
            for (int i = 0; i < leftNum; i++) {
                leftPreOrder[i] = preOrder[i + 1];
                leftInOrder[i] = inOrder[i];
            }
            BinaryTreeNode leftRoot = reconstructe(leftPreOrder, leftInOrder); // �ݹ鹹��������
            root.setLeft(leftRoot);
        }

        // �ع�������
        if (rightNum > 0) {
            //����������������
            int[] rightPreOrder = new int[rightNum];
            //����������������
            int[] rightInOrder = new int[rightNum];
            for (int i = 0; i < rightNum; i++) {
                rightPreOrder[i] = preOrder[leftNum + 1 + i];
                rightInOrder[i] = inOrder[leftNum + 1 + i];
            }
            BinaryTreeNode rightRoot = reconstructe(rightPreOrder, rightInOrder); // �ݹ鹹��������
            root.setRight(rightRoot);
        }
        return root;
    }

    /**
     * ����������������ݹ�ʵ�֣�
     */
    public static void printPostOrder(BinaryTreeNode root) {
        if (root != null) {
            printPostOrder(root.getLeft());
            printPostOrder(root.getRight());
            System.out.println(root.getValue());
        }
    }

}
