public class BinarySearchTree<T extends Comparable<T>> {

    private NodeType<T> root;
    private boolean pp = false;

    /**
     * this method inserts the new root into the binary search tree
     * @param T key is the item in the node which is generic
     */
    public void insert(T key){

        if (this.root == null) {

            this.root = new NodeType<>(key);

        } else {

            insertNodeHelper(key, this.root);

        }
    } // insert

    /**
     * helper function that simplifies the recursive calls for insert
     * @param key is the item in the node
     * @param root is the node itself
     */
    private void insertNodeHelper(T key, NodeType<T> node){


        if (node.info.compareTo(key) > 0) {

            if (node.left == null) {

                node.left = new NodeType<>(key);

            } else {

                insertNodeHelper(key, node.left);
            }

        } else if (node.info.compareTo(key) < 0) {

            if (node.right == null) {

                node.right = new NodeType<>(key);

            } else {

                insertNodeHelper(key, node.right);

            }

        } else {

            System.out.println("The item already exists in the tree.");

        }
    } // insertNodeHelper




    /**
     * searches for the item within the binary search tree
     * @item is what we are searching for
     */
    public boolean search(T item) {

        return searchHelper(root, item);

    } // search

    /**
     *
     * this method helps with recursion for search
     * @param node is the node
     * @param item is what we are searching for
     */
    private boolean searchHelper(NodeType<T> node, T item){

        if (node == null) {

            return false;

        } else if (node.info.compareTo(item) == 0) {

            return true;

        } else if (node.info.compareTo(item) > 0 ) {

            return searchHelper(node.left, item);

        } else {

            return searchHelper(node.right, item);
        }


    } // searchHelper

    /**
     * this method displays the binary search tree in order
     */
    public void inOrder() {

        inOrderHelper(root);

    } // inOrder


    /**
     * this is a helper method for the inOrder method
     * @param node is nodes are are being sorted
     */
    private void inOrderHelper(NodeType<T> node) {

        if (node != null) {

            inOrderHelper(node.left);
            System.out.print(node.info + " ");
            inOrderHelper(node.right);
        }

    } // inOrderHelper

    /**
     * The delete method deletes the node in a binary search tree
     * @param key is a generic item that is used to find the node to delete
     */
    public void delete(T key) {

        if (search(key) == true) {

            deleteHelper(root, key);

        } else {

            System.out.println("The number is not present in the tree");
        }
    } // delete


    /**
     * this method is a helper for the delete method
     * @param node is the traverse through the binary search tree
     * @param key is what we are trying to delete
     */
    private NodeType<T> deleteHelper(NodeType<T> node, T key) {

        if (node == null) {

            return null;

        } else if (node.info.compareTo(key) > 0) {

            node.left = deleteHelper(node.left,key);

        } else if (node.info.compareTo(key) < 0) {

            node.right = deleteHelper(node.right,key);

        } else {

            if (node.left == null && node.right == null) {

                return null;

            } else if (node.right != null) {

                node.info = successor(node);
                node.right = deleteHelper(node.right, node.info);

            } else {

                node.info = predecessor(node);
                node.left = deleteHelper(node.left,node.info);

            }
        }

        return node;
    } // deleteHelper


    /**
     * successor is a helper method that is used in the delete helper method
     * @param node is used to traverse through the binary search tree
     */
    private T successor(NodeType<T> node) {

        node = node.right;

        while (node.left != null) {

            node = node.left;

        }
        return node.info;
    }


    /**
     * predeccessor is a helper method that is used in the delete helper method
     * @param node is used to traverse through the binary search tree
     */
    private T predecessor(NodeType<T> node) {

        node = node.left;

        while (node.right != null) {

            node = node.right;
        }

        return node.info;
    }

    /**
     * this returns the amount of leaf nodes in the binary search tree
     *
     */
    public int getNumLeafNodes() {

        int leaves = getNumLeafNodesHelper(root);

        return leaves;

    } // getNumLeafNodes

    /**
     * helper method for the getNumLeafNodes
     * @param node used for traversal through the binary search tree
     */
    private int getNumLeafNodesHelper(NodeType<T> node) {

        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        return getNumLeafNodesHelper(node.left) + getNumLeafNodesHelper(node.right);
    } // getNumLeafNodesHelper


    /**
     * getSingleParent method finds and prints teh singleParents within the binary search tree
     *
     */
    public void getSingleParent() {
        pp = false;
        //int parents = getSingleParentHelper(root);
        // return parents;

        getSingleParentHelper(root);
        if (pp != true) {

            System.out.println("None");

        }
    } // getSingleParent

    /**
     * this is a helper method for the gsph method and prints out the items
     * @param node is used to traverse and find the parent nodes
     */
    private void getSingleParentHelper(NodeType<T> node) {

        if (node == null) {

            return;
        }
        if (node.left == null && node.right != null || node.right == null && node.left != null) {

            System.out.print(node.info + " ");
            pp = true;
            return;
        }



        getSingleParentHelper(node.left);
        getSingleParentHelper(node.right);
    } // getSingleParentHelper

    /**
     * finds the cousins of the nodes in the binary search tree
     * @param key is what we are finding the cousins of
     */
    public void getCousin(T key) {

        int level = treeLevel(root, key, 1);
        NodeType<T> parent = getParent(root, key);
        getCousinHelper(root, key, level, parent);


    } // getCousin

    /**
     * helper method for the getCousin method
     * @param node is used to traverse and find the cousin
     * @param key is used to identify the node we are comparing and finding the cousin of
     * @param is the levels within the binary search trees as an index to traverse and find cousins
     * @param is the parent node used to find the cousins
     */
    private void getCousinHelper(NodeType<T> node, T key, int level, NodeType<T> parent) {

        if (node == null || level < 2) {
        return;
    }


    if (level == 2) {

        if (node.left != null && node.left != parent && !node.left.info.equals(key)
        && (node.left != parent.left && node.left != parent.right)) {

            System.out.print(node.left.info + " ");

        }

        if (node.right != null && node.right != parent && !node.right.info.equals(key)
        && (node.left != parent.left && node.left != parent.right)) {

            System.out.print(node.right.info + " ");
        }

    } else {

        getCousinHelper(node.left, key, level - 1, parent);

        getCousinHelper(node.right, key, level - 1, parent);
    }

     } // getCousinHelper




    /**
     * determines the level of the item in the binary tree
     * @param node is used to traverse and find the cousin
     * @param key is what we are looking for the cousin of
     * @param is the level in which you are on teh binary search tree
     */
    private int treeLevel (NodeType<T> node, T key, int level ) {

        if (node == null) {

            return -1;

        } // if

        if (node.info.equals(key)) {
            return level;

        } // if

        int lefty = treeLevel(node.left, key, level + 1);
        if (lefty != -1) return lefty;

        int righty = treeLevel(node.right, key, level + 1);

        return righty;

    } // treeLevel

    /**
     * Helper method of the cousin method used to determin the parent
     */
    private NodeType<T> getParent(NodeType<T> node, T key) {

        if (node == null || (node.left == null && node.right == null)) {
            return null;

        }

        if ((node.left != null && node.left.info.equals(key)) ||
            (node.right != null && node.right.info.equals(key))) {
            return node;
        }
        NodeType<T> rightParent = getParent(node.right, key);
        NodeType<T> leftParent = getParent(node.left, key);
        if (leftParent != null) {

            return leftParent;
        } else if (rightParent != null) {

            return rightParent;

        } else {// if

        return null;
        }
    } // getParent


}; // BinaryTree
