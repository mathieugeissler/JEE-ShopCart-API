package fr.matsurf.shopcart.api.dao.product;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import fr.matsurf.shopcart.api.dao.JpaDao;
import fr.matsurf.shopcart.api.entity.Product;

public class JpaProduct extends JpaDao<Product, Long> implements ProductDao {

	public JpaProduct() {
		super(Product.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findByName(String name) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(this.entityClass);

		Root<Product> root = query.from(this.entityClass);
		Path<String> namePath = root.get("name");

		query.where(builder.equal(namePath, name));

		TypedQuery<Product> typedQuery = this.getEntityManager().createQuery(query);
		List<Product> products = typedQuery.getResultList();

		if (products.isEmpty()) {
			return null;
		}

		return products.iterator().next();
	}

}
