package com.example.estore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CreateUserRequest {

    @NotBlank(message = "`email` is required")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "`password` must be at least 8 characters")
    @NotBlank(message = "`password` is required")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "`first_name` is required")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "`last_name` is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "`birth_date` is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // I think DateTimeFormat is preferred here and not pattern
    // Since by using pattern, you need to still validate the range for
    // year, month, and day (e.g. month only ranges from 1-12)
    // and DateTimeFormat should handle this automatically
    private String birthDate;
}