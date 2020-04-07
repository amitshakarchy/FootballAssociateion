package AssociationAssets;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import Users.*;

public class Game {

    //region Fields
    String GID;
    Date date;
    Time time;
    Score score;
    Field field;
    Team host;
    Team guest;
    Referee main, side1, side2;
    List<Event> events;
    //endregion


    public Game(Date date, Time time, Field field, Team host, Team guest, Referee main, Referee side1, Referee side2) {
        this.date = date;
        this.time = time;
        this.field = field;
        this.host = host;
        this.guest = guest;
        this.main =null;
        this.side1 =null;
        this.side2 =null;
        this.score = new Score(); // initializing score, with no value (on game assigning before the game starts.)
        events = new LinkedList<>();
    }


    //region Getters & Setters


    public Referee getMain() {
        return main;
    }

    public void setMain(Referee main) {
        this.main = main;
    }

    public Referee getSide1() {
        return side1;
    }

    public void setSide1(Referee side1) {
        this.side1 = side1;
    }

    public Referee getSide2() {
        return side2;
    }

    public void setSide2(Referee side2) {
        this.side2 = side2;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setScore(int scoreHost, int scoreGuest) {
        score.setGoalsHost(scoreHost);
        score.setGoalsGuest(scoreGuest);
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getGID() {
        return GID;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public Score getScore() {
        return score;
    }

    public Field getField() {
        return field;
    }

    public Team getHost() {
        return host;
    }

    public Team getGuest() {
        return guest;
    }

    public List<Event> getEvents() {
        return events;
    }
    //endregion


    //region Event
    public void editEvent(int eventIndex,EEventType eventType, String description) {
        Event event=events.get(eventIndex);// We need to verify that the modification affects the node in the list.
        event.setEventType(eventType);
        event.setDescription(description);
    }

    public void addEvent(EEventType eventType, String description) {

        Time time = Time.valueOf(LocalTime.now());
        Event event = new Event(date, time, eventType, description);
        events.add(event);
    }

    public void removeEvent(int eventIndex) {
       events.remove(eventIndex);
    }
    //endregion

}
