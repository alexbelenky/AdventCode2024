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
            System.out.println(Arrays.toString(split));
            int first = Integer.parseInt(split[0]);
            int next = Integer.parseInt(split[1]);
            boolean increasing = true;

            if (first > next) {
                increasing = false;
            }

            for (int i = 0; i < split.length - 1; i++) {
                first = Integer.parseInt(split[i]);
                next = Integer.parseInt(split[i + 1]);
                if (increasing) {
                    if (first > next || (next - first) > 3) {
                        invalid++;
                        break;
                    }
                } else {
                    if (first < next || (first - next) > 3) {
                        invalid++;
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