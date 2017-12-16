package com.mart.service;

import java.util.List;

import com.mart.dto.ProductDto;


public interface ProductService {
	
	public ProductDto addProduct(ProductDto product) throws Exception;

	public List<ProductDto> findByType(String type)throws Exception;

	public Boolean deleteProductById(long id)throws Exception;

}
