public class BinarySearchTree {

  //Required variables for a BinarySearchTree
  public node root;

  //Creation of BinarySearchTree
  public BinarySearchTree() {
  }

  //Inserts a node into a tree, starts by checking if theres a root if not we insert the node there.
  //This algorithm is directly adapted from our textbook.
  public static void treeInsert(BinarySearchTree tree, node z) {
    node y = null;
    node x = tree.root;
    while (x != null) {
      y = x;
      if (z.age < x.age) {
        x = x.left;
      } else {
        x = x.right;
      }
    }
    z.parent = y;
    if (y == null) {
      tree.root = z;
    } else if (z.age < y.age) {
      y.left = z;
    } else {
      y.right = z;
    }
  }

  //Can search through the entire tree and print out each node.
  public static void inorderTreeWalk(node treeRoot) {
    if (treeRoot != null) {
      inorderTreeWalk(treeRoot.left);
      System.out.println(treeRoot.name + ", " + treeRoot.age + ", " + treeRoot.gender);
      inorderTreeWalk(treeRoot.right);
    }
  }

  //Pass in the root to find the min of the tree.
  public static node treeMin(node x) {
    while (x.left != null) {
      x = x.left;
    }
    return x;
  }

  //Pass in the root to find the max of a tree
  public static node treeMax(node x) {
    while (x.right != null) {
      x = x.right;
    }
    return x;
  }

  public static node treeSearch(node x, int key) {
    if ((x == null) || (key == x.age)) {
      return x;
    }
    if (key < x.age) {
      return treeSearch(x.left, key);
    } else {
      return treeSearch(x.right, key);
    }
  }

  public static node treeSuccessor(node x) {
    if (x.right != null) {
      return treeMin(x.right);
    }
    node y = x.parent;
    while((y != null) && (x == y.right)) {
      x = y;
      y = y.parent;
    }
    return y;
  }

  

}
