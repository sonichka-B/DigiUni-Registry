package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class Users {
    String username;
    @NonFinal@Setter
    String password;
    @NonFinal@Setter
    Role role;
}
