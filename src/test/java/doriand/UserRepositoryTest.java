package doriand;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserRepositoryTest {

    UserRepository userRepository;
    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepository();
    }

    @Test
    public void successfully_save_user_and_get_user_by_name() {
        User aUser = new User("Alice");
        userRepository.save(aUser);
        Optional<User> optionalUser = userRepository.getUserByName("Alice");
        User actualUser = optionalUser.get();
        Assert.assertEquals(aUser,actualUser);
    }

    @Test
    public void get_no_user_by_name(){
        //Optional<User> = Optional.ofNullable()
        //Assert.assertThat(userRepository.getUserByName("Alice").isPresent(),null);
        Assert.fail("not yet implemented");
    }
}