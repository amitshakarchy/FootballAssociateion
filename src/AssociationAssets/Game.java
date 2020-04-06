package AssociationAssets;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Game {

    Date date;
    Time time; //Consider using something else
    Score score;
    List<EEventType> events;//Consider using something else
    AdditionalInfo info;

    public Game(Date date, Time time, Score score, List<EEventType> events, AdditionalInfo info) {
        this.date = date;
        this.time = time;
        this.score = score;
        this.events = events;
        this.info = info;
    }
}
