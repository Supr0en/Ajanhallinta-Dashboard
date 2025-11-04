package hh.harjoitustyo.ajanhallintadashboard.domain.security;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
}
