/*  Function descriptions:
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

import java.util.Iterator;
import java.util.ArrayList;

public class ArrayListFile {

  public static void SearchName(String name, ArrayList<node> userArray) {
    // Make iterator
    int i = 0;
    String maleInfo = "  NA  NA";
    String femaleInfo = "  NA  NA";
    Iterator<node> arrayIterator = userArray.iterator();
		while (arrayIterator.hasNext()) {
      // Grab the node
      node currNode = arrayIterator.next();
      // Find the name
      if (currNode.name.equals(name)) {
        if (i == 0) {
          System.out.println("Year Male Male-Rank Female Female-Rank");
          System.out.print("2014  ");
          i++;
        }
        // Find info for male and female, if DNE then NA
        if (Character.toLowerCase(currNode.gender) == 'm') {
          maleInfo = currNode.occur + "    " + currNode.rank;
        } else {
          femaleInfo = currNode.occur + "      " + currNode.rank;
        }
      }
		}
    if (maleInfo.equals("  NA  NA") && femaleInfo.equals("  NA  NA")){
      System.out.println("Name not found.");
    } else {
      System.out.println(maleInfo + "    " + femaleInfo);
    }
  }

  public static void MostPopularName() {

  }

  public static void UniqueName() {

  }

  public static void DisplayName() {

  }

}
