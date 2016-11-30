
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

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
    while (l < 11) {
      for (String key : userMaleMap.keySet()) {
        node maleNode = userMaleMap.get(key);
        if ((l <= 10) && (maleNode.rank == l)) {
          displayPopNames[l][3] = key;
          displayPopNames[l][4] = Integer.toString(maleNode.occur);
          displayPopNames[l][5] = String.format("%.2f", ((float)maleNode.occur / (float)maleTotal * 100));
          l++;
        }
      }
    }

    while (j < 11) {
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

    String[][] displayUniqueName = new String[6][3];
    int frequency = 0;
    int precentage = 0;
    int i = 1;
    // Initial Titles
    displayUniqueName[0][0] = "Name";
    displayUniqueName[0][1] = "Frequency";
    displayUniqueName[0][2] = "%  ";

    for (String mkey : userMaleMap.keySet()) {
      // Create node for male
      node maleNode = userMaleMap.get(mkey);
      for (String fkey : userFemaleMap.keySet()) {
        // Create node for female
        node femaleNode = userFemaleMap.get(fkey);
        // Find the frequency of the male and female together
        frequency = femaleNode.occur + maleNode.occur;
        if ((fkey.equals(mkey)) && (frequency >= 5) && (i <= 5)){
          displayUniqueName[i][0] = fkey;
          displayUniqueName[i][1] = Integer.toString(frequency);
          displayUniqueName[i][2] = String.format("%.2f", ((float)frequency / (float)totalBabies * 100));
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

  public static void DisplayName(HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap, int totalBabies) {
    // Make arraylist with names
    ArrayList<String> babyNames = new ArrayList<>();
    float precentage = 0;

    ArrayList<String> babyFemaleNames = new ArrayList<String>(userFemaleMap.keySet());
    ArrayList<String> babyMaleNames = new ArrayList<String>(userMaleMap.keySet());
    babyNames.addAll(babyMaleNames);
    babyNames.addAll(babyFemaleNames);

    String[] sortedNames = babyNames.toArray(new String[babyNames.size()]);
    Arrays.sort(sortedNames);

    for (String name : sortedNames) {
        int count = 0;
        System.out.print("Name: " + name  + ", ");
        // Iterate through all of the nodes until finds count
        for (String mkey : userMaleMap.keySet()) {
          // Create node for male
          node maleNode = userMaleMap.get(mkey);
          if(name.equals(mkey)){
            count = count + maleNode.occur;
          }
        }
        for (String fkey : userFemaleMap.keySet()) {
          // Create node for female
          node femaleNode = userFemaleMap.get(fkey);
          if(name.equals(fkey)) {
            count = count + femaleNode.occur;
          }
        }
        precentage = (float)count / (float)totalBabies;
        Double p = precentage * 100.00;
        System.out.print("Count: " + count + ", ");
        System.out.printf("Percentage: %.2f \n", p);
    }
  }


}
