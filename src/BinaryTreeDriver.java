public class BinarySearchTreeDriver {




    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        tree.insert(6);
        tree.insert(3);
        tree.insert(2);
        tree.insert(9);
        tree.insert(7);
        //tree.insert(10);

        tree.inOrder();
        System.out.println();


        tree.inOrder();

        System.out.println();
        System.out.println(tree.getSingleParent());
    }
} // BinaryTreeDriver
