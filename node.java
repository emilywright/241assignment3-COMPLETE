public class node {
  String name = new String();
  int age;
  boolean gender;
  node parent;
  node left;
  node right;

  //Constructs nodes for the tree, this includes pointers to other nodes
  public node(String name, int age, boolean gender, node parentNode, node leftChild, node rightChild){
    this.name = name;
    this.age = age;
    this.gender = false; // false represents male, true for female, this has to do with saving memory.
    this.parent = parentNode;
    this.left = leftChild;
    this.right = rightChild;
  }

  //This creates a node without the pointers
  public node (String name, int age, boolean gender) {
    this.name = name;
    this.age = age;
    this.gender = false;
  }

  //This creates an entirely blank node.
  public node() {
    }
  }
