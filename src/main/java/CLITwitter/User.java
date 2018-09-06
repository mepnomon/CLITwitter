package CLITwitter;

import java.util.Objects;

/**
 * User object
 * @author Dorian Dresler
 */
public class User {
    private String name;

    /**
     * Constructs a new user.
     * @param name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Returns username
     * @return the username
     */
    public String getUserName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
