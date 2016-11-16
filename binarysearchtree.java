public class BinarySearchTree {

  private class node {
		String name = new String();
    int age;
		node parent;
    node left;
    node right;

    //Constructs nodes for the tree
    public node(String name, int age, node parentNode, node leftChild, node rightChild){
        this.name = name;
        this.age = age;
        this.parent = parentNode;
        this.left = leftChild;
        this.right = rightChild;
    }

    public node() {

    }
	}

  //Required variables for a BinarySearchTree
  private node root;

  //Creation of BinarySearchTree
  public BinarySearchTree() {
    node root;
  }

  //Inserts a node into a tree, starts by checking if theres a root if not we insert the node there.
  public void treeInsert(BinarySearchTree tree, node leaf) {
    if (tree.root == null) {
      tree.root = leaf;
    }

  }
}
