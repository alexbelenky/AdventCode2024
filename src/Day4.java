import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Dataday4");

        String[][] dataSet = new String[fileData.size()][fileData.getFirst().length()];

        for(int row = 0; row < dataSet.length; row++) {
            for (int col = 0; col < dataSet[0].length; col++) {
                dataSet[row][col] = fileData.get(row).substring(col, col + 1);
            }
        }

        for(String[] col : dataSet) {
            for (String elm : col) {
                System.out.print(elm);
            }
            System.out.println();
        }

        int result = 0;
        result += checkXMAS(dataSet);
        System.out.println(result);
        checkVertical(dataSet);
    }

    public static int checkXMAS (String[][] data) {
        int result = 0;
        result += checkHorizontal(data);
        return result;
    }

    public static int checkHorizontal (String[][] data) {
        int found = 0;
        for(String[] row : data) {
            String line = "";
            for (String col : row) {
                line += col;
            }

            while(line.contains("XMAS")) {
                line = line.replaceFirst("XMAS", "");
                found++;
            }

            while(line.contains("SAMX")) {
                line = line.replaceFirst("SAMX", "");
                found++;
            }
        }
        return found;
    }

    public static int checkVertical (String[][] data) {
        int found = 0;
        String line = "";
        for(int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                line += data[col][row];
                System.out.print(data[col][row]);

            }
            System.out.println();
        }

        System.out.println("veritcal :");
            while(line.contains("XMAS")) {
                line = line.replaceFirst("XMAS", "");
                found++;
            }

            while(line.contains("SAMX")) {
                line = line.replaceFirst("SAMX", "");
                found++;
            }
        return found;
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
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }
}