/*

Names: Christian Brintnall, Emily Wright
Description: This is the driver file for the three data structures/functions.
             A user will input their chosen data structure and this program will user
             that data structure to display data. All the handle functions are contained
             in handle.java.

*/

import java.util.Scanner;
import java.io.*;

public class names {


  //Our main function, where we see what data structure is used.
  public static void main (String[] args) {

    try {


      while (true) {

        System.out.print("\nPlease input a text file: ");
        Scanner uInput = new Scanner(System.in);
        String userInput = uInput.next();
        File yearFile = new File(userInput);
        Scanner yearScan = new Scanner(yearFile);

        System.out.println("\nWhich data structure would you like to use? 1: Tree, 2: HashMap, 3: ArrayList");
        Scanner userScan = new Scanner(System.in);
        int userChoice = userScan.nextInt();

        if (userChoice == 1) {

          //This functions does everything the tree should.
          handle.handleTree(yearScan);

        } else if (userChoice == 2) {

          //This function does everything the HashMap should
          handle.handleHashMap(yearScan);

        } else if (userChoice == 3) {

          //This function does everything the ArrayList should
          handle.handleArrayList(yearScan);

        } else {

          System.out.println("Not a valid choice, please enter a number between 1-3.");
        }
      }

    } catch (FileNotFoundException e) {

      System.out.println("Please input a valid YOB text file, with the format [name], [gender], [frequency].");
      System.exit(0);

    }
  }
}
