package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final List<User> userList = new ArrayList<>();


    public List<User> getAllUsers() {
        return userList;
    }

    public Optional<User> findUserByLogin(String login) {
        return getAllUsers().stream()
                .filter(u->u.getLogin().equals(login))
                .findFirst();
    }

    public Optional<User> findUserByLoginPassword(String login, String password) {
        return this.userList.stream()
                .filter(u->u.getLogin().equals(login)&&u.getPassword().equals(password))
                .findFirst();
    }

    public void addUser(User user) {
        this.userList.add(user);
    }
}
