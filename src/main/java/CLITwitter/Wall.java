package CLITwitter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Wall {

    private User aUser;
    private List<User> followedUsers;
    public Wall(User aUser) {
        this.aUser = aUser;
        followedUsers = new ArrayList<>();
    }

    public boolean belongsTo(User aUser){
        return aUser == this.aUser;
    }

    public User getUser(){
        return aUser;
    }

    public List<User> isFollowing() {
        return followedUsers;
    }

    public void addUsersToFollow(User aUser) {
        followedUsers.add(aUser);
    }
}
