import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/DataDay2");

        int invalid = 0;
        for(String report : fileData) {
            String[] split = report.split(" ");
            ArrayList<Integer> original = new ArrayList<>();

            for (String number : split) {
                original.add(Integer.parseInt(number));
            }

            int first = original.getFirst();
            int next = original.get(1);
            boolean increasing = true;
            boolean failChange = false;

            if (first > next) {
                increasing = false;
            }

            System.out.println("Orignal: " + original);
            for(int i = 0; i < original.size() - 1; i++) {
                first = original.get(i);
                next = original.get(i + 1);

                if (increasing) {
                    if (first > next || (next - first) > 3 || first == next) {
                        ArrayList<Integer> option1;
                        option1 = new ArrayList<>(original.subList(0, i));
                        //add the rest of the list to option 1. Do the same for option 2.
                        //then check which one works. Whichever does stays
                        //If none, you could end early with break and add invalid
                        System.out.println("option 1: " + option1);
                        original.remove(i);
                        i--;
                    }
                } else {
                    if (first < next || (first - next) > 3 || first == next) {
                        original.remove(i);
                        i--;
                    }
                }
            }

            System.out.println("After cleanup: " + original);
            for(int i = 0; i < original.size() - 1; i++) {
                first = original.get(i);
                next = original.get(i + 1);

                if (increasing) {
                    if (first > next || (next - first) > 3 || first == next) {
                        invalid++;
                        System.out.println("invalid: " + original);
                        break;
                    }
                } else {
                    if (first < next || (first - next) > 3 || first == next) {
                        invalid++;
                        System.out.println("invalid: " + original);
                        break;
                    }
                }
            }
        }

        System.out.println("Safe = " + (fileData.size() - invalid));
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}