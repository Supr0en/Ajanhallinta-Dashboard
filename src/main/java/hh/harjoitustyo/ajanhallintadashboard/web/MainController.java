package hh.harjoitustyo.ajanhallintadashboard.web;

import hh.harjoitustyo.ajanhallintadashboard.domain.event.Event;
import hh.harjoitustyo.ajanhallintadashboard.domain.event.EventRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.Todo;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.TodoRepository;
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
    public String index(Model model, Principal principal) {
        AppUser appUser = appUserRepository.findByUsername(principal.getName());
        List<Todo> todosList = (List<Todo>) todoRepository.findTodosForUserTeamAndDueDate(appUser, appUser.getTeam(), LocalDate.now());
        List<Event> eventsList = (List<Event>) eventRepository.findEventsForUserTeamAndDueDate(appUser, appUser.getTeam(), LocalDate.now());

        model.addAttribute("todolist", todosList);
        model.addAttribute("eventlist", eventsList);
        return "index"; // index.html - this will be dashboard page where you navigate to other pages
    }
    @GetMapping("/weekview")
    public String weekview(Model model, Principal principal) {
        AppUser appUser = appUserRepository.findByUsername(principal.getName());
        LocalDate today = LocalDate.now();

        WeekFields weekFields = WeekFields.ISO;
        DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();

        LocalDate startOfWeek = today.with(firstDayOfWeek);

        List<LocalDate> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDays.add(startOfWeek.plusDays(i));
        }

        Map<LocalDate, List<Event>> eventsByDay = new LinkedHashMap<>();

        for (LocalDate day : weekDays) {
            List<Event> eventsList = eventRepository.findEventsForUserTeamAndDueDate(appUser, appUser.getTeam(), day);
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
        List<Todo> TodoList = (List<Todo>) todoRepository.findByAppUser(appUser);
        List<Todo> TeamTodoList = (List<Todo>) todoRepository.findByTeam(appUser.getTeam());
        model.addAttribute("teamTodoList", TeamTodoList);
        model.addAttribute("todoList", TodoList);
        return "Todolist"; // Todolist.html
    }
    @GetMapping("/notes")
    public String notes() {
        return "Notes"; // Notes.html
    }
}
