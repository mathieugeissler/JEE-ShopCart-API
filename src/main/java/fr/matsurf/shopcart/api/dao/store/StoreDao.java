package fr.matsurf.shopcart.api.dao.store;


import fr.matsurf.shopcart.api.dao.Dao;
import fr.matsurf.shopcart.api.entity.Store;

public interface StoreDao extends Dao<Store, Long> {
	
	Store findByName(String name);

}
