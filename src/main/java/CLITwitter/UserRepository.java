package CLITwitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository to store users
 * @author Dorian Dressler
 */
public class UserRepository {

    List<User> userList;

    /**
     * Constructs a user repository.
     */
    public UserRepository() {
        userList = new ArrayList<>();
    }

    /**
     * Saves a user.
     * @param aUser
     */
    public void save(User aUser) {
        userList.add(aUser);
    }

    /**
     * Returns a user if present
     * @param name the user's name
     * @return a user / or no user
     */
    public Optional<User> getUserByName(String name){
        return userList.stream()
                .filter(user -> name.equals(user.getUserName()))
                .findFirst();
    }
}
