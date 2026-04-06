package com.example.giftnest.controller;

import com.example.giftnest.dto.PageResponseDto;
import com.example.giftnest.dto.ProductDto;
import com.example.giftnest.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "${giftnest.frontend.url}")
public class ProductController {
    private final IProductService iProductService;

    @GetMapping("/page/{pageNum}")
    public ResponseEntity<PageResponseDto<ProductDto>> getProductsOnPage(@PathVariable(name = "pageNum") int pageNum, @RequestParam("sortType") int sortType) throws InterruptedException{
        Page<ProductDto> productDtoPage = iProductService.getProductsOnPage(pageNum,sortType);
        List<ProductDto> products = productDtoPage.getContent();
        PageResponseDto<ProductDto> response = new PageResponseDto<>();
        response.setContent(products);
        response.setPageNumber(productDtoPage.getNumber()+1); //converting back to 1-based indexing
        response.setPageSize(productDtoPage.getSize());
        response.setTotalElements(productDtoPage.getTotalElements());
        response.setTotalPages(productDtoPage.getTotalPages());
        response.setLast(productDtoPage.isLast());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() throws InterruptedException{
        return ResponseEntity.status(HttpStatus.OK).body(iProductService.getProducts());
    }


}
