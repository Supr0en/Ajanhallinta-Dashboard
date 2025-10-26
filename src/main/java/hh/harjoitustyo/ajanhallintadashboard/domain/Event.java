package hh.harjoitustyo.ajanhallintadashboard.domain;

import java.util.Date;

public class Event {
    private long id;
    private String name;
    private String description;
    private Date Date;

    public Event() {
        this.name = "";
        this.description = "";
        this.Date = new Date();
    }
    public Event(long id, String name, String description, Date Date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.Date = Date;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return Date;
    }
    public void setDate(Date date) {
        this.Date = date;
    }

    @Override
    public String toString() {
        return "[eventName=" + name + ", eventDescription=" + description + ", eventDate=" + Date.toString() + "]";
    }
}
