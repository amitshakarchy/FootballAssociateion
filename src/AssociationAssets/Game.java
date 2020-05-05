package AssociationAssets;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import System.*;
import Users.*;

/**
 * Class Game represents a game in a specific league and season. It is defined by its date, ID, playing teams and referees.
 * The class offers modifying the events on the game.
 */
public class Game {

    //region Fields
    public static final long HOUR = 3600*1000; // in milli-seconds.
    static int runningID = 0;
    boolean isUpdatable;
    Season season;
    League league;
    int GID;
    Date date;
    Time time;
    //LocalDateTime dateTime = LocalDateTime.of(date, time.toLocalTime());
    Instant endOfUpdateTime;
    Score score;
    Field field;
    Team host;
    Team guest;
    Referee main, side1, side2;
    List<Event> events;
    private List<Fan> observers;
    private EGameStatus status;
    //endregion


    /**
     * The constructor validates that a game does not occur between a team to itself,
     *  and there are 3 different referees
     * @param date
     * @param field
     * @param host
     * @param guest
     * @param main
     * @param side1
     * @param side2
     * @param season
     * @param league
     */
    public Game(Date date,Time time, Field field, Team host, Team guest, Referee main, Referee side1, Referee side2, Season season, League league) throws Exception {

        // Validating methods:
        validateReferees(main,side1,side2);
        validateTeams(host,guest);

        // In case all details are valid, create a new game:

        this.GID = ++runningID;
        isUpdatable = true;
        this.date = date;
        this.time = time;
        //endOfUpdateTime = time.toInstant().plus(Duration.ofHours(7));
        this.field = field;
        this.host = host;
        this.guest = guest;
        this.season = season;
        this.league = league;
        this.main = main;
        this.side1 = side1;
        this.side2 = side2;
        this.score = new Score(); // initializing score, with no value (on game assigning before the game starts.)
        events = new LinkedList<>();
        observers = new ArrayList<>();

        // Write to the log
        Logger.getInstance().addActionToLogger("Game created, GameID: "+ GID);

    }

    //region Validation
    /**
     * Throws an exception in case the to teams are the same
     * @param host
     * @param guest
     * @throws Exception
     */
    private void validateTeams(Team host, Team guest) throws Exception {
        if(host.getTID()==guest.getTID())
            throw new DuplicateValueException();
    }

    /**
     * Throws an exception in case there is a duplicate referee
     * @param main
     * @param side1
     * @param side2
     * @throws Exception
     */
    private void validateReferees(Referee main, Referee side1, Referee side2) throws Exception {

        if(main.getUserName().equals(side1.getUserName())||
                main.getUserName().equals(side2.getUserName())||
                side1.getUserName().equals(side2.getUserName()))
            throw new DuplicateValueException();
    }
    //endregion


    //region Getters & Setters


    public EGameStatus getStatus() {
        return status;
    }

    public void setStatus(EGameStatus status) {
        if(status != null) {
            this.status = status;
        }
    }

    public boolean isUpdatable(int hoursSinceGameStarted) {
        LocalDateTime currentDate = LocalDateTime.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(currentDate.getYear() == date.getYear()){
            if(currentDate.getMonthValue() == date.getMonth() + 1){
                if(currentDate.getDayOfMonth() == date.getDate()){
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                    Time endUpdateTime = new Time(time.getHours() + hoursSinceGameStarted,time.getMinutes(),time.getSeconds());
                    return LocalTime.now().isBefore(LocalTime.parse(endUpdateTime.toString())) && LocalTime.now().isAfter(LocalTime.parse(time.toString()));
                }
            }
        }
//        if(date.getDay() == currentDate.getDay()){
//            if(date.getYear() == currentDate.getYear()){
//                if(date.getMonth() == currentDate.getMonth()){
//                    Calendar cal = Calendar.getInstance();
//                    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
//                    Time endUpdateTime = new Time(time.getHours() + hoursSinceGameStarted,time.getMinutes(),time.getSeconds());
//                    return LocalTime.now().isBefore(LocalTime.parse(endUpdateTime.toString())) && LocalTime.now().isAfter(LocalTime.parse(time.toString()));
//                }
//            }
//        }

        return false;
        
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setHost(Team host) throws Exception {
        // Checking teams are valid
        validateTeams(guest, host);
        // If everything is ok, set the new team
        this.host = host;
    }

    public void setGuest(Team guest) throws Exception {
        // Checking teams are valid
        validateTeams(guest, host);
        // If everything is ok, set the new team
        this.guest = guest;
    }

    public Referee getMain() {
        return main;
    }

    public void setMain(Referee main) throws Exception {
        // Checking referees are valid
        validateReferees(main, side1, side2);
        // If everything is ok, set the new referee
        this.main = main;
    }

    public Referee getSide1() {
        return side1;
    }

    public void setSide1(Referee side1) throws Exception {
        // Checking referees are valid
        validateReferees(main, side1, side2);
        // If everything is ok, set the new referee
        this.side1 = side1;
    }

    public Referee getSide2() {
        return side2;
    }

    public void setSide2(Referee side2) throws Exception {
        // Checking referees are valid
        validateReferees(main, side1, side2);
        // If everything is ok, set the new referee
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

    public int getGID() {
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

    /**
     * This method adds an event into the game's events log. It uses the game's date, and the time the event is created,
     * means the time the function is being called.
     * @param eventType - Enum, describes the event
     * @param description - Freestyle text to describe what happened
     */
    public void addEvent(EEventType eventType, String description) {

        Time time = Time.valueOf(LocalTime.now());
        Event event = new Event(date, time, eventType, description);
        events.add(event);

        // Write to the log
        Logger.getInstance().addActionToLogger("Event was added to gameID: "+GID+", Event type: "+ event.getEventType());

    }

    /**
     *The method receives an event and removes it.
     * @param eventIndex
     */
    public void removeEvent(int eventIndex) {
        Event e= events.get(eventIndex);
        events.remove(eventIndex);
        // Write to the log
        Logger.getInstance().addActionToLogger("Event was removed from gameID: "+GID+", Event type: "+ e.getEventType());
    }

    /**
     * The method offers modifying an event. you must enter its index in order to find it.
     * Time and date cannot be modified.
     * @param eventIndex
     * @param eventType
     * @param description
     */
    public void editEvent(int eventIndex, EEventType eventType, String description) {
        Event event = events.get(eventIndex);// We need to verify that the modification affects the node in the list.
        event.setEventType(eventType);
        event.setDescription(description);
        // Write to the log
        Logger.getInstance().addActionToLogger("Event was edited. gameID: "+GID+", Event type: "+ event.getEventType());
    }


    //endregion

    public void register(Fan observer){
        if(observer != null) {
            this.observers.add(observer);
        }
    }

    public void delete(Fan observer){
        if(observer != null) {
            this.observers.remove(observer);
        }
    }

    public List<Fan> getObservers() {
        return observers;
    }


    public void notifyObserver(String description,EEventType eventType) {

        if (this.observers.size() > 0) {
            for (Fan fan :
                    this.observers) {
                fan.updateGame(description,eventType);
            }

        }
    }


    @Override
    public String toString() {
        return "Game{" +
                "season=" + season.getYear() +
                ", league=" + league.getLeagueName() +
                ", GID=" + GID +
                ", date=" + date.toString() +
                ", time=" + time.toString() +
                ", score=" + score.goalsGuest + ":" + score.goalsHost +
                ", field=" + field.name +
                ", host team=" + host.getName() +
                ", guest team=" + guest.getName() +
                ", main referee=" + main.getfName() + " " + main.getlName() +
                ", side1 referee=" + side1.getfName()+ " " +side1.getlName() +
                ", side2 referee=" + side2.getfName() + " " +side2.getlName() +
                '}';
    }
}
