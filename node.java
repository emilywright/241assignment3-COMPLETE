public class node {
  String name = new String();
  int occur;
  char gender;
  node parent;
  node left;
  node right;

  //Constructs nodes for the tree, this includes pointers to other nodes
  public node(String name, int occur, char gender, node parentNode, node leftChild, node rightChild){
    this.name = name;
    this.occur = occur;
    this.gender = gender; // false represents male, true for female, this has to do with saving memory.
    this.parent = parentNode;
    this.left = leftChild;
    this.right = rightChild;
  }

  //This creates a node without the pointers
  public node (String name, int occur, char gender) {
    this.name = name;
    this.occur = occur;
    this.gender = gender;
  }

  //This creates a node for the HashMap
  public node (int occur, char gender) {
    this.occur = occur;
    this.gender = gender;
  }

  //This creates an entirely blank node.
  public node() {
    }
  }
