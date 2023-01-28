package by.novik.thymeleafstudents.controller;

import by.novik.thymeleafstudents.model.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("users")
@Slf4j
@SessionAttributes("user") //этого юзера в рамках сессии надо одного держать при переходе со страницы на страницк

public class UserController {
    private final List<User> users=new ArrayList<>(); //финал потому что не надо будет во второй раз делать юзера
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users",users);
        return "users";
    }
    @PostMapping
    public String create(Model model, @Valid User user, Errors errors, SessionStatus status) {
if (errors.hasErrors()) {
    log.info("user is incorrect: {}",user);
    model.addAttribute("users",users);
    return "users";
}
log.info("user is correct: {}",user);
users.add(new User(0,user.getName(),user.getRole(),false)); //не наш юзер, для сохранения в листе
model.addAttribute("users",users);
status.setComplete(); //тут говорим сохранили - сессия почистись
        user.setName("");
        user.setRole(""); //чтоб очищать форму когда что-то ввел, но это криво косо, просто сверху не работает
        //поменяли юзеры поля снизу тоже, изменили сохраненного в сессии
        return "users";
    }
    @ModelAttribute(name="user") //короче в сессии один юзер мы его то заполняем то обнуляем.
    public User getNewUser() {
        return new User();
    }
}
