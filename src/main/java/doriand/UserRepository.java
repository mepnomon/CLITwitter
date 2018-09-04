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
        User aUser = new User(name);
        Optional<User> returnVal = Optional.of(filterUser(aUser));
        return returnVal;
    }

    public Optional<User> getUser(User aUser){
        return Optional.of(filterUser(aUser));
    }

    private <Optional>User filterUser(User aUser){
        User returnUser = null;
        for(User u : userList){
            if(u.getUserName().equals(aUser.getUserName())){
                returnUser = u;
            }
        }
        return returnUser;
    }
}
