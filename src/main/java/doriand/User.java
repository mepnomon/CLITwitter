package doriand;

import java.util.Objects;

public class User {
    private String alice;

    public User(String alice) {
        this.alice = alice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(alice, user.alice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alice);
    }
}
