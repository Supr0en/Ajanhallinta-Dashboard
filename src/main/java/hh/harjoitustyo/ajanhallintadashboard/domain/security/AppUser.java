package hh.harjoitustyo.ajanhallintadashboard.domain.security;

import hh.harjoitustyo.ajanhallintadashboard.domain.todo.Todo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public AppUser() {
        this.username = null;
        this.passwordHash = null;
        this.team = null;
    }
    public AppUser(String username, String passwordHash, Team team) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.team = team;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "[Id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", team= " + team + "]";
    }

}

