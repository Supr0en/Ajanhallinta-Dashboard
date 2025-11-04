package hh.harjoitustyo.ajanhallintadashboard.domain.security;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppUser> members = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<AppUser> getMembers() {
        return members;
    }
    public void setMembers(List<AppUser> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", members=" + getMembers() + '}';
    }
}
