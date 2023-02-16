import Exceptions.UserNonUniqueException;
import Model.User;
import Model.UserRepository;
import Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenUserRepositoryIsEmptyThenReturnsEmptyLoginList() {
        when(userRepository.getAllUsers()).thenReturn(Collections.emptyList());
        assertTrue(userService.getLoginList().isEmpty());
    }


    @Test
    void whenUserRepositoryIsFilledThenReturnsFilledLoginList() {
        when(userRepository.getAllUsers()).thenReturn(List.of
                (new User("maria", "e3333"),
                        new User("rita", "9999")));
        List<String> loginList = userService.getLoginList();
        assertTrue(
                loginList.contains("maria")
                        && loginList.contains("rita"));
    }
    @Test
    void whenLoginIsInvalid() {
        assertThatThrownBy(() -> userService.addUser("      ", "dsfdf"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid login");
    }
    @Test
    void whenPasswordIsInvalid() {
        assertThatThrownBy(() -> userService.addUser("dsad", "   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid password");
    }
    @Test
    void whenLoginAndPasswordAreNotValid(){
        assertThatThrownBy(() -> userService.addUser(null, "   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid login");
    }
    @Test
    void whenLoginIsNonUnique(){
        when(userRepository.findUserByLogin(any())).thenReturn(Optional.of(new User("maria", "12345")));
        assertThatThrownBy(() -> userService.addUser("maria", "12345"))
                .isInstanceOf(UserNonUniqueException.class);
    }
    @Test
    void whenAllGood(){
        when(userRepository.findUserByLogin(any())).thenReturn(Optional.empty());
        assertThatNoException().isThrownBy(() -> userService.addUser("victor", "888"));
    }
}
