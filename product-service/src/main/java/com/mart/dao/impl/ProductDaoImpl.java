package com.mart.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.ProductMapper;
import com.mart.dao.ProductDao;
import com.mart.entity.Product;
import com.mart.entity.TestUser;

@Repository("productDao")
public class ProductDaoImpl   implements ProductDao{
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	SessionFactory sessionFactory;
	
	public ProductDaoImpl(){
		System.out.println("bean creation");
		
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Product addProduct(Product product) {
		this.getSession().save(product);
		String query = "insert into product values(:id,:name,:type)";
		Map<String,Object> param = new HashMap<>();
		param.put("id", product.getId()+1);
		param.put("type", "elec");
		param.put("name", "mob");
		jdbcTemplate.update(query, param);
		List<Product> list = jdbcTemplate.query("select * from product", new ProductMapper());
		return product;
	}

	public List<Product> findByType(String type) throws Exception {
		Session session = this.getSession();
		Criteria criteria =session.createCriteria(Product.class);
		criteria.add(Expression.eq("type", type));
		return criteria.list();
	}

	public Boolean deleteProductById(long id) throws Exception {
		Query query = this.getSession().createQuery("delete from Product where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		return true;
	}
	
	//@Transactional
	@Override
	public void addTestUser() throws Exception{
		TestUser user = new TestUser();
		user.setAge("11");
		user.setName("abc");
		this.getSession().save(user);
		throw new Exception();
		
	}
}
