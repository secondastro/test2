package Service;

import Exceptions.UserNonUniqueException;
import Model.User;
import Model.UserRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getLoginList() {
        return userRepository.getAllUsers().stream().map(User::getLogin).toList();
    }

    public void addUser(String login, String password) {
        if (StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("invalid login");
        }
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("invalid password");
        }
        if (userRepository.findUserByLogin(login).isPresent()) {
            throw new UserNonUniqueException("This user already exists");
        }
        userRepository.addUser(new User(login,password));

    }

}
