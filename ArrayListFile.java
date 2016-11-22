
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
      Double frequency = 0.0;

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
          displayPopNames[j][2] = Integer.toString(frequency) + "%";
          j++;
        }

        // input male names
        if ((Character.toLowerCase(currNode.gender) == 'm')  && (l <= 10)){
          displayPopNames[l][3] = currNode.name;
          displayPopNames[l][4] = Integer.toString(currNode.occur);
          frequency = currNode.occur / femaleCount;
          displayPopNames[l][5] = Integer.toString(frequency) + "%";
          l++;
        }


      }

      // Print the double array with Popular name info
      //System.out.println(Arrays.deepToString(displayPopNames));
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
    displayPopNames[0][0] = "Name";
    displayPopNames[0][1] = "Frequency";
    displayPopNames[0][2] = "%  ";
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

    Iterator<node> femaleIterator = userArray.iterator();
    Iterator<node> maleIterator = userArray.iterator();
    while (femaleIterator.hasNext()) {
      while (maleIterator.hasNext()) {
        node femaleNode = femaleIterator.next();
        node maleNode = maleIterator.next();
        int babyNameTotal = femaleNode.occur + maleNode.occur;
        if ((femaleIterator.name.equals(maleIterator.name)) && (babyNameTotal > 5)){

        }
      }
    }
  }

  /*
      DisplayName prints the names in alphabetical order,
      and next to each name prints the number of male and female babies that have this
      name and percentage of babies (male and female) for that name.

   */

  public static void DisplayName() {

  }

}
