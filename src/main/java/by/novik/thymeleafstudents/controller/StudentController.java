package by.novik.thymeleafstudents.controller;

import by.novik.thymeleafstudents.model.Student;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("students")
@Slf4j
    @SessionAttributes("student")

public class StudentController {
    private final List<Student> students = new ArrayList<>(Arrays.asList(new Student(1, "Polina", "Chemistry", 20),
            new Student(2, "Alina", "Biology", 20), new Student(3, "Kalina", "Chemistry", 21)));

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", students);
        return "students";
    }


    @PostMapping
    public String create(Model model, @Valid Student student, Errors errors, SessionStatus status) {
        if (errors.hasErrors()) {
            log.info("student is incorrect: {}", student);
            model.addAttribute("students", students);
            return "students";
        }
        log.info("student is correct: {}", student);
        students.add(new Student(student.getId(), student.getName(), student.getCourse(), student.getAge()));
        model.addAttribute("students", students);
        status.setComplete();
        student.setAge(15);
        student.setId(0);
        student.setName("");
        student.setCourse("");
        return "students";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        Student student = students.stream().filter(stud -> stud.getId() == id).findAny().orElseThrow();
        students.remove(student);
        return "redirect:/students";
    }

    @ModelAttribute(name = "student")
    public Student getNewStudent() {
        return new Student();
    }
}
