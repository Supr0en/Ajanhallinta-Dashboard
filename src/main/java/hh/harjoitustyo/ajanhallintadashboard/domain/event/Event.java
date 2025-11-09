package hh.harjoitustyo.ajanhallintadashboard.domain.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isPrivate;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Team team;

    public Event() {
        this.title = null;
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.isPrivate = false;
        this.appUser = null;
        this.team = null;
    }
    public Event(String title, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isPrivate, AppUser appUser, Team team) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isPrivate = isPrivate;
        this.appUser = appUser;
        this.team = team;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public boolean getIsPrivate(){
        return isPrivate;
    }
    public void setIsPrivate(boolean isPrivate){
        this.isPrivate = isPrivate;
    }
    public AppUser getAppUser(){
        return appUser;
    }
    public void setAppUser(AppUser appUser){
        this.appUser = appUser;
    }
    public Team getTeam(){
        return team;
    }
    public void setTeam(Team team){
        this.team = team;
    }

    @Override
    public String toString() {
        return "[ Title: " + title + ", date" + date + ", Start Time: " + startTime + ", End Time: " + endTime + " ]";
    }

}
