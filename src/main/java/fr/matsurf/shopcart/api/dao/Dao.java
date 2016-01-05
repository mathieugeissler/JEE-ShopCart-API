package fr.matsurf.shopcart.api.dao;

import java.util.List;

import fr.matsurf.shopcart.api.entity.Entity;

public interface Dao<E extends Entity, I> {

	//find all entry of entity
	List<E> findAll();
	
	//find entry by id
	E find(I id);
	
	//save entity
	E save(E entity);
	
	//delete entity by id
	void delete(I id);
	
}
