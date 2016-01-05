package fr.matsurf.shopcart.api.dao.product;

import fr.matsurf.shopcart.api.dao.Dao;
import fr.matsurf.shopcart.api.entity.Product;

public interface ProductDao extends Dao<Product, Long> {
	
	Product findByName(String name);

}
