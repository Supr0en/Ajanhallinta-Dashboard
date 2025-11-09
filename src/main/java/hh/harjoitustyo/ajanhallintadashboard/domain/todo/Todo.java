package hh.harjoitustyo.ajanhallintadashboard.domain.todo;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private boolean isPrivate;

    @ManyToOne
    private AppUser appUser;
    
    @ManyToOne
    private Team team;

    public Todo() {
        this.name = null;
        this.description = null;
        this.dueDate = null;
        this.isPrivate = false;
        this.appUser = null;
        this.team = null;
    }
    public Todo(String name, String description, LocalDate dueDate, boolean isPrivate, AppUser appUser, Team team) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isPrivate = isPrivate;
        this.appUser = appUser;
        this.team = team;
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
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public boolean getIsPrivate() {
        return isPrivate;
    }
    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    public AppUser getAppUser() {
        return appUser;
    }
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "[Name=" + name + ", Description=" + description + ", DueDate=" + dueDate.toString() + "]";
    }
}
