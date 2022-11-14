package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//was working on making the test more dynamic/reusable by having a Navigation Logic in a reusable Utility class/method, but ran out of time

public class HooverNavigationUtil {

    public static void main(String[] args) {
// Build the dirty patches collection as list of lists because you can remove the inner lists easily once the dirty patch is encountered
        List<List<Integer>> patches = new ArrayList<>();
        ArrayList<Integer> each = new ArrayList<>();
        each.add(1);
        each.add(0);
        patches.add(each);
        each = new ArrayList<>();
        each.add(2);
        each.add(2);
        patches.add(each);
        each = new ArrayList<>();
        each.add(2);
        each.add(3);
        patches.add(each);


        System.out.println(getResult(5, 5, 1, 2, patches, "NNESEESWNWW"));
    }


    public static List<Integer> getResult(int xMax, int yMax, int startX, int startY, List<List<Integer>> patches, String directions) {

        String[] dirs = directions.split("");

        int patchesCleaned = 0;

        for (String dir : dirs) {


            switch (dir) {

                case "N":
                    // if north increment y axis
                    startY++;
                    // check if matches the x,y of any of the patches, if it is, update the count and remove the pair from the list
                    patchesCleaned = checkAndRemoveDirtyPatchAndReturnUpdatedDirtyPatchCount(startX, startY, patches, patchesCleaned);
                    break;
                case "S":
                    // if south decrement y axis
                    startY--;
                    patchesCleaned = checkAndRemoveDirtyPatchAndReturnUpdatedDirtyPatchCount(startX, startY, patches, patchesCleaned);
                    break;
                case "E":
                    // if east increment x axis
                    startX++;
                    patchesCleaned = checkAndRemoveDirtyPatchAndReturnUpdatedDirtyPatchCount(startX, startY, patches, patchesCleaned);
                    break;
                case "W":
                    // if west decrement x axis
                    startX--;
                    patchesCleaned = checkAndRemoveDirtyPatchAndReturnUpdatedDirtyPatchCount(startX, startY, patches, patchesCleaned);
                    break;
            }

        }
        List<Integer> returnList = new ArrayList<>();
        returnList.add(startX);
        returnList.add(startY);
        returnList.add(patchesCleaned);

//        return "The final coordinates: " + startX + "," + startY + ". Patches cleaned: " + patchesCleaned;
        return returnList;

    }


    public static int checkAndRemoveDirtyPatchAndReturnUpdatedDirtyPatchCount(int startX, int startY, List<List<Integer>> patches, int patchesCleaned) {
        for (int i = 0; i < patches.size(); i++) {
            if (patches.get(i).get(0) == startX && patches.get(i).get(1) == startY) {
                patchesCleaned++;
                patches.remove(i);
            }
        }

        return patchesCleaned;
    }


}

