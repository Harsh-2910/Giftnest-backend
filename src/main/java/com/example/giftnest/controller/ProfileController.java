package com.example.giftnest.controller;

import com.example.giftnest.dto.ProfileRequestDto;
import com.example.giftnest.dto.ProfileResponseDto;
import com.example.giftnest.service.IProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final IProfileService iProfileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(){
        log.debug("Inside controller of profile");
        ProfileResponseDto responseDto = iProfileService.getProfile();
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<ProfileResponseDto> updateProfile(@Validated @RequestBody ProfileRequestDto profileRequestDto){
        ProfileResponseDto profileResponseDto = iProfileService.updateProfile(profileRequestDto);
        return ResponseEntity.ok(profileResponseDto);
    }
}
