public class BinarySearchTree<T extends Comparable<T>> {

private NodeType<T> root;

    // inserts the new root into the bst
    public void insert(T key){

        if (this.root == null) {

            this.root = new NodeType<>(key);

        } else {

            insertNodeHelper(key, this.root);

        }
    } // insert

    // helper function that simplifies the recursive calls
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

            System.out.println("The item already exists in the tree");

        }
    } // insertNodeHelper





    public boolean search(T item) {

        return searchHelper(root, item);

    } // search

    // helps with recursion
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

    public void inOrder() {

        inOrderHelper(root);

    } // inOrder

    private void inOrderHelper(NodeType<T> node) {

        if (node != null) {

            inOrderHelper(node.left);
            System.out.println(node.info);
            inOrderHelper(node.right);
        }

    } // inOrderHelper

    public void delete(T key) {

        if (search(key) == true) {

            deleteHelper(root, key);

        } else {

            System.out.println("The number is not present in the tree");
        }
    } // delete

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

    private T successor(NodeType<T> node) {

        node = node.right;

        while (node.left != null) {

            node = node.left;

        }
        return node.info;
    }

    private T predecessor(NodeType<T> node) {

        node = node.left;

        while (node.right != null) {

            node = node.right;
        }

        return node.info;
    }

    public int getNumLeafNodes() {

        int leaves = getNumLeafNodesHelper(root);

        return leaves;

    } // getNumLeafNodes

    private int getNumLeafNodesHelper(NodeType<T> node) {

        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        return getNumLeafNodesHelper(node.left) + getNumLeafNodesHelper(node.right);
    } // getNumLeafNodesHelper

    public int getSingleParent() {

        int parents = getSingleParentHelper(root);
        return parents;

    } // getSingleParent

    private int getSingleParentHelper(NodeType<T> node) {

        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right != null || node.right == null && node.left != null) {
            return 1;
        }

        return getSingleParentHelper(node.left) + getSingleParentHelper(node.right);
    } // getSingleParentHelper


    public void getCousin(T key) {

        int level = treeLevel(root, key, 1);
        NodeType<T> parent = getParent(root, key);
        getCousinHelper(root, key, level, parent);


    } // getCousin

    private void getCousinHelper(NodeType<T> node, T key, int level, NodeType<T> parent) {

        if (node == null || level < 2) {
        return;
    }


    if (level == 2) {

        if (node.left != null && node.left != parent) {

            System.out.print(node.left.info + " ");

        }

        if (node.right != null && node.right != parent) {

            System.out.print(node.right.info + " ");
        }

    } else {

        getCousinHelper(node.left, key, level - 1, parent);

        getCousinHelper(node.right, key, level - 1, parent);
    }

     } // getCousinHelper





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
