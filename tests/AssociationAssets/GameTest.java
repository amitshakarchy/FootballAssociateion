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
    Game game;
    List<Event> events;
    @Before
    public void setUp() throws Exception {
        String GID="111";
        Date date= new Date(2020,12,12);
        Time time= new Time(19,21,0);
        Score score= new Score();
        Field field= new Field("Teddi", "Beer Sheva", 800);
        Team host= null;
        Team guest=null;
        e1= new Event(date,time,EEventType.INJURY,"bla");
        e2= new Event(date,time,EEventType.OFFSIDE,"bli");
        events= new LinkedList<>();
        events.add(e1);
        events.add(e2);
        Referee main, side1, side2;
        main=null;
        side1=null;
        side2=null;
        game= new Game(date,time,field,host,guest,main,side1,side2);
    }


    @Test
    public void addEvent() {
        game.addEvent(EEventType.GOAL,"bla");
        assertEquals(game.getEvents().get(0).getDescription(), "bla");
    }

    @Test
    public void editEvent() {
        game.addEvent(EEventType.GOAL,"bla");
        game.editEvent(0,EEventType.GOAL,"blu");
        assertEquals(game.getEvents().get(0).getDescription(),"blu");
    }

    @Test
    public void removeEvent() {
        game.addEvent(EEventType.GOAL,"bla");
        game.removeEvent(0);
        assertEquals(game.getEvents().size(),0);

    }
}