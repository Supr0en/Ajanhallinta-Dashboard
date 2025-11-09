package hh.harjoitustyo.ajanhallintadashboard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AppUserRepositoryTests {
  @Autowired
  private AppUserRepository appUserRepository;
  @Test
  public void createAndFindNewAppUser() { // test for adding new User and find user by username
      AppUser appUser = new AppUser("Testi Terhaaja", "test", null);
      appUserRepository.save(appUser);
      assertThat(appUserRepository.findByUsername("Testi Terhaaja")).isNotNull();
      appUserRepository.delete(appUser);
  }
}
