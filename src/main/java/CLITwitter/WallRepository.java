package CLITwitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WallRepository {

    List<Wall> userWalls;

    public WallRepository() {
        userWalls = new ArrayList<>();
    }

    public void addWall(Wall aWall){
        userWalls.add(aWall);
    }

    public Optional<Wall> getWallForUser(User aUser) {
        Optional<Wall> optionalWall = filterUserWall(aUser);
        return optionalWall;
    }

    private Optional<Wall> filterUserWall(User aUser){
        return userWalls.stream()
                .filter(wall -> wall.belongsTo(aUser))
                .findFirst();
    }
}
