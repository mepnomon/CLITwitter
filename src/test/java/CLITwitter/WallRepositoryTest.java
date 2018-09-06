package CLITwitter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WallRepositoryTest {

    WallRepository aWallRepository;

    @Before
    public void setUp() throws Exception {

        aWallRepository = new WallRepository();
    }

    @Test
    public void get_a_wall_for_a_user(){

        User aUser = new User("Alice");
        Wall aWall = new Wall(aUser);
        aWallRepository.addWall(aWall);
        assertEquals(aWall,aWallRepository.getWallForUser(aUser).get());
    }
}