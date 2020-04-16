package Users;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import System.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemManagerTest {

    @Test
    void closeTeam() {
    }

    @Test
    void removeUser() {
    }

    @Test
    void getComplainsAndresponseOnComplain() {
        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
        SystemManager systemManager = new SystemManager("1","1","1");
        player.submitComplain("new complain");
        player.submitComplain("complain");
        assertEquals(Complains.getInstance().getComplain().size() , 2 );
        List<Pair<String, Fan>> complains = systemManager.getComplains();
        systemManager.responseOnComplain("take care" , complains.get(0));
        assertEquals(Complains.getInstance().getComplain().size() , 1 );
        Complains.getInstance().WriteObjectToFile(new File("C:\\Users\\יובל בן אליעזר\\Desktop\\complain.txt"));
        Logger.getInstance().WriteObjectToFile(new File("C:\\Users\\יובל בן אליעזר\\Desktop\\logger.txt"));

    }


    @Test
    void getLogInformation() {
        Logger.getInstance();
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        SystemManager systemManager = new SystemManager("1","1","1");
        assertEquals(systemManager.getLogInformation().size() , 2);
    }

}