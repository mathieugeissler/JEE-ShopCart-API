package fr.matsurf.shopcart.api.dao.product;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import fr.matsurf.shopcart.api.dao.JpaDao;
import fr.matsurf.shopcart.api.entity.ProductCategory;

public class JpaProductCategory extends JpaDao<ProductCategory, Long> implements ProductCategoryDao {

	public JpaProductCategory() {
		super(ProductCategory.class);
	}

	@Override
	public ProductCategory findByName(String name) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ProductCategory> query = builder.createQuery(this.entityClass);

		Root<ProductCategory> root = query.from(this.entityClass);
		Path<String> namePath = root.get("name");

		query.where(builder.equal(namePath, name));

		TypedQuery<ProductCategory> typedQuery = this.getEntityManager().createQuery(query);
		List<ProductCategory> categories = typedQuery.getResultList();

		if (categories.isEmpty()) {
			return null;
		}

		return categories.iterator().next();
	}


}
