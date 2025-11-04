package hh.harjoitustyo.ajanhallintadashboard;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
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
    public CommandLineRunner commandLineRunner(TodoRepository todoRepository, EventRepository eventRepository, AppUserRepository appUserRepository) {
        return (args) -> {
            log.info("save test todo");
            todoRepository.save(new Todo("Complete this project", "have the project completed in 2 weeks", LocalDate.of(2025, 10, 17)));
            todoRepository.save(new Todo("Today's test data", "This is a test data for today", LocalDate.now()));
            log.info("save test events");
            eventRepository.save(new Event("testEvent for a day", LocalDate.now(), LocalTime.of(11, 0), LocalTime.of(13, 45)));
            eventRepository.save(new Event("Back End Programming", LocalDate.of(2025, 11, 3), LocalTime.of(11, 0), LocalTime.of(13, 45)));
            eventRepository.save(new Event("Software Development Project 1", LocalDate.of(2025, 11, 4), LocalTime.of(14, 0), LocalTime.of(16, 45)));
            eventRepository.save(new Event("Linux Services", LocalDate.of(2025, 11, 5), LocalTime.of(11, 0), LocalTime.of(13, 45)));
            eventRepository.save(new Event("Software Development project 1", LocalDate.of(2025, 11,6), LocalTime.of(11, 0), LocalTime.of(13, 45)));
            eventRepository.save(new Event("Entrepreneurship and Business Operations", LocalDate.of(2025, 11,6), LocalTime.of(17, 40), LocalTime.of(20, 30)));
            eventRepository.save(new Event("Back End Programming", LocalDate.of(2025, 11, 7), LocalTime.of(8, 15), LocalTime.of(10, 45)));

            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C");
            appUserRepository.save(user1);
            appUserRepository.save(user2);
        };
    }
}
