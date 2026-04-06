package com.example.giftnest.controller;

import com.example.giftnest.constants.ApplicationConstants;
import com.example.giftnest.dto.ContactResponseDto;
import com.example.giftnest.dto.OrderResponseDto;
import com.example.giftnest.dto.ResponseDto;
import com.example.giftnest.entity.Product;
import com.example.giftnest.service.IContactService;
import com.example.giftnest.service.IOrderService;
import com.example.giftnest.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IOrderService iOrderService;
    private final IContactService iContactService;
    private final IProductService iProductService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllPendingOrders(){
        return ResponseEntity.ok().body(iOrderService.getAllPendingOrders());
    }

    @PatchMapping("/orders/{orderId}/confirm")
    public ResponseEntity<ResponseDto> confirmOrder(@PathVariable Long orderId) {
        iOrderService.updateOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CONFIRMED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been approved.")
        );
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable Long orderId) {
        iOrderService.updateOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CANCELLED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been cancelled.")
        );
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ContactResponseDto>> getAllOpenMessages() {
        return ResponseEntity.ok(iContactService.getAllOpenMessages());
    }

    @PatchMapping("/messages/{contactId}/close")
    public ResponseEntity<ResponseDto> closeMessage(@PathVariable Long contactId) {
        iContactService.updateMessageStatus(contactId, ApplicationConstants.CLOSED_MESSAGE);
        return ResponseEntity.ok(
                new ResponseDto("200", "Contact #" + contactId + " has been closed.")
        );
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity<String> uploadBulkProducts(@RequestParam("file") MultipartFile file) {
        try {
            List<Product> products = iProductService.parseCsv(file);
//            log.debug(products.toString());
            boolean success = iProductService.saveAllProducts(products);
            return ResponseEntity.status(HttpStatus.CREATED).body("Products Uploaded Successfully");
        }
        catch (Exception ex){
            throw new RuntimeException(ex + "Please check any details missing or Template is not correct");
        }

    }

}
