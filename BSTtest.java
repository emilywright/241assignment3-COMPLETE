public class BSTtest {

  public static void main(String []args) {
    System.out.println("Testing creating BST/Nodes");
    BinarySearchTree testTree = new BinarySearchTree();
    node testNode1 = new node("TestNode1", 21, false);
    node testNode2 = new node("TestNode2", 63, true);
    node testNode3 = new node("TestNode3", 52, false);
    node testNode4 = new node("TestNode4", 92, true);
    node testNode5 = new node("TestNode5", 12, false);
    node testNode6 = new node("TestNode6", 65, true);
    System.out.println("BST/Nodes test complete");

    System.out.println("Testing insert...");
    BinarySearchTree.treeInsert(testTree, testNode1);
    BinarySearchTree.treeInsert(testTree, testNode2);
    BinarySearchTree.treeInsert(testTree, testNode3);
    BinarySearchTree.treeInsert(testTree, testNode4);
    BinarySearchTree.treeInsert(testTree, testNode5);
    BinarySearchTree.treeInsert(testTree, testNode6);
    System.out.println("Insert test complete!");

    System.out.println("Testing inorderTreeWalk..");
    BinarySearchTree.inorderTreeWalk(testTree.root);
    System.out.println("inorderTreeWalk complete!");

    System.out.println("Testing min/max nodes...");
    node min = BinarySearchTree.treeMin(testTree.root);
    System.out.println("Min is: " + min.age);

    node max = BinarySearchTree.treeMax(testTree.root);
    System.out.println("Max is: " + max.age);
    System.out.println("Min/max testing complete!");

    System.out.println("Testing search...");
    node searchTest = BinarySearchTree.treeSearch(testTree.root, 63);
    System.out.println("Searched node age: " + searchTest.age);
    System.out.println("Search test complete!");

    System.out.println("Testing find successor...");
    node succTest = BinarySearchTree.treeSuccessor(testNode2);
    System.out.println("The successor is: " + succTest.age);
    System.out.println("Successor test complete!");

    System.out.println("Testing transplant...");
    BinarySearchTree.transplant(testTree, testNode1, testNode6);
    System.out.println("Transplant test complete!");

    System.out.println("Testing treeDelete...");
    System.out.println("Deleting the root..");
    BinarySearchTree.treeDelete(testTree, testNode1);
    System.out.println("Root deleted! New tree:");
    BinarySearchTree.inorderTreeWalk(testTree.root);
    System.out.println("Deleting a leaf..");
    BinarySearchTree.treeDelete(testTree, testNode5);
    System.out.println("Leaf deleted! New tree: ");
    BinarySearchTree.inorderTreeWalk(testTree.root);
    System.out.println("Deleting a parent node...");
    BinarySearchTree.treeDelete(testTree, testNode2);
    System.out.println("Parent deleted! New tree: ");
    BinarySearchTree.inorderTreeWalk(testTree.root);

    System.out.println("All test complete!");
  }

}
