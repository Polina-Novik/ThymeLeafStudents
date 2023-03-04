package by.novik.thymeleafstudents.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Min(0)
    private int id;
    @Length(min = 4, message = "name is too short")
    private String name;
    @NotEmpty(message = "course can't be empty")
    private String course;
    @Min(15)
    private int age;

}
