
import java.util.HashMap;
import java.util.Arrays;

public class HashMapFile {
  /*
      SearchName for a name returns number of male and female babies born in that year who has that name.
      It should also return a rank for this name (how popular is this name for boys and girls).

  */

  public static void SearchName(String name, HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap) {
    System.out.println("Year Male Male-Rank Female Female-Rank");
    System.out.print("2014  ");
    // If the male and/or female version of the name DNE
    String maleInfo = "NA    NA   ";
    String femaleInfo = "NA    NA";
    if (userFemaleMap.containsKey(name)) {
      node femaleName = userFemaleMap.get(name);
      femaleInfo = femaleName.occur + "    " + femaleName.rank;
    }

    if (userMaleMap.containsKey(name)) {
      node maleName = userMaleMap.get(name);
      maleInfo = maleName.occur + "    " + maleName.rank;
    }

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

  public static void MostPopularName(HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap, int maleTotal, int femaleTotal) {

    String[][] displayPopNames = new String[11][6];
    int j = 1;
    int l = 1;

    // Initial Titles
    displayPopNames[0][0] = "Female Name";
    displayPopNames[0][1] = "Frequency ";
    displayPopNames[0][2] = "%  ";
    displayPopNames[0][3] = "Male Name";
    displayPopNames[0][4] = "Frequency  ";
    displayPopNames[0][5] = "%  ";

    // input male names
    for (String key : userMaleMap.keySet()) {
      node maleNode = userMaleMap.get(key);
      if ((l <= 10) && (maleNode.rank == l)) {
        displayPopNames[l][3] = key;
        displayPopNames[l][4] = Integer.toString(maleNode.occur);
        displayPopNames[l][5] = String.format("%.2f", ((float)maleNode.occur / (float)maleTotal * 100));
        l++;
      }
    }

    // input female names
    for (String key : userFemaleMap.keySet()) {
      node femaleNode = userFemaleMap.get(key);
      if ((j <= 10) && (femaleNode.rank == j)) {
        displayPopNames[j][0] = key;
        displayPopNames[j][1] = Integer.toString(femaleNode.occur);
        displayPopNames[j][2] = String.format("%.2f", ((float)femaleNode.occur / (float)femaleTotal * 100));
        j++;
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

  public static void UniqueName(HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap, int totalBabies) {

  }

  /*
      DisplayName prints the names in alphabetical order,
      and next to each name prints the number of male and female babies that have this
      name and percentage of babies (male and female) for that name.

   */

  public static void DisplayName(HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap, int totalBabies) {

  }


}
