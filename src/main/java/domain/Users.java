package domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Users {
    private final String username;
    private String password;
    private Role role;

    public Users(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
