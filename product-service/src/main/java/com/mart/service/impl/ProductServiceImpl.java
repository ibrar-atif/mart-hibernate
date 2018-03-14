package com.mart.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mart.dao.ProductDao;
import com.mart.dto.ProductDto;
import com.mart.entity.Product;
import com.mart.service.ProductService;

@Service

public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public ProductDto addProduct(ProductDto productDto) throws Exception {
		Product product = new Product();
		BeanUtils.copyProperties(product, productDto);
		product = productDao.addProduct(product);
		productDto.setId(product.getId());
		//productDao.addTestUser();
		
		
		return productDto;
	}
	
	@Transactional
	public List<ProductDto> findByType(String type) throws Exception {
		
		List<Product> list = productDao.findByType(type);
		List<ProductDto> dtoList = new ArrayList<ProductDto>();
				
		for(Product ob:list){
			ProductDto dtoOb = new ProductDto();
			BeanUtils.copyProperties(dtoOb,ob);
			dtoList.add(dtoOb);
		}
		return dtoList;
	}

	@Transactional
	public Boolean deleteProductById(long id) throws Exception {
		
		return productDao.deleteProductById(id);
	}
	
}
