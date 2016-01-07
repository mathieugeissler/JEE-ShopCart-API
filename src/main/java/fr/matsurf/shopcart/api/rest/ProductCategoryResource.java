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

import fr.matsurf.shopcart.api.dao.product.ProductCategoryDao;
import fr.matsurf.shopcart.api.entity.ProductCategory;

@Component
@Path("/product/category")
public class ProductCategoryResource {

	@Autowired
	private ProductCategoryDao categoryDao;

	@Autowired
	private ObjectMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonProcessingException {
		List<ProductCategory> categories = this.categoryDao.findAll();
		return this.mapper.writeValueAsString(categories);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ProductCategory read(@PathParam("id") Long id) {
		ProductCategory category = this.categoryDao.find(id);

		if (category == null) {
			throw new WebApplicationException(404);
		}

		return category;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductCategory save(ProductCategory category) {
		return this.categoryDao.save(category);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		this.categoryDao.delete(id);
	}
}
