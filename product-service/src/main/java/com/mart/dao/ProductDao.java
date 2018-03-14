package com.mart.dao;

import java.util.List;

import com.mart.entity.Product;

public interface ProductDao {

	public Product addProduct(Product product)throws Exception;

	public List<Product> findByType(String type)throws Exception;

	public Boolean deleteProductById(long id)throws Exception;

	void addTestUser() throws Exception;
}
