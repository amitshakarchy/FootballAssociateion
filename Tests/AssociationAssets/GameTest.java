package AssociationAssets;

import Users.Referee;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Event e1;
    Event e2;
    Field field;
    Team host, guest;
    Season season;
    League league;
    Game game;
    Referee main, side1, side2;
    Date date;
    List<Event> events;

    @Before
    public void setUp() throws Exception {
        String GID = "111";
        date = new Date(2020, 12, 12);
        Time time = new Time(19, 21, 0);
        Score score = new Score();
        league = new League("league");
        season = new Season("1993");
        field = new Field("Teddi", "Beer Sheva", 800);
        host = new Team(1, field, null, season, null, null, null, null);
        guest = new Team(2, field, null, season, null, null, null, null);
        ;
        e1 = new Event(date, time, EEventType.INJURY, "bla");
        e2 = new Event(date, time, EEventType.OFFSIDE, "bli");
        events = new LinkedList<>();
        events.add(e1);
        events.add(e2);
        main = new Referee("1", "main", "main");
        side1 = new Referee("2", "side1", "side1");
        side2 = new Referee("3", "side2", "side1");
        game = new Game(date, time, field, host, guest, main, side1, side2, season, league);
    }



    @Test
    public void addEvent() {
        game.addEvent(EEventType.GOAL, "bla");
        assertEquals(game.getEvents().get(0).getDescription(), "bla");
    }

    @Test
    public void editEvent() {
        game.addEvent(EEventType.GOAL, "bla");
        game.editEvent(0, EEventType.GOAL, "blu");
        assertEquals(game.getEvents().get(0).getDescription(), "blu");
    }

    @Test
    public void removeEvent() {
        game.addEvent(EEventType.GOAL, "bla");
        game.removeEvent(0);
        assertEquals(game.getEvents().size(), 0);

    }

    @Test(expected = DuplicateValueException.class)
    public void setMain() throws Exception {
        game.setMain(side1);
    }

    @Test(expected = DuplicateValueException.class)
    public void setSide1() throws Exception {
        game.setSide1(main);
    }

    @Test(expected = DuplicateValueException.class)
    public void setSide2() throws Exception {
        game.setSide2(main);
    }

    @Test
    public void getters() {
        assertEquals(game.getEvents().size(), 0);
        assertEquals(game.getDate().toString(), date.toString());
        assertEquals(game.getField().getCity(), field.getCity());
        assertEquals(game.getGuest().getTID(), guest.getTID());
        assertEquals(game.getHost().getTID(), host.getTID());
        assertEquals(game.getLeague().getLeagueName(),league.getLeagueName() );
        assertEquals(game.getMain().getfName(),main.getfName() );
    }


}

