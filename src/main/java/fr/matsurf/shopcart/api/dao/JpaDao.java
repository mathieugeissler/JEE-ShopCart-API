package fr.matsurf.shopcart.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

import fr.matsurf.shopcart.api.entity.Entity;

public class JpaDao<E extends Entity, I> implements Dao<E, I> {

	private EntityManager entityManager;
	protected Class<E> entityClass;

	public JpaDao(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<E> query = builder.createQuery(this.entityClass);
		
		query.from(this.entityClass);
		
		TypedQuery<E> typedQuery = this.getEntityManager().createQuery(query);
		
		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public E find(I id) {
		return this.getEntityManager().find(this.entityClass, id);
	}

	@Override
	@Transactional
	public E save(E entity) {
		return this.getEntityManager().merge(entity);
	}

	@Override
	@Transactional
	public void delete(I id) {
		if(id == null) {
			return;
		}
		
		E entity = this.find(id);
		if(entity == null) {
			return;
		}
		
		this.getEntityManager().remove(entity);
	}

}
