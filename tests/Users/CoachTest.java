package Users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {

    @Test
    void addFeedToMyPage() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        String feed = "this is my first post";
        coach.addFeedToMyPage(feed);
        assertTrue(coach.getMyPage().getMyFeed().size() == 1 && coach.getMyPage().getMyFeed().get(0).equals(feed));
        coach.addFeedToMyPage(null);
        assertTrue(coach.getMyPage().getMyFeed().size() == 1 && coach.getMyPage().getMyFeed().get(0).equals(feed));

    }

    @Test
    void removeFeedFromMyPage() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        String feed = "this is my first post";
        coach.addFeedToMyPage(feed);
        coach.getMyPage().removeFeedFromMyPage(feed);
        assertEquals(0, coach.getMyPage().getMyFeed().size());
        coach.getMyPage().removeFeedFromMyPage(null);
        assertEquals(0, coach.getMyPage().getMyFeed().size());

    }

    @Test
    void setRole() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        assertEquals(coach.getRole(), ECoachRole.AssistantCoach);
        coach.setRole(ECoachRole.GoalkeeperCoach);
        assertEquals(coach.getRole(), ECoachRole.GoalkeeperCoach);
        coach.setRole(null);
        assertEquals(coach.getRole(), ECoachRole.GoalkeeperCoach);

    }

    @Test
    void setTraining() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        assertEquals(coach.getTraining(), ETraining.CDiploma);
        coach.setTraining(ETraining.UEFAA);
        assertEquals(coach.getTraining(), ETraining.UEFAA);
        coach.setTraining(null);
        assertEquals(coach.getTraining(), ETraining.UEFAA);

    }


    @Test
    void setMyPage() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        String feed = "this is my first post";
        coach.addFeedToMyPage(feed);
        CoachPageEditor newPage = new CoachPageEditor("yossi","cohen",ECoachRole.AssistantCoach,ETraining.CDiploma);
        coach.setMyPage(newPage);
        assertEquals(0, coach.getMyPage().getMyFeed().size());
        coach.setMyPage(null);
        assertEquals(0, coach.getMyPage().getMyFeed().size());

    }

    @Test
    void logout() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        assertEquals(coach.status, EStatus.ONLINE);
        coach.logout();
        assertEquals(coach.status, EStatus.OFFLINE);
    }


    @Test
    void setfName() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        assertEquals(coach.fName,"yossi");
        coach.setfName("yosi");
        assertEquals(coach.fName, "yosi");
        coach.setfName(null);
        assertEquals(coach.fName, "yosi");

    }

    @Test
    void setlName() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        assertEquals(coach.lName,"cohen");
        coach.setlName("levi");
        assertEquals(coach.lName, "levi");
        coach.setlName(null);
        assertEquals(coach.lName, "levi");

    }

    @Test
    void setStatus() {
        Coach coach = new Coach("newCoach", "yossi","cohen",ETraining.CDiploma, ECoachRole.AssistantCoach);
        assertEquals(coach.status, EStatus.ONLINE);
        coach.setStatus(EStatus.OFFLINE);
        assertEquals(coach.status, EStatus.OFFLINE);
    }
}