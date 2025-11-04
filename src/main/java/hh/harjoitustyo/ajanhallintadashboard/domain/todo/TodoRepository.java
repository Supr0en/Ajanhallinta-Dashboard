package hh.harjoitustyo.ajanhallintadashboard.domain.todo;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByDueDate(LocalDate dueDate);
    List<Todo> findByAppUser(AppUser appUser);
}
