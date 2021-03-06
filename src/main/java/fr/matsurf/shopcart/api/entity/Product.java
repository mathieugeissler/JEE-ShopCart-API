package fr.matsurf.shopcart.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@javax.persistence.Entity
@Table(name = "Product")
public class Product implements Entity {

	private static final long serialVersionUID = 1665869316413297956L;

	private Long id;
	private String name;
	private ProductCategory category;
	private Set<ProductPrice> productPrices = new HashSet<ProductPrice>();

	public Product() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@OneToMany(mappedBy = "product")
	public Set<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(Set<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

}
