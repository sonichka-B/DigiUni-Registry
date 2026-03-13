package validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadString {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public String readString(String message) {
        System.out.println(message);
        try {
            return reader.readLine();
        } catch (Exception e) {
            return "";
        }
    }


}
