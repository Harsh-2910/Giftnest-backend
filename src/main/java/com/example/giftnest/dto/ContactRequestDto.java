package com.example.giftnest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Mobile Number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number must be 10 digit")
    private String mobileNumber;

    @NotBlank(message = "Message cannot be blank")
    @Size(min =5, max = 500, message = "Message must be between 5 and 500 characters")
    private String message;

}
