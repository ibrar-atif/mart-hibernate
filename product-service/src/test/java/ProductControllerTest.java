import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import com.mart.controller.ProductController;
import com.mart.dao.ProductDao;
import com.mart.dto.ProductDto;
import com.mart.service.ProductService;

public class ProductControllerTest {

	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
	@Mock
	ProductDao productDao;
	
	List<ProductDto> productList;
	
	 @Before
	    public void setUp(){
	        MockitoAnnotations.initMocks(this);
	        productList = getProducts();
	    }
	
	@Test
	public void addProductTest() throws HibernateException, Exception{
		
		ProductDto productDto = new ProductDto();
		productDto.setName("Laptop");
		productDto.setType("ELECTRONIC");
		when(productService.addProduct(productDto)).thenReturn(productDto);
		ResponseEntity<ProductDto> response = productController.addProduct(productDto);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
		
	}
	
	@Test
	public void findBYTypeTest() throws HibernateException, Exception{
		

		when(productService.findByType("ELECTRONIC")).thenReturn(productList);
		 ResponseEntity<List<ProductDto>> response = productController.findByType("ELECTRONIC");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
		
	}
	
	@Test
	public void findBYTypeNullTest() throws HibernateException, Exception{
		
		when(productService.findByType("ELECTRONIC")).thenReturn(null);
		 ResponseEntity<List<ProductDto>> response = productController.findByType("ELECTRONIC");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		Assert.assertNull(response.getBody());
		
	}
	
	@Test
	public void deleteProduct() throws Exception{
		when(productService.deleteProductById(anyLong())).thenReturn(true);
		ResponseEntity<Boolean> response = productController.deleteProductById(anyLong());
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		Assert.assertEquals(response.getBody(),true);
	}
	
	
	public List<ProductDto> getProducts(){
		 List<ProductDto> list = new ArrayList<ProductDto>();
		 ProductDto pro1 = new ProductDto();
		 pro1.setId(1);
		 pro1.setName("Laptop");
		 pro1.setType("ELECTRONIC");
		 list.add(pro1);
		 
		 ProductDto pro2 = new ProductDto();
		 pro2.setId(1);
		 pro2.setName("Mobile");
		 pro2.setType("ELECTRONIC");
		 list.add(pro2);
		 
		return list;
	}
	
	
}
