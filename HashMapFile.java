
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

public class HashMapFile {
  /*
      SearchName for a name returns number of male and female babies born in that year who has that name.
      It should also return a rank for this name (how popular is this name for boys and girls).

  */

  public static void SearchName(String name, HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap) {
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
      System.out.println("Year Male Male-Rank Female Female-Rank");
      System.out.print("2014  ");
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
    String pre = "%";

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
          displayPopNames[l][5] = String.format("%.2f%s", ((float)maleNode.occur / (float)maleTotal * 100), pre);
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
          displayPopNames[j][2] = String.format("%.2f%s", ((float)femaleNode.occur / (float)femaleTotal * 100), pre);
          j++;
        }
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

  public static void UniqueName(HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap, int totalBabies) {

    String[][] displayUniqueName = new String[6][6];
    int frequency = 0;
    int precentage = 0;
    String pre = "%";
    int i = 1;
    int j = 1;
    int flow = Integer.MAX_VALUE;
    int mlow = Integer.MAX_VALUE;

    for (String malekey : userMaleMap.keySet()) {
      node maleNode = userMaleMap.get(malekey);
      if (maleNode.occur < mlow) {
        mlow = maleNode.occur;
      }
    }

    for (String femalekey : userFemaleMap.keySet()) {
      node femaleNode = userFemaleMap.get(femalekey);
      if (femaleNode.occur < flow) {
        flow = femaleNode.occur;
      }
    }

    // Initial Titles
    displayUniqueName[0][0] = "Female Name";
    displayUniqueName[0][1] = "Frequency ";
    displayUniqueName[0][2] = "%  ";
    displayUniqueName[0][3] = "Male Name";
    displayUniqueName[0][4] = "Frequency  ";
    displayUniqueName[0][5] = "%  ";

    while (i <= 5) {
      for (String mkey : userMaleMap.keySet()) {
        // Create node for male
        node maleNode = userMaleMap.get(mkey);
        frequency = maleNode.occur;
        if ((frequency == mlow) && (i <= 5)){
          displayUniqueName[i][0] = mkey;
          displayUniqueName[i][1] = Integer.toString(frequency);
          displayUniqueName[i][2] = String.format("%.7f%s", ((float)frequency / (float)totalBabies * 100), pre);
          i++;
        }
      }
      mlow++;
    }

    while (j <= 5) {
      for (String fkey : userFemaleMap.keySet()) {
        // Create node for female
        node femaleNode = userFemaleMap.get(fkey);
        // Find the frequency of the male and female together
        frequency = femaleNode.occur;
        if ((frequency == flow) && (j <= 5)){
          displayUniqueName[j][3] = fkey;
          displayUniqueName[j][4] = Integer.toString(frequency);
          displayUniqueName[j][5] = String.format("%.7f%s", ((float)frequency / (float)totalBabies * 100), pre);
          j++;
        }
      }
      flow++;
    }

    for(int r=0; r<displayUniqueName.length; r++) {
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

  public static void DisplayName(HashMap<String, node> userMaleMap, HashMap<String, node> userFemaleMap, int totalBabies) {
    // Make arraylist with names
    ArrayList<String> babyNames = new ArrayList<>();
    float precentage = 0;
    String pre = "%";

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
        System.out.printf("Percentage: %.2f%s \n", p, pre);
    }
  }


}
