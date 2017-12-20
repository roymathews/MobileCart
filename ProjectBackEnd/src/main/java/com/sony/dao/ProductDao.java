package com.sony.dao;



import java.util.List;

import com.sony.model.Product;


public interface ProductDao {
	List<Product> list();
	public List<Product> list2(int id);
	public Product  findById(int id);
	public List<Product>  findByCatId(int id,int sort);
	void saveProduct(Product p);
	void delete(Product p);
	void updateproduct(Product s);
	
}
