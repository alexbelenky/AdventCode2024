import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

        for(int row = 0; row < dataSet.length; row++) {
            for (int col = 0; col < dataSet[0].length; col++) {
                if (dataSet[row][col].equals("X")) {
                    if (col < dataSet[0].length - 3 && dataSet[row][col + 1].equals("M") && dataSet[row][col + 2].equals("A") && dataSet[row][col + 3].equals("S")) {
                        result++;
                    } else if (row < dataSet.length - 3  && dataSet[row + 1][col].equals("M") && dataSet[row + 2][col].equals("A") && dataSet[row + 3][col].equals("S")) {
                        result++;
                    }
                } else if (dataSet[row][col].equals("S")) {
                    if (col < dataSet[0].length - 3 && dataSet[row][col + 1].equals("A") && dataSet[row][col + 2].equals("M") && dataSet[row][col + 3].equals("X")) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
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