package com.example.giftnest.controller;

import com.example.giftnest.dto.PaymentIntentRequestDto;
import com.example.giftnest.dto.PaymentIntentResponseDto;
import com.example.giftnest.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService iPaymentService;
    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentIntentResponseDto> createPaymentIntent(@RequestBody PaymentIntentRequestDto requestDto){
        PaymentIntentResponseDto response = iPaymentService.createPaymentIntent(requestDto);
        return ResponseEntity.ok(response);
    }
}
