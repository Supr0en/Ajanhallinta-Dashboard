package hh.harjoitustyo.ajanhallintadashboard.web;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
    public String saveEvent(Event event) {
        eventRepository.save(event);
        return "redirect:/weekview"; // redirect back to todolist
    }
}
