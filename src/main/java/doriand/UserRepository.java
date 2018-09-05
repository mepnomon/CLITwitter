package doriand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    List<User> userList;

    public UserRepository() {
        userList = new ArrayList<>();
    }

    public void save(User aUser) {
        userList.add(aUser);
    }

    public Optional<User> getUserByName(String name){
        return userList.stream()
                .filter(user -> name.equals(user.getUserName()))
                .findFirst();
    }
}
