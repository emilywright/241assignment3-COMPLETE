/*

Names: Christian Brintnall, Emily Wright

Description: Handles the handle functions for names.java, this file is 100% Just
             to keep names.java looking clean.

Function descriptions:
 -SearchName for a name returns number of male and female babies born in that year who has that name.
  It should also return a rank for this name (how popular is this name for boys and girls).

 -MostPopularName returns the most popular 10 male and female baby names for a
  given year with their numbers and percentage of babies with that name.

 -UniqueName returns 5 male and female baby names that are unique with their frequency and percentage of babies with that name.
  For this method, ignore names that appear less than 5 times.

 -DisplayName prints the names in alphabetical order,
  and next to each name prints the number of male and female babies that have this
  name and percentage of babies (male and female) for that name.

*/


import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class handle {

  //This just serves to keep main nice and clean
  public static void handleTree(Scanner fileInfo) {

    System.out.println("You chose: Tree");
    BinarySearchTree maleTree = new BinarySearchTree();
    BinarySearchTree femaleTree = new BinarySearchTree();
    BinarySearchTree mainTree = new BinarySearchTree();
    int maleTotal = 0;
    int femaleTotal = 0;
    int maleCount = 0;
    int femaleCount = 0;

    //This while loads the BST with all necessary nodes..
    //It does this by using the comma as a delimeter and splits the info into an array.
    //It then creates three trees. A female, a male and a mixed. This is done to
    //Simplify later functions that require female and male into be pulled, as it should reduce run time a bit
    //But also simplifies our logic.
    while (fileInfo.hasNext()) {

      String currLine = fileInfo.nextLine();
      String[] currLineSplit = currLine.split("\\,");
      if (currLineSplit[1].charAt(0) == 'M' || currLineSplit[1].charAt(0) == 'm') {

        maleTotal += Integer.parseInt(currLineSplit[2]);
        maleCount += 1;
        node currNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), maleCount);
        BinarySearchTree.treeInsert(maleTree, currNode);
      } else {

        femaleTotal += Integer.parseInt(currLineSplit[2]);
        femaleCount += 1;
        node currNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), femaleCount);
        BinarySearchTree.treeInsert(femaleTree, currNode);
      }

      node mainNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0));
      BinarySearchTree.treeInsert(mainTree, mainNode);

    }

    System.out.println("\nWhich function did you want to use? 1: SearchName, 2: MostPopularName, 3: UniqueName, 4: DisplayName");
    Scanner options = new Scanner(System.in);
    int funcChoice = options.nextInt();

    //Option one is SearchName, which is described in BinarySearchTree.java
    if (funcChoice == 1) {

      System.out.println("You chose SearchName");
      System.out.println("Please enter a name to search for: ");
      Scanner nameScan = new Scanner(System.in);
      String name = nameScan.next();
      try {

    	  node maleNode = BinarySearchTree.searchTreeName(maleTree.root, name);
        node femaleNode = BinarySearchTree.searchTreeName(femaleTree.root, name);

        int nullCheck = maleNode.occur; //The dumbest way to catch a NullPointer before anything happens.

        //These next few lines just ensure formatting doesn't print weird.
        System.out.println("Year      " + "Male      " + "Rank-Male " + "Female    " + "Rank-Female");
        System.out.print("2014      ");
        System.out.print(maleNode.occur);
        for (int i = 0; i < (10 - String.valueOf(maleNode.occur).length()); i++) {
          System.out.print(" ");
        }
        System.out.print(maleNode.rank);
        for (int i = 0; i < (10 - String.valueOf(maleNode.rank).length()); i++) {
          System.out.print(" ");
        }
        System.out.print(femaleNode.occur);
        for (int i = 0; i < (10 - String.valueOf(femaleNode.occur).length()); i++) {
          System.out.print(" ");
        }
        System.out.print(femaleNode.rank);

      } catch (NullPointerException e) {

        System.out.println("Name not found!");
      }

      //Option two is MostPopularName, described in BinarySearchTree.java
    } else if (funcChoice == 2) {

      System.out.println("You chose MostPopularName");// It's best not to inorder walk after inserting, due to a stack overflow. It'll be okay with smaller text files.

      //Our function returns the top occurences in an array of size 5. So we init two seperate arrays since we need female and male info.
      node[] femArr = BinarySearchTree.MostPopularName(femaleTree.root);
      node[] malArr = BinarySearchTree.MostPopularName(maleTree.root);
      System.out.println("Female name:  " + "Frequency:    " + "Percentage:   " + "Male name:    " + "Frequency:    " + "Percentage:   ");

      //Again all for statements are just used to ensure formatting is pretty, it's less confusing than it looks.
      for (int i = 0; i < femArr.length; i++) {
        //Female names from here down
        System.out.print("\n" + femArr[i].name);
        for (int o = 0; o < 14 - femArr[i].name.length(); o++){
          System.out.print(" ");
        }
        System.out.print(femArr[i].occur);
        for (int o = 0; o < 14 - String.valueOf(femArr[i].occur).length(); o++){
          System.out.print(" ");
        }
        System.out.printf("%.7f", (float)femArr[i].occur / (float)femaleTotal * 100);
        System.out.print("%");
        for (int o = 0; o < 4; o++){
          System.out.print(" ");
        }

        //Male names from here down
        System.out.print(malArr[i].name);
        for (int o = 0; o < 14 - malArr[i].name.length(); o++){
          System.out.print(" ");
        }
        System.out.print(malArr[i].occur);
        for (int o = 0; o < 14 - String.valueOf(malArr[i].occur).length(); o++){
          System.out.print(" ");
        }
        System.out.printf("%.7f", (float)malArr[i].occur / (float)maleTotal * 100);
        System.out.print("%");
      }

      //UniqueName is option three, which is described in BinarySearchTree.java
    } else if (funcChoice == 3) {
// It's best not to inorder walk after inserting, due to a stack overflow. It'll be okay with smaller text files.
      System.out.println("You chose UniqueName");
      System.out.println("Unique male names: ");
      BinarySearchTree.UniqueName(maleTree.root, maleTotal + femaleTotal);
      System.out.println("Unique female names: ");
      BinarySearchTree.UniqueName(femaleTree.root, maleTotal + femaleTotal);

    } else if (funcChoice == 4) {

      System.out.println("You chose DisplayName");
      BinarySearchTree.DisplayName(mainTree.root, maleTotal + femaleTotal);
  } else {

    System.out.println("Please choose a valid option.");
  }
}

//--------------------------------------------------------------------------------------------------------------------------------------
  // Code for HashMap here.
  public static void handleHashMap(Scanner fileInfo) {

    System.out.println("You chose: HashMap");

    HashMap<String, node> userFemaleMap = new HashMap<>();
    HashMap<String, node> userMaleMap = new HashMap<>();
    int femaleTotal = 0;
    int maleTotal = 0;
    int maleCount = 0;
    int femaleCount = 0;// It's best not to inorder walk after inserting, due to a stack overflow. It'll be okay with smaller text files.
    int rank = 0;
    int totalBabies = 0;

    // Loops through file and for each name sorts info
    while (fileInfo.hasNext()) {
      String currLine = fileInfo.nextLine();
      String[] currLineSplit = currLine.split("\\,");
      // If male
      if (currLineSplit[1].charAt(0) == 'M' || currLineSplit[1].charAt(0) == 'm') {
        maleCount += 1;
        rank = maleCount;
        // Create node
        maleTotal = maleTotal + Integer.parseInt(currLineSplit[2]);
        totalBabies = totalBabies + Integer.parseInt(currLineSplit[2]);
        node currNode = new node(Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), rank);
        // Add node to male map as value and name as key
        userMaleMap.put(currLineSplit[0], currNode);
      // else female
      } else {
        femaleCount += 1;
        rank = femaleCount;
        // Create node
        femaleTotal = femaleTotal + Integer.parseInt(currLineSplit[2]);
        totalBabies = totalBabies + Integer.parseInt(currLineSplit[2]);
        node currNode = new node(Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), rank);
        // Add node to female map as value and name as key
        userFemaleMap.put(currLineSplit[0], currNode);
      }
    }

    System.out.println("\nWhich function did you want to use? 1: SearchName, 2: MostPopularName, 3: UniqueName, 4: DisplayName");
    Scanner options = new Scanner(System.in);
    int funcChoice = options.nextInt();
    if (funcChoice == 1) {

      System.out.println("You chose SearchName");
      System.out.println("Please enter a name to search for: ");
      Scanner nameScan = new Scanner(System.in);
      String name = nameScan.next();
      HashMapFile.SearchName(name, userMaleMap, userFemaleMap);

    } else if (funcChoice == 2) {

      System.out.println("You chose MostPopularName");
      HashMapFile.MostPopularName(userMaleMap, userFemaleMap, maleTotal, femaleTotal);

    } else if (funcChoice == 3) {

      System.out.println("You chose UniqueName");
      HashMapFile.UniqueName(userMaleMap, userFemaleMap, totalBabies);

    } else if (funcChoice == 4) {

      System.out.println("You chose DisplayName");
      HashMapFile.DisplayName(userMaleMap, userFemaleMap, totalBabies);

    } else {

      System.out.println("Please choose a valid option.");
    }
  }


//--------------------------------------------------------------------------------------------------------------------------------------
  //Code for the ArrayList
  public static void handleArrayList(Scanner fileInfo) {

    System.out.println("You chose: ArrayList");
    // Array with baby names
    ArrayList<node> userArray = new ArrayList<>();
    int femaleTotal = 0;
    int maleTotal = 0;
    int maleCount = 0;
    int femaleCount = 0;
    int rank = 0;
    int totalBabies = 0;

    while (fileInfo.hasNext()) {

      String currLine = fileInfo.nextLine();
      String[] currLineSplit = currLine.split("\\,");
      if (currLineSplit[1].charAt(0) == 'M' || currLineSplit[1].charAt(0) == 'm') {
        maleCount += 1;
        rank = maleCount;
        maleTotal = maleTotal + Integer.parseInt(currLineSplit[2]);
        totalBabies = totalBabies + Integer.parseInt(currLineSplit[2]);
      } else {
        femaleCount += 1;
        rank = femaleCount;
        femaleTotal = femaleTotal + Integer.parseInt(currLineSplit[2]);
        totalBabies = totalBabies + Integer.parseInt(currLineSplit[2]);
      }
      // Create nodes for userArray
      node currNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), rank);
      userArray.add(currNode);
    }

    System.out.println("\nWhich function did you want to use? 1: SearchName, 2: MostPopularName, 3: UniqueName, 4: DisplayName");
    Scanner options = new Scanner(System.in);
    int funcChoice = options.nextInt();
    if (funcChoice == 1) {

      System.out.println("You chose SearchName");
      System.out.println("Please enter a name to search for: ");
      Scanner nameScan = new Scanner(System.in);
      String name = nameScan.next();
      ArrayListFile.SearchName(name, userArray);

    } else if (funcChoice == 2) {

      System.out.println("You chose MostPopularName");
      ArrayListFile.MostPopularName(userArray, maleTotal, femaleTotal);

    } else if (funcChoice == 3) {

      System.out.println("You chose UniqueName");
      ArrayListFile.UniqueName(userArray, totalBabies);

    } else if (funcChoice == 4) {

      System.out.println("You chose DisplayName");
      ArrayListFile.DisplayName(userArray, totalBabies);

    } else {

      System.out.println("Please choose a valid option.");

    }
  }

}
