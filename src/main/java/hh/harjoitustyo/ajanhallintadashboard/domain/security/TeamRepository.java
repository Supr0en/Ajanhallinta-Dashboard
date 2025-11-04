package hh.harjoitustyo.ajanhallintadashboard.domain.security;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<AppUser, Long> {
}
