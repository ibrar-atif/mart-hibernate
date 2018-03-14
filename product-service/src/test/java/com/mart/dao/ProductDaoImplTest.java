package com.mart.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.mart.entity.Product;

@RunWith( SpringJUnit4ClassRunner.class )
//@RunWith(MockitoJUnitRunner.class)
public class ProductDaoImplTest extends EntityDaoImplTest{

	@Autowired
	ProductDao productDao;
	
	public  ProductDaoImplTest() {
		System.out.println("TEst dao");
	}

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Product.xml"));
		return dataSet;
	}
	
	@Test
	
	public void saveEmployee() throws Exception{
		Product product = new Product();
		product.setName("ipad");
		product.setType("ELECTRONIC");
		productDao.addProduct(product);
		System.out.println(product.getId());
		
	}
}
