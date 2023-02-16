import Model.User;
import Model.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepository();

    @BeforeEach
    public void setUp() {
        User andre = new User("Andre", "qwertyaaa");
        User babaKlava = new User("BabaKlava", "dsddasdfas");
        User quicksilver = new User("quicksilver", "121121");
        userRepository.addUser(andre);
        userRepository.addUser(babaKlava);
        userRepository.addUser(quicksilver);
    }

    @Test
    @DisplayName("When user repository is empty then userlist is empty")
    public void getEmptyUserList() {
        userRepository.getAllUsers().clear();
        Assertions.assertTrue(userRepository.getAllUsers().isEmpty());
    }

    @Test
    @DisplayName("When some users passed into repository then same users returns")
    public void correctPassingOfUsers() {
        Assertions.assertEquals(
                new User("Andre", "qwertyaaa"),
                userRepository.getAllUsers().get(0));
        Assertions.assertEquals(
                new User("BabaKlava", "dsddasdfas"),
                userRepository.getAllUsers().get(1));

    }

    @Test
    @DisplayName("When some user found in repository by login then repository returns this one")
    public void whenUserIsExistThenReturnThisOne() {
        Optional<User> user = Optional.of(new User("Andre", "qwertyaaa"));
        Optional<User> actualUser = userRepository.findUserByLogin("Andre");
        Assertions.assertEquals(user,actualUser);
    }
    @Test
    @DisplayName("When some user is not found in repository then repository returns an empty Optional")
    public void whenUserIsNotFoundThenRepositoryReturnsEmptyOptional(){
        Assertions.assertTrue(
                userRepository.findUserByLogin("fsdf").isEmpty());
    }
    @Test
    @DisplayName("When some user found in repository by login and pass then repository returns this one")
    public void whenUserFoundByLogAndPassThenReturnThisOne() {
        User user = new User("victor", "12345");
        userRepository.addUser(user);
        Optional<User> actualUser = userRepository.findUserByLoginPassword("victor","12345");
        Assertions.assertEquals(Optional.of(user),actualUser);
    }
    @Test
    @DisplayName("When some user is not found in repository by login or password then repository returns an empty Optional")
    public void whenUserIsNotFoundByLoginAndPasswordThenRepositoryReturnsEmptyOptional(){
        Assertions.assertTrue(
                userRepository.findUserByLoginPassword("Andre","qwerty").isEmpty());
        Assertions.assertTrue(
                userRepository.findUserByLoginPassword("Technique","qwertyaaa").isEmpty());
    }
}
