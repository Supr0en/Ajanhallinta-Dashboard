package hh.harjoitustyo.ajanhallintadashboard.web;

import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUser;
import hh.harjoitustyo.ajanhallintadashboard.domain.security.AppUserRepository;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.Todo;
import hh.harjoitustyo.ajanhallintadashboard.domain.todo.TodoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class TodoController {
    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;

    public TodoController(TodoRepository todoRepository, AppUserRepository appUserRepository) {
        this.todoRepository = todoRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "AddTodo";
    }
    @GetMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/todolist"; // redirect back to todolist
    }
    @PostMapping("/saveTodo")
    public String saveTodo(@ModelAttribute Todo todo, Principal principal) {
        AppUser appUser = appUserRepository.findByUsername(principal.getName());     
        if(todo.getIsPrivate()) {
          todo.setTeam(null);
          todo.setAppUser(appUser);
        } else {
          todo.setTeam(appUser.getTeam());
          todo.setAppUser(null);
        }

        todoRepository.save(todo);
        return "redirect:/todolist"; // redirect back to todolist
    }
}
