package fr.matsurf.shopcart.api.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.matsurf.shopcart.api.dao.product.ProductPriceDao;
import fr.matsurf.shopcart.api.dao.store.StoreDao;
import fr.matsurf.shopcart.api.entity.ProductPrice;
import fr.matsurf.shopcart.api.entity.Store;

@Component
@Path("/store")
public class StoreResource {

	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private ProductPriceDao priceDao;

	@Autowired
	private ObjectMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonProcessingException {
		List<Store> stores = this.storeDao.findAll();

		return mapper.writeValueAsString(stores);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Store read(@PathParam("id") Long id) {
		Store store = this.storeDao.find(id);

		if (store == null) {
			throw new WebApplicationException(404);
		}

		return store;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Store save(Store store) {
		return this.storeDao.save(store);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		this.storeDao.delete(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/product")
	public String listProducts(@PathParam("id") Long id) throws JsonProcessingException {
		List<ProductPrice> products = this.priceDao.listByStore(id);
		
		return mapper.writeValueAsString(products);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/product/{prodId}")
	public ProductPrice getProduct(@PathParam("id") Long storeId, @PathParam("prodId") Long prodId) {
		ProductPrice product = this.priceDao.findbyStore(prodId, storeId);
		
		if(product == null) {
			throw new WebApplicationException(404);
		}
		
		return product;
	}

}
