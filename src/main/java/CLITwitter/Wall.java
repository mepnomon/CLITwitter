package CLITwitter;

import java.util.List;

public class Wall {

    private User aUser;

    public Wall(User aUser) {
        this.aUser = aUser;
    }

    public boolean belongsTo(User aUser){
        return aUser == this.aUser;
    }

    public List<User> getUsersFollowed() {
            //should contain names of users a user is following
        return null;
    }

    public void addUsersToFollow(User aUser) {

    }
}
