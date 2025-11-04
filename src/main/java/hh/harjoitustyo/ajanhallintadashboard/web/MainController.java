package hh.harjoitustyo.ajanhallintadashboard.web;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.Todo;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.TodoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Controller
public class MainController {

    private final TodoRepository todoRepository;
    private final EventRepository eventRepository;
    private final AppUserRepository appUserRepository;

    public MainController(TodoRepository todoRepository, EventRepository eventRepository, AppUserRepository appUserRepository) {
        this.todoRepository = todoRepository;
        this.eventRepository = eventRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todosList = (List<Todo>) todoRepository.findByDueDate(LocalDate.now());
        List<Event> eventsList = (List<Event>) eventRepository.findByDate(LocalDate.now());

        model.addAttribute("todolist", todosList);
        model.addAttribute("eventlist", eventsList);
        return "index"; // index.html - this will be dashboard page where you navigate to other pages
    }
    @GetMapping("/weekview")
    public String weekview(Model model) {
        LocalDate today = LocalDate.now();

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();

        LocalDate startOfWeek = today.with(firstDayOfWeek);

        List<LocalDate> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDays.add(startOfWeek.plusDays(i));
        }

        Map<LocalDate, List<Event>> eventsByDay = new LinkedHashMap<>();

        for (LocalDate day : weekDays) {
            List<Event> eventsList = eventRepository.findByDate(day);
            eventsList.sort(Comparator.comparing(Event::getStartTime));
            eventsByDay.put(day, eventsList);
        }

        int weekNumber = today.get(weekFields.weekOfYear());
        model.addAttribute("eventsByDay", eventsByDay);
        model.addAttribute("weekNumber", weekNumber);
        return "Weekview"; // Weekview.html
    }
    @GetMapping("/todolist")
    public String todolist(Model model, Principal principal) {
        AppUser appUser = appUserRepository.findByUsername(principal.getName());
        List<Todo> todosList = (List<Todo>) todoRepository.findByAppUser(appUser);
        model.addAttribute("todolist", todosList);
        return "Todolist"; // Todolist.html
    }
    @GetMapping("/notes")
    public String notes() {
        return "Notes"; // Notes.html
    }
}
