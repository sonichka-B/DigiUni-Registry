import exceptions.IncorrectDataException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import validation.ValidEmail;
import validation.ValidPhoneNumber;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionalValidation {
    @ParameterizedTest
    @ValueSource(strings = {"test@ukma.edu.ua", "user.name@gmail.com", "student-123@ukr.net"})
    void validEmail(String email){
        assertDoesNotThrow(() -> {
            ValidEmail.isValidEmail(email);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"test@", "user.name", "plainaddress", "@domain.com", "", "   "})
    void invalidEmail(String email){
        assertThrows(IncorrectDataException.class, () -> {
            ValidEmail.isValidEmail(email);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"+380123456789", "+380945826184"})
    void validPhone(String num){
        assertDoesNotThrow(() -> {
            ValidPhoneNumber.isValidPhoneNumber(num);
        });
    }
    @ParameterizedTest
    @ValueSource(strings = {"+123123456789", "380945826184", "2334", "+3801234"})
    void invalidPhone(String num){
        assertThrows(IncorrectDataException.class, () -> {
            ValidPhoneNumber.isValidPhoneNumber(num);
        });
    }

}
