package com.example.giftnest.service;

import com.example.giftnest.dto.ContactRequestDto;
import com.example.giftnest.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
    List<ContactResponseDto> getAllOpenMessages();
    void updateMessageStatus(Long contactId, String status);
}
