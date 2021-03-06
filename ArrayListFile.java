
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListFile {

  /*
      SearchName for a name returns number of male and female babies born in that year who has that name.
      It should also return a rank for this name (how popular is this name for boys and girls).

  */

  public static void SearchName(String name, ArrayList<node> userArray) {

    // If the male and/or female version of the name DNE
    String maleInfo = "NA    NA   ";
    String femaleInfo = "NA    NA";
    // Make iterator
    Iterator<node> arrayIterator = userArray.iterator();
		while (arrayIterator.hasNext()) {
      // Grab the node
      node currNode = arrayIterator.next();
      // Find the name
      if (currNode.name.equals(name)) {
        // Find info for male and female, if DNE then NA
        if (Character.toLowerCase(currNode.gender) == 'm') {
          maleInfo = currNode.occur + "    " + currNode.rank;
        } else {
          femaleInfo = currNode.occur + "      " + currNode.rank;
        }
      }
		}
    // If name DNE
    if (maleInfo.equals("NA    NA   ") && femaleInfo.equals("NA    NA")){
      System.out.println();
      System.out.println("Name not found.");
    } else {
      // Print info
      System.out.println("Year Male Male-Rank Female Female-Rank");
      System.out.print("2014  ");
      System.out.println(maleInfo + "    " + femaleInfo);
    }
  }

  /*
      MostPopularName returns the most popular 10 male and female baby names for a
      given year with their numbers and percentage of babies with that name.

   */

  public static void MostPopularName(ArrayList<node> userArray, int maleTotal, int femaleTotal) {

      String[][] displayPopNames = new String[11][6];
      Iterator<node> arrayIterator = userArray.iterator();
      int j = 1;
      int l = 1;
      String pre = "%";

      // Initial Titles
      displayPopNames[0][0] = "Female Name";
      displayPopNames[0][1] = "Frequency ";
      displayPopNames[0][2] = "%  ";
      displayPopNames[0][3] = "Male Name";
      displayPopNames[0][4] = "Frequency  ";
      displayPopNames[0][5] = "%  ";


  		while (arrayIterator.hasNext()) {
        // Grab the node
        node currNode = arrayIterator.next();
        // input female names
        if (j <= 10) {
          displayPopNames[j][0] = currNode.name;
          displayPopNames[j][1] = Integer.toString(currNode.occur);
          displayPopNames[j][2] = String.format("%.2f%s", ((float)currNode.occur / (float)femaleTotal * 100), pre);
          j++;
        }

        // input male names
        if ((Character.toLowerCase(currNode.gender) == 'm')  && (l <= 10)){
          displayPopNames[l][3] = currNode.name;
          displayPopNames[l][4] = Integer.toString(currNode.occur);
          displayPopNames[l][5] = String.format("%.2f%s", ((float)currNode.occur / (float)maleTotal * 100), pre);
          l++;
        }


      }

      // Print the double array with Popular name info
      for(int r=0; r<displayPopNames.length; r++) {
         for(int c=0; c < displayPopNames[r].length; c++){
           if((r == 0) && (c <= 5)){
             System.out.print(displayPopNames[r][c] + "        ");
           } else if ((c == 0) && (r <= 10)) {
             System.out.print(displayPopNames[r][c]);
             for(int o = 0; o < 20 - String.valueOf(displayPopNames[r][c]).length(); o++){
               System.out.print(" ");
             }
           } else if ((c == 1) && (r <= 10)) {
             System.out.print(displayPopNames[r][c]);
             for(int o = 0; o < 15 - String.valueOf(displayPopNames[r][c]).length(); o++){
               System.out.print(" ");
             }
           } else if ((c == 2) && (r <= 10)) {
             System.out.print(displayPopNames[r][c]);
             for(int o = 0; o < 16 - String.valueOf(displayPopNames[r][c]).length(); o++){
               System.out.print(" ");
             }
           } else if ((c == 3) && (r <= 10)) {
             System.out.print(displayPopNames[r][c]);
             for(int o = 0; o < 16 - String.valueOf(displayPopNames[r][c]).length(); o++){
               System.out.print(" ");
             }
           } else {
             System.out.print(displayPopNames[r][c]);
             for(int o = 0; o < 15 - String.valueOf(displayPopNames[r][c]).length(); o++){
               System.out.print(" ");
             }
           }
         }
         System.out.println();
      }
  }

  /*
      UniqueName returns 5 male and female baby names that are unique with their frequency and percentage of babies with that name.
      For this method, ignore names that appear less than 5 times.

   */

  public static void UniqueName(ArrayList<node> userArray, int totalBabies) {
    // Make array for displaying the unique names
    String[][] displayUniqueName = new String[6][6];
    // Arraylists for male and female names
    ArrayList<node> femaleNames = new ArrayList<>();
    ArrayList<node> maleNames = new ArrayList<>();
    // Other variables for printing or adding to displayUniqueName
    String pre = "%";
    int frequency = 0;
    int precentage = 0;
    int checkloop = 1;
    int i = 1;
    int j = 1;
    int flow = Integer.MAX_VALUE;
    int mlow = Integer.MAX_VALUE;



    // Initial Titles
    displayUniqueName[0][0] = "Female Name";
    displayUniqueName[0][1] = "Frequency ";
    displayUniqueName[0][2] = "%  ";
    displayUniqueName[0][3] = "Male Name";
    displayUniqueName[0][4] = "Frequency  ";
    displayUniqueName[0][5] = "%  ";

    Iterator<node> arrayIterator = userArray.iterator();
    // Create two arraylists for male and female containing nodes
    while (arrayIterator.hasNext()) {
      // Grab the node
      node currNode = arrayIterator.next();
      // Add the name to the male list and find the lowest occurance
      if (Character.toLowerCase(currNode.gender) == 'm'){
        if (currNode.occur < mlow) {
          mlow = currNode.occur;
        }
        maleNames.add(currNode);
        // Add the name to the female list and find the lowest occurance
      } else {
        if (currNode.occur < flow) {
          flow = currNode.occur;
        }
        femaleNames.add(currNode);
      }
    }

    // Iterate through the male and female list
    Iterator<node> femaleIterator = femaleNames.iterator();
    Iterator<node> maleIterator = maleNames.iterator();
    // Loop until it finds the five smallest female names
    while (i <= 5) {
      while (femaleIterator.hasNext()) {
        // Create node for female
        node femaleNode = femaleIterator.next();
        // Find the frequency of the male and female together
        frequency = femaleNode.occur;
        // Make sure the two names are the same, the frequency is greater than 5, and for only 5 names
        if ((frequency == flow) && (i <= 5)){
          displayUniqueName[i][0] = femaleNode.name;
          displayUniqueName[i][1] = Integer.toString(frequency);
          displayUniqueName[i][2] = String.format("%.7f%s", ((float)frequency / (float)totalBabies * 100), pre);
          i++;
        }
      }
      femaleIterator = femaleNames.iterator();
      flow++;
    }

    // Loop until it finds the five smallest male names
    while (j <= 5) {
      while (maleIterator.hasNext()) {
        // Create node for male
        node maleNode = maleIterator.next();
        frequency = maleNode.occur;
        // Find nodes with the lowest frequency but are still greater than 5
        if ((frequency == mlow) && (j <= 5)){
          displayUniqueName[j][3] = maleNode.name;
          displayUniqueName[j][4] = Integer.toString(frequency);
          displayUniqueName[j][5] = String.format("%.7f%s", ((float)frequency / (float)totalBabies * 100), pre);
          j++;
        }
      }
      maleIterator = maleNames.iterator();
      mlow++;
    }

    // Display the names
    for(int r=0; r < displayUniqueName.length; r++) {
       for(int c=0; c < displayUniqueName[r].length; c++){
         if((r == 0) && (c <= 5)){
           System.out.print(displayUniqueName[r][c] + "        ");
         } else if ((c == 0) && (r <= 5)) {
           System.out.print(displayUniqueName[r][c]);
           for(int o = 0; o < 23 - String.valueOf(displayUniqueName[r][c]).length(); o++){
             System.out.print(" ");
           }
         } else if ((c == 1) && (r <= 5)) {
           System.out.print(displayUniqueName[r][c]);
           for(int o = 0; o < 10 - String.valueOf(displayUniqueName[r][c]).length(); o++){
             System.out.print(" ");
           }
         } else if ((c == 2) && (r <= 5)) {
           System.out.print(displayUniqueName[r][c]);
           for(int o = 0; o < 16 - String.valueOf(displayUniqueName[r][c]).length(); o++){
             System.out.print(" ");
           }
         } else if ((c == 3) && (r <= 5)) {
           System.out.print(displayUniqueName[r][c]);
           for(int o = 0; o < 20 - String.valueOf(displayUniqueName[r][c]).length(); o++){
             System.out.print(" ");
           }
         } else {
           System.out.print(displayUniqueName[r][c]);
           for(int o = 0; o < 11 - String.valueOf(displayUniqueName[r][c]).length(); o++){
             System.out.print(" ");
           }
         }
       }
       System.out.println();
    }
  }

  /*
      DisplayName prints the names in alphabetical order,
      and next to each name prints the number of male and female babies that have this
      name and percentage of babies (male and female) for that name.

   */

  public static void DisplayName(ArrayList<node> userArray, int totalBabies) {

    // Make arraylist with names
    ArrayList<String> babyNames = new ArrayList<>();
    Iterator<node> namesIterator = userArray.iterator();
    float precentage = 0;
    String pre = "%";

    // Create arraylist with names
    while (namesIterator.hasNext()) {
      node currNode = namesIterator.next();
      babyNames.add(currNode.name);
    }

    String[] sortedNames = babyNames.toArray(new String[babyNames.size()]);
    Arrays.sort(sortedNames);

    Iterator<node> nIterator = userArray.iterator();
    for (String name : sortedNames) {
        int count = 0;
        System.out.print("Name: " + name  + ", ");
        // Iterate through all of the nodes until finds count
        while (nIterator.hasNext()) {
          node cNode = nIterator.next();
          if(name.equals((String)cNode.name)){
            count = count + cNode.occur;
          }
        }
        // Move iterator back to beginning of list
        nIterator = userArray.iterator();
        // Find precentage
        precentage = (float)count / (float)totalBabies;
        Double p = precentage * 100.00;
        // Print the count and precentage
        System.out.print("Count: " + count + ", ");
        System.out.printf("Percentage: %.2f%s \n", p, pre);
    }
  }

}
