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
    int maleCount = 0;
    int femaleCount = 0;

    while (fileInfo.hasNext()) {

      String currLine = fileInfo.nextLine();
      String[] currLineSplit = currLine.split("\\,");
      if (currLineSplit[1].charAt(0) == 'M' || currLineSplit[1].charAt(0) == 'm') {

        maleCount += 1;
        node currNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), maleCount);
        BinarySearchTree.treeInsert(maleTree, currNode);
      } else {

        femaleCount += 1;
        node currNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), femaleCount);
        BinarySearchTree.treeInsert(femaleTree, currNode);
      }

      node mainNode = new node(currLineSplit[0], Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0));
      BinarySearchTree.treeInsert(mainTree, mainNode);

    } // It's best not to inorder walk after inserting, due to a stack overflow. It'll be okay with smaller text files.
    System.out.println("\nWhich function did you want to use? 1: SearchName, 2: MostPopularName, 3: UniqueName, 4: DisplayName");
    Scanner options = new Scanner(System.in);
    int funcChoice = options.nextInt();
    if (funcChoice == 1) {

      System.out.println("You chose SearchName");
      System.out.println("Please enter a name to search for: ");
      Scanner nameScan = new Scanner(System.in);
      String name = nameScan.next();
  	  node maleNode = BinarySearchTree.searchTreeName(maleTree.root, name);
      node femaleNode = BinarySearchTree.searchTreeName(femaleTree.root, name);

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

    } else if (funcChoice == 2) {

      System.out.println("You chose MostPopularName");
    } else if (funcChoice == 3) {

      System.out.println("You chose UniqueName");
    } else if (funcChoice == 4) {

      System.out.println("You chose DisplayName");
      BinarySearchTree.DisplayName(mainTree.root, maleCount + femaleCount);
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
        node currNode = new node(Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), rank);
        userMaleMap.put(currLineSplit[0], currNode);
      } else {
        femaleCount += 1;
        rank = femaleCount;
        femaleTotal = femaleTotal + Integer.parseInt(currLineSplit[2]);
        totalBabies = totalBabies + Integer.parseInt(currLineSplit[2]);
        node currNode = new node(Integer.parseInt(currLineSplit[2]), currLineSplit[1].charAt(0), rank);
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
