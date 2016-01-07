package fr.matsurf.shopcart.api.dao.store;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import fr.matsurf.shopcart.api.dao.JpaDao;
import fr.matsurf.shopcart.api.entity.Store;

public class JpaStore extends JpaDao<Store, Long> implements StoreDao {

	public JpaStore() {
		super(Store.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Store findByName(String name) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Store> query = builder.createQuery(this.entityClass);

		Root<Store> root = query.from(this.entityClass);
		Path<String> namePath = root.get("name");

		query.where(builder.equal(namePath, name));

		TypedQuery<Store> typedQuery = this.getEntityManager().createQuery(query);
		List<Store> products = typedQuery.getResultList();

		if (products.isEmpty()) {
			return null;
		}

		return products.iterator().next();
	}

}
