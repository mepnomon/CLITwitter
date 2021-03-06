package CLITwitter;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
public class UserRepositoryTest {

    UserRepository userRepository;
    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void successfully_save_user_and_get_user_by_name() {
        User aUser = new User("Alice");
        userRepository.save(aUser);
        Optional<User> optionalUser = userRepository.getUserByName("Alice");
        User actualUser = optionalUser.get();
        assertEquals(aUser,actualUser);
    }

    @Test
    public void get_no_user_by_name(){
        assertFalse(userRepository.getUserByName("Alice").isPresent());
    }

    @Test
    public void get_user_from_populated_user_list(){
        User aUserAlice = new User("Alice");
        User aUserBob = new User("Bob");
        User aUserGuy = new User("Guybrush");
        userRepository.save(aUserAlice);
        userRepository.save(aUserBob);
        userRepository.save(aUserGuy);
        Optional<User> optionalUser = userRepository.getUserByName("Guybrush");
        User actualUser = optionalUser.get();
        assertEquals(aUserGuy,actualUser);
    }
}