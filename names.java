/*

Names: Christian Brintnall, Emily Wright
Description: This is the driver file for the three data structures/functions.
             A user will input their chosen data structure and this program will user
             that data structure to display data.

*/
import java.util.Scanner;
import java.io.*;

public class names {

  public static void main (String[] args) {

    try {

      File yearFile = new File("yob2014.txt");
      Scanner yearScan = new Scanner(yearFile);
    } catch (FileNotFoundException e) {

      System.out.println("Please ensure yob2014.txt is in the current directory and try again.");
      System.exit(0);
    }

    while (true) {

      System.out.println("\nWhich data structure would you like to use? 1: Tree, 2: HashMap, 3: ArrayList");
      Scanner userScan = new Scanner(System.in);
      int userChoice = userScan.nextInt();
      if (userChoice == 1) {

        System.out.println("You chose: Tree");
        System.out.println("Loading the data structure, due to large file size this may take a second. There will be a message indicating when complete");
      } else if (userChoice == 2) {

        System.out.println("You chose: HashMap");
        System.out.println("Loading the data structure, due to large file size this may take a second. There will be a message indicating when complete"); // Emily please make sure to add this after it's done loading.
        System.out.println("Emily this is where you load the HashMap with necessary info");
      } else if (userChoice == 3) {

        System.out.println("You chose: ArrayList");
        System.out.println("Loading the data structure, due to large file size this may take a second. There will be a message indicating when complete"); // Emily please make sure to add this after it's done loading.
        System.out.println("Emily this is where you load the ArrayList with necessary info");
      } else {

        System.out.println("Not a valid choice, please enter a number between 1-3.");
      }
    }
  }
}
