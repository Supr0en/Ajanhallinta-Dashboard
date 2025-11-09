package hh.harjoitustyo.ajanhallintadashboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.Todo;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.TodoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void CreateSharedTodoWithAppUsersTeam() {
        Team team = teamRepository.save(new Team("Team 1"));
        AppUser appUser = appUserRepository.save(new AppUser("Testaaja", "test", team));
        todoRepository.save(new Todo("Test creation of new todo", "in this test we test if new todo will be added with Team", LocalDate.now(), false, null, appUser.getTeam()));
        assertEquals(1, todoRepository.findByTeam(appUser.getTeam()).size());
    }
    @Test
    public void CreatePrivateTodo() {
        Team team = teamRepository.save(new Team("Team 1"));
        AppUser appUser = appUserRepository.save(new AppUser("Testaaja", "Test", team));
        todoRepository.save(new Todo("Test of creating todo", "new private todo", LocalDate.now(), false, appUser, null));
        assertEquals(1, todoRepository.findByAppUser(appUser).size());
    }
}
