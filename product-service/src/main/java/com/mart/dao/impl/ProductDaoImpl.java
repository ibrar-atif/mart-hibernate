package com.mart.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mart.dao.ProductDao;
import com.mart.entity.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{

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
}
