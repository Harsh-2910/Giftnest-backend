package com.example.giftnest.service;

import com.example.giftnest.dto.ProfileRequestDto;
import com.example.giftnest.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();
    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
