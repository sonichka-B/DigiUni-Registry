package validation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import exceptions.IncorrectDataException;
import validation.ReadString;
public class ReadPhoneNumber {
   // private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ReadString readString = new ReadString();

    public String isValidPhoneNumber(String phoneNumber) {
        while (true) {
            String number=readString.readString(phoneNumber);
            try {
                ValidPhoneNumber.isValidPhoneNumber(number);
                return phoneNumber;
            }catch(IncorrectDataException e){
                System.out.println("Помилка: "+e.getMessage());
            }
            }
        }
    }

