package com.example.giftnest.service.impl;

import com.example.giftnest.dto.ProductDto;
import com.example.giftnest.entity.Product;
import com.example.giftnest.repository.ProductRepository;
import com.example.giftnest.service.IProductService;
import com.example.giftnest.config.GiftnestProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.opencsv.CSVReader;

import static java.lang.Integer.valueOf;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final GiftnestProps giftnestProps;

    @Cacheable("products")
    @Override
    public List<ProductDto> getProducts(){
        //throw new RuntimeException("Not Implemented");
        return productRepository.findAll().stream().map(this::transformToDto).collect(Collectors.toList());
    }

    private ProductDto transformToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

    public List<Product> parseCsv(MultipartFile file) throws Exception{
        List<Product> products = new ArrayList<>();

        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CSVReader csvReader = new CSVReader(reader);
            String[] line;

            csvReader.readNext(); // skip header

            while ((line = csvReader.readNext()) != null) {
                Product p = new Product();
                p.setName(line[0]);
                p.setDescription(line[1]);
                p.setPrice(new BigDecimal(line[2]));
                p.setPopularity(valueOf(line[3]));
                p.setImageUrl(line[4]);

                products.add(p);
            }
        }

        return products;
    }

    public boolean saveAllProducts(List<Product> products){
        try{
            productRepository.saveAll(products);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Page<ProductDto> getProductsOnPage(int pageNum, int sortType) {
        int pageSize = giftnestProps.getPageSize();
        Sort sort ;
        switch (sortType){
            case 2:
                sort = Sort.by("price").ascending();
                break;
            case 3:
                sort = Sort.by("price").descending();
                break;
            default:
                sort = Sort.by("popularity").descending();
        }

        if(null!=giftnestProps.getProducts() && null!=giftnestProps.getProducts().get("pageSize")){
            pageSize = Integer.parseInt(giftnestProps.getProducts().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<ProductDto> productDtoPage = productRepository.findAll(pageable).map(this::transformToDto);
        return productDtoPage;
    }
}
