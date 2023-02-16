package Model;

import java.util.Objects;

public class User {
    private final String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getLogin().equals(user.getLogin()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }
}
