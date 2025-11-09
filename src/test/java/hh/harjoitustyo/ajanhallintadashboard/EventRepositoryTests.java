package hh.harjoitustyo.ajanhallintadashboard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EventRepositoryTests {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Test
    public void CreateEventSharedWithAppUsersTeam() {
        Team team = teamRepository.save(new Team("Kayttajat"));
        AppUser appUser = appUserRepository.save(new AppUser("Testaaja", "test", team));
        eventRepository.save(new Event("Junit test shared", LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(14, 45), true, appUser, null));
        assertEquals(1, eventRepository.findByAppUser(appUser).size());
    }
    @Test
    public void CreatePrivateEvent() {
        Team team = teamRepository.save(new Team("Kayttajat"));
        AppUser appUser = appUserRepository.save(new AppUser("Testaaja", "test", team));
        eventRepository.save(new Event("Junit test shared", LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(14, 45), true, null, appUser.getTeam()));
        assertEquals(1, eventRepository.findByTeam(appUser.getTeam()).size());
    }
} 
