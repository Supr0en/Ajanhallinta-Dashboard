package hh.harjoitustyo.ajanhallintadashboard.web;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class EventController {
    private final EventRepository eventRepository;
    private final AppUserRepository appUserRepository;

    public EventController(EventRepository eventRepository, AppUserRepository appUserRepository) {
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/addEvent")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "AddEvent";
    }
    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
        return "redirect:/weekview"; // redirect back to todolist
    }
    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute Event event, Principal principal) {
        AppUser appUser = appUserRepository.findByUsername(principal.getName());
        if(event.getIsPrivate()){
          event.setTeam(null);
          event.setAppUser(appUser);
        } else {
          event.setTeam(appUser.getTeam());
          event.setAppUser(null);
        };
        eventRepository.save(event);
        return "redirect:/weekview"; // redirect back to todolist
    }
}
