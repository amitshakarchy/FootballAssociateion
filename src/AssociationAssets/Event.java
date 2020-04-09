package AssociationAssets;

import AssociationAssets.EEventType;

import java.sql.Time;
import java.util.Date;

public class Event {

    Date date;
    Time time;
    EEventType EventType;
    String description;


    public Event(Date date, Time time, EEventType eventType, String description) {
        this.date = date;
        this.time = time;
        EventType = eventType;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public EEventType getEventType() {
        return EventType;
    }

    public void setEventType(EEventType eventType) {
        EventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
