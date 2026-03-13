package validation;

import exceptions.IncorrectDataException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadEmail {
   // private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
   private ReadString readString = new ReadString();

    public String isValidEmail(String email) {
        while (true) {
            String input=readString.readString(email);
            try {
                ValidEmail.isValidEmail(input);
                return email;
            }catch(IncorrectDataException e){
                System.out.println("Помилка: "+e.getMessage());
            }
        }
    }
}
