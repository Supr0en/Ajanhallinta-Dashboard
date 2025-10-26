package hh.harjoitustyo.ajanhallintadashboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index() {
        return "index"; // index.html - this will be dashboard page where you navigate to other pages
    }
    @GetMapping("/weekview")
    public String weekview() {
        return "Weekview"; // Weekview.html
    }
    @GetMapping("/todolist")
    public String todolist() {
        return "Todolist"; // Todolist.html
    }
    @GetMapping("/notes")
    public String notes() {
        return "Notes"; // Notes.html
    }
}
