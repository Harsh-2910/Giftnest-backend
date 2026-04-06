package com.example.giftnest.service;


import com.example.giftnest.dto.PaymentIntentRequestDto;
import com.example.giftnest.dto.PaymentIntentResponseDto;

public interface IPaymentService {
    PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto);
}
