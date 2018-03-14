package com.mart.controller;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mart.dto.ErrorMsg;
import com.mart.dto.ProductDto;
import com.mart.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public ResponseEntity<ProductDto> addProduct(@RequestBody  ProductDto productDto)throws Exception,HibernateException{
		System.out.println("success");
		productDto = productService.addProduct(productDto);
		//if(productDto.getName())
		//
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
		
	@RequestMapping(value="/findbytype/{type}", method=RequestMethod.GET)
	public ResponseEntity<List<ProductDto>> findByType(@PathVariable("type") String type)throws Exception,HibernateException{
		List<ProductDto> list = productService.findByType(type);
		return new ResponseEntity<List<ProductDto>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProductById(@PathVariable("id") long id)throws Exception,HibernateException{
		Boolean status = productService.deleteProductById(id);
		return new ResponseEntity<Boolean>(status,HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMsg> handleException(Exception ex) {
		ex.printStackTrace();
		ErrorMsg error = new ErrorMsg();
		error.setStatus("ERROR");
		error.setErrorMessage("Error Occured...");
		return new ResponseEntity<ErrorMsg>(error,HttpStatus.OK);

	}
	
	@ExceptionHandler(HibernateException.class)
	public ResponseEntity<ErrorMsg> handleHibernateException(Exception ex) {
		ex.printStackTrace();
		ErrorMsg error = new ErrorMsg();
		error.setStatus("ERROR");
		error.setErrorMessage("Error during db operation");
		return new ResponseEntity<ErrorMsg>(error,HttpStatus.OK);

	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
}
