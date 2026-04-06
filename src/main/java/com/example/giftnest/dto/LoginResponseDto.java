package com.example.giftnest.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
