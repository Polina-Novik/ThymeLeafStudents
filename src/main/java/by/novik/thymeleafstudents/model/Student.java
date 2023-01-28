package by.novik.thymeleafstudents.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    @Length(min=4,message = "name is too short") //доб валидации
    private String name;
    @NotEmpty(message = "role can't be empty")
    private String role;
    private boolean isAdmin;
}
