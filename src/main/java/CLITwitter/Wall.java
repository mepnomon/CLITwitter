package CLITwitter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Wall {

    private User aUser;
    private List<User> followedUsers;

    /**
     * Constructs a wall
     * @param aUser the owner of the wall
     */
    public Wall(User aUser) {
        this.aUser = aUser;
        followedUsers = new ArrayList<>();
    }

    /**
     * Checks if wall belongs to user
     * @param aUser
     * @return true or false
     */
    public boolean belongsTo(User aUser){
        return aUser == this.aUser;
    }

    public User getUser(){
        return aUser;
    }

    /**
     * List of Users followed by wall owner
     * @return list of users
     */
    public List<User> isFollowing() {
        return followedUsers;
    }

    /**
     * Follow a user
     * @param aUser the User to follow.
     */
    public void followUser(User aUser) {
        if(!userFollowed(aUser) && aUser != this.aUser) {
            followedUsers.add(aUser);
        }
    }

    /*
      Returns true if a user is already being followed
     */
    private boolean userFollowed(User aUser){
        return followedUsers.stream().anyMatch(user -> user.equals(aUser));
    }
}
