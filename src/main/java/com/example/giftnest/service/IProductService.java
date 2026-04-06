package com.example.giftnest.service;

import com.example.giftnest.dto.ProductDto;
import com.example.giftnest.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();
    List<Product> parseCsv(MultipartFile file) throws Exception;
    boolean saveAllProducts(List<Product> products);

    Page<ProductDto> getProductsOnPage(int pageNum, int sortType);
};
