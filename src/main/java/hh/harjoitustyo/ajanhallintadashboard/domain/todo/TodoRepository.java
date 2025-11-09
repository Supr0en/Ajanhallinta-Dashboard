package hh.harjoitustyo.ajanhallintadashboard.domain.todo;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
  @Query("SELECT t FROM Todo t WHERE t.appUser = :appUser OR t.team = :team AND t.dueDate = :dueDate")
  List<Todo> findTodosForUserTeamAndDueDate(
    @Param("appUser") AppUser appUser,
    @Param("team") Team team,
    @Param("dueDate") LocalDate dueDate
  );
  List<Todo> findByAppUser(AppUser appUser);
  List<Todo> findByTeam(Team team);
}
