package hh.harjoitustyo.ajanhallintadashboard.domain.event;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByDate(LocalDate date);
}
