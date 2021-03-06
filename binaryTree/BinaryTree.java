public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;

    public static int sumTree(Node<Integer> root) {

    if(root== null) {
	return 0;
    }
       
    int center = 0;

    if( root.item % 2 == 0) { 

	center = root.item;

    }

    int left = sumTree(root.left);

    int right = sumTree(root.right);

    return center + left + right;

    }

    public static int numberOfChars(Node<String> root) {

	if(root == null) {

	    return 0;

	}

	//add the length of my word and my left child and my right child
	return root.item.length() + numberOfChars(root.left) + numberOfChars(root.right);
        
    }
    

    public BinaryTree() {

	this.root = null;

    }

    public int size() {

        return this.size(this.root);
    }


    private int size(Node<E> root) {

	if(root == null) {

	    return 0;
        }

	return 1 + size(root.left)+ size(root.right);

    }

    public void add(E item) {

	this.root = add(this.root, item);

    }

    private Node<E> add(Node<E> root, E item) {

	if (root == null) {

	    return new Node<E>(item);

	}

	int comparisonResult = item.compareTo(root.item);

	if (comparisonResult == 0) {
            root.left = add(root.left, item);
            return root;
        } else if (comparisonResult < 0) {
            root.left = add(root.left, item);
            return root;
        } else {
            root.right = add(root.right, item);
            return root;
        }

    }

    public void remove(E item) {

	this.root = remove(this.root, item);

    }


    private Node<E> remove(Node<E> root, E item) {

	if (root == null) {

	    return null;

	}

	int comparisonResult = item.compareTo(root.item);

	if (comparisonResult < 0) {
            root.left = remove(root.left, item);
            return root;
        } else if (comparisonResult > 0) {
            root.right = remove(root.right, item);
            return root;
        } else {  // root is the item we want to delete
	    
            // case 1, root is leaf
            if (root.left == null && root.right == null) {

		return null;

	    } // case 2, root has only left child

	    else if (root.left != null && root.right == null) {

		return root.left;

	    } else if (root.left == null && root.right != null) {

		return root.right;

	    } else {

		Node<E> rootOfLeftSubtree = root.left;
                Node<E> parentOfPredecessor = null;
                Node<E> predecessor = null;

                if (rootOfLeftSubtree.right == null) {

		    root.item = rootOfLeftSubtree.item;
                    root.left = rootOfLeftSubtree.left;
                    return root;

		} else {

		    parentOfPredecessor = rootOfLeftSubtree;
                    Node<E> current = rootOfLeftSubtree.right;
                    while (current.right != null) {
                        parentOfPredecessor = current;
                        current = current.right;

		    }

                    predecessor = current;
                    root.item = predecessor.item;
                    parentOfPredecessor.right = predecessor.left;
                    return root;

                }
            }

        }
    }

    public boolean contains(E item) {

	return contains(this.root, item);

    }

    private boolean contains(Node<E> root, E item) {

	if (root == null) {

	    return false;

	}

	int comparisonResult = item.compareTo(root.item);

	if (comparisonResult == 0) {

	    return true;

	} else if (comparisonResult < 0) {

	    return contains(root.left, item);

	} else {

	    return contains(root.right, item);

	}

    }


    public String toString() {

	StringBuilder sb = new StringBuilder();

	preOrderTraverse(root, 1, sb);

	return sb.toString();

    }


    private String toString(Node<E> root) {
        if (root == null) {
            return "";
        }
        String output = "";

        
        output += toString(root.left);
        output += root.item + " ";
        output += toString(root.right);
        return output;

    }
    
    //right rotation added here

    public Node<E> rightRotate(Node<E> root){

	//identify the pivot first

	Node<E> pivot = root.left;

	//moves root's left subtree to become pivot's right
	root.left = pivot.right;

	//makes the pivot to become the new root of the entire tree
	pivot.right = root;
	root = pivot;

	return pivot;
    }



    private void preOrderTraverse(Node<E> root, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  "); // indentation
        }
        if (root == null) {
            sb.append("null\n");
        } else {
            sb.append(root.toString());
            sb.append("\n");
            preOrderTraverse(root.left, depth + 1, sb);
            preOrderTraverse(root.right, depth + 1, sb);
        }
    }

    private static class Node<E extends Comparable<E>> {
        private E item;
        private Node<E> left;  // left child
        private Node<E> right; // right child

        public Node(E item) {
            this.item = item;
        }


        public String toString() {
            return item.toString();
        }
    }



    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(5);
        tree.add(1);
        tree.add(0);
        tree.add(2);

        tree.add(4);
        tree.add(3);

        tree.add(12);
        tree.add(7);
        tree.add(15);
        tree.add(14);
        tree.add(20);

        tree.remove(1);

        System.out.println(tree);
	System.out.println("Before right rotation!");
	System.out.println(tree);

	tree.root = tree.rightRotate(tree.root);
	System.out.println("After right rotation!");
	System.out.println(tree);
    }

}
