import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.atLeastOnce;
import com.mart.dao.ProductDao;
import com.mart.dto.ProductDto;
import com.mart.entity.Product;
import com.mart.service.impl.ProductServiceImpl;

public class ProductServiceTest {

	@Mock
	ProductDao productDao;
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	 @Before
	    public void setUp(){
	        MockitoAnnotations.initMocks(this);

	    }
	 
		@Test
		public void addProductTest() throws HibernateException, Exception{
			
			Product product = new Product();
			product.setName("Laptop");
			product.setType("ELECTRONIC");
			
			Product product1 = new Product();
			product1.setName("Laptop");
			product1.setType("ELECTRONIC");
			product1.setId(anyLong());
			when(productDao.addProduct(product)).thenReturn(product1);
			ProductDto response = productServiceImpl.addProduct(new ProductDto());
			
			//verify(productDao, atLeastOnce()).addProduct(any(Product.class));
			Assert.assertEquals(response.getId(),anyLong());
			
			
		}
		
		@Test
		public void findBYTypeTest() throws HibernateException, Exception{
			

			when(productDao.findByType("ELECTRONIC")).thenReturn(getProducts());
			List<ProductDto> response = productServiceImpl.findByType("ELECTRONIC");
			Assert.assertNotNull(response);
			
		}
		

		@Test
		public void deleteProduct() throws Exception{
			when(productDao.deleteProductById(anyLong())).thenReturn(true);
			 Boolean response = productDao.deleteProductById(anyLong());
			Assert.assertEquals(response,true);
		}
		
		
		public List<Product> getProducts(){
			 List<Product> list = new ArrayList<Product>();
			 Product pro1 = new Product();
			 pro1.setId(1);
			 pro1.setName("Laptop");
			 pro1.setType("ELECTRONIC");
			 list.add(pro1);
			 
			 Product pro2 = new Product();
			 pro2.setId(1);
			 pro2.setName("Mobile");
			 pro2.setType("ELECTRONIC");
			 list.add(pro2);
			 
			return list;
		}
}
