package hh.harjoitustyo.ajanhallintadashboard;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.TeamRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.Todo;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.TodoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class AjanhallintaDashboardApplication {
    private static final Logger log = LoggerFactory.getLogger(AjanhallintaDashboardApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AjanhallintaDashboardApplication.class, args);
    }

    @Bean
    public CommandLineRunner DemoData(TodoRepository todoRepository, EventRepository eventRepository, AppUserRepository appUserRepository, TeamRepository teamRepository) {
        return (args) -> {
            log.info("save test Teams");
            Team team = new Team("Kayttajat");
            teamRepository.save(team);
            log.info("save test Users");
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", team);
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", team);
            appUserRepository.save(user1);
            appUserRepository.save(user2);
            log.info("save test Todo");
            todoRepository.save(new Todo("Complete this project", "have the project completed in 2 weeks", LocalDate.of(2025, 11, 9), false, null, team));
            todoRepository.save(new Todo("Today's test data", "This is a test data for today", LocalDate.now(), true, user2, null));
            log.info("save test Events");
            eventRepository.save(new Event("testEvent for a day", LocalDate.now(), LocalTime.of(11, 0), LocalTime.of(13, 45), false, null, team));
            eventRepository.save(new Event("Back End Programming", LocalDate.of(2025, 11, 10), LocalTime.of(11, 0), LocalTime.of(13, 45), false, null, team));
            eventRepository.save(new Event("Software Development Project 1", LocalDate.of(2025, 11, 11), LocalTime.of(14, 0), LocalTime.of(16, 45), false, null, team));
            eventRepository.save(new Event("Linux Services", LocalDate.of(2025, 11, 12), LocalTime.of(11, 0), LocalTime.of(13, 45), false, null, team));
            eventRepository.save(new Event("Software Development project 1", LocalDate.of(2025, 11,13), LocalTime.of(11, 0), LocalTime.of(13, 45), false, null, team));
            eventRepository.save(new Event("Entrepreneurship and Business Operations", LocalDate.of(2025, 11,13), LocalTime.of(17, 40), LocalTime.of(20, 30), false, null, team));
            eventRepository.save(new Event("Front End Programming", LocalDate.of(2025, 11, 14), LocalTime.of(8, 15), LocalTime.of(10, 45), false, null, team));
        };
    }
}
