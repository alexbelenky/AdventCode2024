import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
            int firstCheck;
            int nextCheck;
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
                        ArrayList<Integer> option1 = new ArrayList<>(original.subList(0, i));
                        ArrayList<Integer> option1rest = new ArrayList<>(original.subList(i + 1, original.size()));
                        option1.addAll(option1rest);
                        boolean option1Valid = true;
                        boolean option2Valid = true;

                        System.out.println("option 1: " + option1);
                        ArrayList<Integer> option2 = new ArrayList<>(original.subList(0, i + 1));
                        ArrayList<Integer> option2rest = new ArrayList<>(original.subList(i + 2, original.size()));
                        option2.addAll(option2rest);
                        System.out.println("option 2: " + option2);

                        for (int j = 0; j < option1.size() - 1; j++) {
                            firstCheck = option1.get(j);
                            nextCheck = option1.get(j + 1);
                            if (firstCheck > nextCheck || (nextCheck - firstCheck) > 3 || firstCheck == nextCheck) {
                                option1Valid = false;
                                break;
                            }
                        }

                        if (!option1Valid) {
                            for (int j = 0; j < option2.size() - 1; j++) {
                                firstCheck = option2.get(j);
                                nextCheck = option2.get(j + 1);
                                if (firstCheck > nextCheck || (nextCheck - firstCheck) > 3 || firstCheck == nextCheck) {
                                    option2Valid = false;
                                    break;
                                }
                            }
                        }

                        if (!option1Valid && !option2Valid) {
                            invalid++;
                            break;
                        }

                        if (option1Valid) {
                            original = option1;
                        } else {
                            original = option2;
                        }
                        i--;
                    }
                } else {
                    if (first < next || (first - next) > 3 || first == next) {
                        ArrayList<Integer> option1 = new ArrayList<>(original.subList(0, i));
                        ArrayList<Integer> option1rest = new ArrayList<>(original.subList(i + 1, original.size()));
                        option1.addAll(option1rest);
                        boolean option1Valid = true;
                        boolean option2Valid = true;

                        System.out.println("option 1: " + option1);
                        ArrayList<Integer> option2 = new ArrayList<>(original.subList(0, i + 1));
                        ArrayList<Integer> option2rest = new ArrayList<>(original.subList(i + 2, original.size()));
                        option2.addAll(option2rest);
                        System.out.println("option 2: " + option2);

                        for (int j = 0; j < option1.size() - 1; j++) {
                            firstCheck = option1.get(j);
                            nextCheck = option1.get(j + 1);
                            if (firstCheck < nextCheck || (firstCheck- nextCheck) > 3 || firstCheck == nextCheck) {
                                option1Valid = true;
                                original = option1;
                            }
                        }

                        if (!option1Valid) {
                            for (int j = 0; j < option2.size() - 1; j++) {
                                firstCheck = option2.get(j);
                                nextCheck = option2.get(j + 1);
                                if (firstCheck < nextCheck || (firstCheck- nextCheck) > 3 || firstCheck == nextCheck) {
                                    option2Valid = true;
                                    original = option2;
                                }
                            }
                        }

                        if (!option1Valid && !option2Valid) {
                            invalid++;
                            break;
                        }

                        if (option1Valid) {
                            original = option1;
                        } else {
                            original = option2;
                        }
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