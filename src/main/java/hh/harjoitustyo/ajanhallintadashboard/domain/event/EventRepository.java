package hh.harjoitustyo.ajanhallintadashboard.domain.event;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
  @Query("SELECT e FROM Event e WHERE (e.appUser = :appUser OR e.team = :team) AND e.date = :date")
  List<Event> findEventsForUserTeamAndDueDate(
    @Param("appUser") AppUser appUser, 
    @Param("team") Team team, 
    @Param("date") LocalDate date
  );
  List<Event> findByDate(LocalDate date);
  List<Event> findByAppUser(AppUser appUser);
  List<Event> findByTeam(Team team);
}
