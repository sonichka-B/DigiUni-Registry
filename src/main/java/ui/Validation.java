package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Validation {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message + "(від "+min+" до "+max + "): ");
            try {
                String line = reader.readLine();
                int number = Integer.parseInt(line);
                if (number >=min&&number<= max) {
                    return number;
                }
                else {
                    System.out.println("Такої опції немає! Введіть число від " + min + " до " + max);
                }

            } catch (Exception e) {
                System.out.println("Це не число");
            }
        }
    }

    public String readString(String message) {
        System.out.println(message);
        try {
            return reader.readLine();
        } catch (Exception e) {
            return "";
        }
    }
    public void waitZeroToExit() {
        System.out.println();
        int input = -1;
        while (input != 0) {
            input = readInt("Введіть 0, щоб повернутися назад" ,0,0);
        }
    }
}