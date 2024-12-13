import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Dataday3");

        String data = "";
        for (String line : fileData) {
            data += line;
        }

        ArrayList<String> instructions = new ArrayList<>();

        String regex = "mul\\([1-9][0-9]{0,2},[1-9][0-9]{0,2}\\)";

        Matcher mulFound = Pattern.compile(regex).matcher(data);

        while(mulFound.find()) {
            instructions.add(mulFound.group());
        }

        int result = 0;

        for (String command : instructions) {
            result += solveMul(command);
        }

        System.out.println("Answer is " + result);
    }

    public static int solveMul(String command) {
        boolean halfReached = false;
        String firstHalf = "";
        String secondHalf = "";
        for(int i = 4; i < command.length() - 1; i++) {
            if (command.charAt(i) == ',') {
                halfReached = true;
            } else if (!halfReached) {
                firstHalf += command.charAt(i);
            } else {
                secondHalf += command.charAt(i);
            }
        }
        return Integer.parseInt(firstHalf) * Integer.parseInt(secondHalf);
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