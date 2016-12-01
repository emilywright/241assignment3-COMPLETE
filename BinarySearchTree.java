/*

Names: Christian Brintnall, Emily Wright

Description: A Binary Search Tree object that can perform operations upon the tree.
             Most of the functions are adapted from our algorithms book to fit out own tree.

*/

import java.util.*;

public class BinarySearchTree {

  //Required variables for a BinarySearchTree
  public node root;

  //Size is really just used to ensure it is the same size as our other Data Structures
  public int size;

  //Creation of BinarySearchTree
  public BinarySearchTree() {
  }

  //Inserts a node into a tree, starts by checking if theres a root if not we insert the node there.
  public static void treeInsert(BinarySearchTree tree, node z) {

    node y = null;
    node x = tree.root;
    while (x != null) {
      y = x;
      if (z.occur < x.occur) {
        x = x.left;
      } else {
        x = x.right;
      }
    }
    z.parent = y;
    if (y == null) {
      tree.root = z;
    } else if (z.occur < y.occur) {
      y.left = z;
    } else {
      y.right = z;
    }
    tree.size++;
  }

  //Lets us see the size of the BST
  public static int bstSize(BinarySearchTree tree) {
    return tree.size;
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

  //Takes in a tree's root, and a key and returns the node with that key (occur).
  public static node treeSearch(node x, int key) {

    if ((x == null) || (key == x.occur)) {
      return x;
    }
    if (key < x.occur) {
      return treeSearch(x.left, key);
    } else {
      return treeSearch(x.right, key);
    }
  }

  //Takes in a node and finds the node containing the successor to that.
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

  public static node treePredecessor(node Node) {

        if (Node.left != null) {
            return treeMax(Node.left);
          }

        node y = Node.parent;
        node x = Node;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

  //Moves nodes u into node z's position
  public static void transplant(BinarySearchTree tree, node u, node v) {

    if (u.parent == null) {
      tree.root = v;
    } else if (u == u.parent.left) {
      u.parent.left = v;
    } else {
      u.parent.right = v;
    }
    if (v != null) {
      v.parent = u.parent;
    }
  }

  //Deletes a node from a tree, uses three cases to determine how this is performed.
  public static void treeDelete(BinarySearchTree tree, node z) {

    if (z.left == null) {
      transplant(tree, z, z.right);
    } else if (z.right == null) {
      transplant(tree, z, z.left);
    } else {
      node y = treeMin(z.right);
      if (y.parent != z) {
        transplant(tree, y, y.right);
        y.right = z.right;
        y.right.parent = y;
      }
      transplant(tree, z, y);
      y.left = z.left;
      y.left.parent = y;
    }
  }

  //Searches for a name in the tree, iteratively, when it's found it displays info about the females and the males with that name.
  public static node searchTreeName(node root, String key) {
	  if (root == null) {
		  return null;
	  }

    //We use a stack to search iteratively
	  Stack<node> nodeStack = new Stack<node>();
	  node currRoot = root;

	  while (currRoot != null) {
		  nodeStack.push(currRoot);
		  currRoot = currRoot.left;
	  }

	  while (nodeStack.size() > 0) {

		  node visitNode = nodeStack.pop();
		  if (visitNode.name.equals(key)) {
			  return visitNode;
		  }
		  if (visitNode.right != null) {
			  visitNode = visitNode.right;

			  while (visitNode != null) {
				  nodeStack.push(visitNode);
				  visitNode = visitNode.left;
			  }
		  }
	  }
  return null;
  }

  //Takes every node, stores it in the stack, pops them out and stores them into an array.
  //Once that array is full, and the stack is empty, we sort the array alphabetically and print it out.
  public static void DisplayName(node root, int total) {

    ArrayList<String> printList = new ArrayList<String>();
	  Stack<node> nodeStack = new Stack<node>();
	  node currRoot = root;

	  while (currRoot != null) {

		  nodeStack.push(currRoot);
		  currRoot = currRoot.left;
	  }

	  while (nodeStack.size() > 0) {

		  node visitNode = nodeStack.pop();
      printList.add(visitNode.name);
		  if (visitNode.right != null) {

			  visitNode = visitNode.right;
			  while (visitNode != null) {

				  nodeStack.push(visitNode);
				  visitNode = visitNode.left;
			  }
		  }
	  }

    String[] sortedArray = printList.toArray(new String[printList.size()]);
    Arrays.sort(sortedArray);

    for (int i = 0; i < sortedArray.length; i++) {
      node currName = searchTreeName(root, sortedArray[i]);
      System.out.print("Name: " + sortedArray[i] + ", Count: " + currName.occur + ",");
      System.out.printf(" Percentage: %.7f", ((float)currName.occur/ (float)total) * 100.00);
      System.out.print("%\n");
    }
  }

  //Returns an array with the necessary info about the names with the highest occurences.
  //This is done by finding the max occurence in the tree, and finding the Predecessors on
  //Each following node, which will give the top 5 occurences.
  public static node[] MostPopularName(node root) {

	  Stack<node> nodeStack = new Stack<node>();
    node[] returnArr;
    returnArr = new node[5];
	  returnArr[0] = treeMax(root);
    returnArr[1] = treePredecessor(returnArr[0]);
    returnArr[2] = treePredecessor(returnArr[1]);
    returnArr[3] = treePredecessor(returnArr[2]);
    returnArr[4] = treePredecessor(returnArr[3]);

    return returnArr;
  } // End MostPopularName

  //Finds the minimum amount of occurences in our tree, once that is done it finds all the unique names
  //That have that amount of occurences and prints them out.
  public static void UniqueName(node root, int total) {

	  Stack<node> nodeStack = new Stack<node>();
	  node currRoot = root;
    node minOccur = treeMin(root);
    LinkedList<node> printList = new LinkedList<node>();

	  while (currRoot != null) {

		  nodeStack.push(currRoot);
		  currRoot = currRoot.left;
	  }

	  while (nodeStack.size() > 0) {

		  node visitNode = nodeStack.pop();
      if (visitNode.occur == minOccur.occur) {
          printList.add(visitNode);
          if (printList.size() >= 5) {
            break;
          }
      }
		  if (visitNode.right != null) {

			  visitNode = visitNode.right;
			  while (visitNode != null) {

				  nodeStack.push(visitNode);
				  visitNode = visitNode.left;
			  }
		  }
	  }

    System.out.println("Name:     " + "Frequency:" + " Percentage:");
    for (int i = 0; i < printList.size(); i++) {
      System.out.print(printList.get(i).name);
      for (int z = 0; z < 10 -printList.get(i).name.length(); z++)  {
        System.out.print(" ");
      }
      System.out.print(printList.get(i).occur);
      for (int y = 0; y < 10 - String.valueOf(printList.get(i).occur).length(); y++) {
        System.out.print(" ");
      }
      System.out.printf(" %.7f", ((float)printList.get(i).occur/ (float)total) * 100.00);
      System.out.print("%\n");
    }
  } // End UniqueName
} //End BST
