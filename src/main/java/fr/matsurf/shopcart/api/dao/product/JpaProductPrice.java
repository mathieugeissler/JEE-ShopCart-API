package fr.matsurf.shopcart.api.dao.product;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import fr.matsurf.shopcart.api.dao.JpaDao;
import fr.matsurf.shopcart.api.entity.Product;
import fr.matsurf.shopcart.api.entity.ProductPrice;
import fr.matsurf.shopcart.api.entity.Store;

public class JpaProductPrice extends JpaDao<ProductPrice, Long> implements ProductPriceDao {

	public JpaProductPrice() {
		super(ProductPrice.class);
	}

	@Override
	public List<ProductPrice> listByStore(Long id) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ProductPrice> query = builder.createQuery(ProductPrice.class);

		Root<ProductPrice> root = query.from(ProductPrice.class);
		Path<Store> pathStore = root.get("store");

		query.where(builder.equal(pathStore.get("id"), id));

		TypedQuery<ProductPrice> typedQuery = this.getEntityManager().createQuery(query);

		List<ProductPrice> stores = typedQuery.getResultList();

		if (stores.isEmpty()) {
			return null;
		}

		return stores;
	}

	@Override
	public List<ProductPrice> listByProduct(Long id) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ProductPrice> query = builder.createQuery(ProductPrice.class);

		Root<ProductPrice> root = query.from(ProductPrice.class);
		Path<Product> pathProduct = root.get("product");

		query.where(builder.equal(pathProduct.get("id"), id));

		TypedQuery<ProductPrice> typedQuery = this.getEntityManager().createQuery(query);

		List<ProductPrice> products = typedQuery.getResultList();

		if (products.isEmpty()) {
			return null;
		}

		return products;
	}

	@Override
	public ProductPrice findbyStore(Long productId, Long storeId) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ProductPrice> query = builder.createQuery(ProductPrice.class);

		Root<ProductPrice> root = query.from(ProductPrice.class);
		Path<Store> pathStore = root.get("store");
		Path<Product> pathProduct = root.get("product");

		query.where(builder.equal(pathStore.get("id"), storeId), builder.equal(pathProduct.get("id"), productId));

		TypedQuery<ProductPrice> typedQuery = this.getEntityManager().createQuery(query);
		
		List<ProductPrice> product = typedQuery.getResultList();
		
		if(product.isEmpty()) {
			return null;
		}
		
		return product.iterator().next();
	}

}
