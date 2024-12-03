import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/DataDay1");
        int simalarity = 0;

        int[] leftSide = new int[fileData.size()];
        int[] rightSide = new int[fileData.size()];

        for (int i = 0; i < fileData.size(); i++) {
            String[] split = fileData.get(i).split("   ");
            leftSide[i] = Integer.parseInt(split[0]);
            rightSide[i] = Integer.parseInt(split[1]);
        }

        Arrays.sort(leftSide);
        Arrays.sort(rightSide);

        for (int leftCheck : leftSide) {
            int count = 0;
            for (int rightCheck : rightSide) {
                if (leftCheck == rightCheck) {
                    count++;
                }
            }
            simalarity+= leftCheck * count;
        }

        System.out.println("Answer is: " + simalarity);
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