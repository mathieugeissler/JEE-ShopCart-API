package fr.matsurf.shopcart.api.dao.product;

import fr.matsurf.shopcart.api.dao.Dao;
import fr.matsurf.shopcart.api.entity.ProductCategory;

public interface ProductCategoryDao extends Dao<ProductCategory, Long> {

	ProductCategory findByName(String name);
	
}
