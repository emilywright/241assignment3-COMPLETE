/*

Names: Christian Brintnall, Emily Wright
Description: This is the driver file for the three data structures/functions.
             A user will input their chosen data structure and this program will user
             that data structure to display data. All the handle functions are contained
             in handle.java.

Issues: The scanner needs to be reset, otherwise you can only pick one data structure and it won't be able to load data with the others.
        This can be fixed multiple ways, by loading the data structures before we do anything (this would take A LONG time) or by
        creating a scanner when the option is chosen, I'm going to talk to Moushomi about it Monday.

        Just kidding I fixed it by putting it in the loop too.

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
