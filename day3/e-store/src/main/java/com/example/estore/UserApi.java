package com.example.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());

        // Calculate age
        int age = LocalDate.now().getYear() - birthDate.getYear();

        // If age is below 15, throw an InvalidUserAgeException(you must create this exception)
        if (age < 15) {
            throw new InvalidUserAgeException("Age must be at least 15");
        } else {
            return new CreateUserResponse("RANDOM FOR NOW");
        }
    }
}
