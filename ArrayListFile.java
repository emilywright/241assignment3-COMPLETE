
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListFile {

  /*
      SearchName for a name returns number of male and female babies born in that year who has that name.
      It should also return a rank for this name (how popular is this name for boys and girls).

  */

  public static void SearchName(String name, ArrayList<node> userArray) {

    System.out.println("Year Male Male-Rank Female Female-Rank");
    System.out.print("2014  ");
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
      System.out.println("Name not found.");
    } else {
      // Print info
      System.out.println(maleInfo + "    " + femaleInfo);
    }
  }

  /*
      MostPopularName returns the most popular 10 male and female baby names for a
      given year with their numbers and percentage of babies with that name.

   */

  public static void MostPopularName(ArrayList<node> userArray, int femaleCount, int maleCount) {

      String[][] displayPopNames = new String[11][6];
      Iterator<node> arrayIterator = userArray.iterator();
      int j = 1;
      int l = 1;
      int frequency = 0;

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
          frequency = currNode.occur / femaleCount;
          displayPopNames[j][2] = Double.toString(frequency) + "%";
          j++;
        }

        // input male names
        if ((Character.toLowerCase(currNode.gender) == 'm')  && (l <= 10)){
          displayPopNames[l][3] = currNode.name;
          displayPopNames[l][4] = Integer.toString(currNode.occur);
          frequency = currNode.occur / femaleCount;
          displayPopNames[l][5] = Double.toString(frequency) + "%";
          l++;
        }


      }

      // Print the double array with Popular name info
     for (String[] row : displayPopNames) {
        System.out.println(Arrays.toString(row));
     }
  }

  /*
      UniqueName returns 5 male and female baby names that are unique with their frequency and percentage of babies with that name.
      For this method, ignore names that appear less than 5 times.

   */

  public static void UniqueName(ArrayList<node> userArray, int femaleCount, int maleCount) {
    String[][] displayUniqueName = new String[6][3];
    Iterator<node> arrayIterator = userArray.iterator();
    ArrayList<node> femaleNames = new ArrayList<>();
    ArrayList<node> maleNames = new ArrayList<>();
    int totalBabies = femaleCount + maleCount;
    int frequency = 0;
    int precentage = 0;
    int i = 1;
    // Initial Titles
    displayUniqueName[0][0] = "Name";
    displayUniqueName[0][1] = "Frequency";
    displayUniqueName[0][2] = "%  ";
    // Create two arraylists for male and female containing nodes
    while (arrayIterator.hasNext()) {
      // Grab the node
      node currNode = arrayIterator.next();
      if (Character.toLowerCase(currNode.gender) == 'm'){
        maleNames.add(currNode);
      } else {
        femaleNames.add(currNode);
      }
    }

    // Iterate through the male and female list
    Iterator<node> femaleIterator = femaleNames.iterator();
    Iterator<node> maleIterator = maleNames.iterator();
    while (femaleIterator.hasNext()) {
      while (maleIterator.hasNext()) {
        // Create nodes for male and female
        node femaleNode = femaleIterator.next();
        node maleNode = maleIterator.next();
        // Find the frequency of the male and female together
        frequency = femaleNode.occur + maleNode.occur;
        // Make sure the two names are the same, the frequency is greater than 5, and for only 5 names
        if ((femaleNode.name.equals(maleNode.name)) && (frequency >= 5) && (i <= 5)){
          System.out.println("female " + femaleNode.occur + " male " + maleNode.occur);
          displayUniqueName[i][0] = femaleNode.name;
          displayUniqueName[i][1] = Integer.toString(frequency);
          displayUniqueName[i][2] = String.format("%.2f", ((float)frequency / (float)totalBabies));
          i++;
        }
      }
    }

    for (String[] row : displayUniqueName) {
       System.out.println(Arrays.toString(row));
    }
  }

  /*
      DisplayName prints the names in alphabetical order,
      and next to each name prints the number of male and female babies that have this
      name and percentage of babies (male and female) for that name.

   */

  public static void DisplayName(ArrayList<node> userArray, int femaleCount, int maleCount) {

    // Make arraylist with names
    ArrayList<String> babyNames = new ArrayList<>();
    Iterator<node> namesIterator = userArray.iterator();
    int totalBabies = femaleCount + maleCount;
    float precentage = 0;

    // Create arraylist with names
    while (namesIterator.hasNext()) {
      node currNode = namesIterator.next();
      babyNames.add(currNode.name);
    }

    String[] sortedNames = babyNames.toArray(new String[babyNames.size()]);
    Arrays.sort(sortedNames);
    //System.out.println(Arrays.toString(sortedNames));

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
        nIterator = userArray.iterator();
        precentage = (float)count / (float)totalBabies;
        Double p = precentage * 100.00;
        System.out.print("Count: " + count + ", ");
        System.out.printf("Precentage: %.2f \n", p);
    }
  }

}
