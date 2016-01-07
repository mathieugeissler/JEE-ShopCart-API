package fr.matsurf.shopcart.api.dao.product;


import java.util.List;

import fr.matsurf.shopcart.api.dao.Dao;
import fr.matsurf.shopcart.api.entity.ProductPrice;

public interface ProductPriceDao extends Dao<ProductPrice, Long> {
	
	List<ProductPrice> listByStore(Long storeId);
	List<ProductPrice> listByProduct(Long productId);
	
	ProductPrice findbyStore(Long productId, Long storeId);
	
}
