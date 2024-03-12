package hgc.demojwt.entitys;

import hgc.demojwt.entitys.DTO.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private double price;
	
	@Lob
	@Column(name = "data", length = Integer.MAX_VALUE)
	private String image;
	
	private String description;
	private int quantity;

	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Product() {
		super();
	}

	public Product(Integer id, String name, double price, String image, String description, int quantity,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
	}

	public Product(String name, double price, String image, String description, int quantity, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void updateFromDTO(ProductDTO productDTO) {
        // Actualiza solo los campos necesarios a partir del DTO
        this.setName(productDTO.getName());
        this.setPrice(productDTO.getPrice());
        this.setDescription(productDTO.getDescription());
        this.setQuantity(productDTO.getQuantity());
        this.setCategory(productDTO.getCategory());
        this.setImage(productDTO.getImage());
    }

}
