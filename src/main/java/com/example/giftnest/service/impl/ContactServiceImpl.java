package com.example.giftnest.service.impl;

import com.example.giftnest.constants.ApplicationConstants;
import com.example.giftnest.dto.ContactRequestDto;
import com.example.giftnest.dto.ContactResponseDto;
import com.example.giftnest.entity.Contact;
import com.example.giftnest.exception.ResourceNotFoundException;
import com.example.giftnest.repository.ContactRepository;
import com.example.giftnest.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        try {
            Contact contact = transformToEntity(contactRequestDto);
            // createdAt and createdBy are handled by JPA Auditing by introducing Base Entity
//            contact.setCreatedAt(Instant.now());
//            contact.setCreatedBy(contactRequestDto.getName());
            contactRepository.save(contact);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ContactResponseDto> getAllOpenMessages() {
        List<Contact> contacts = contactRepository.findByStatus(ApplicationConstants.OPEN_MESSAGE);
        return contacts.stream().map(this::mapToContactResponseDTO).collect(Collectors.toList());
    }

    private ContactResponseDto mapToContactResponseDTO(Contact contact) {
        ContactResponseDto responseDTO = new ContactResponseDto(
                contact.getContactId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMobileNumber(),
                contact.getMessage(),
                contact.getStatus()
        );
        return responseDTO;
    }

    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact", "ContactID", contactId.toString())
        );
        contact.setStatus(status);
        contactRepository.save(contact);
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto){
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto,contact);
        contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
        return contact;
    }
}
