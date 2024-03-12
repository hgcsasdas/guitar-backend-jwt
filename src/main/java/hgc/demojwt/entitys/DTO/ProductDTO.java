package hgc.demojwt.entitys.DTO;

import hgc.demojwt.entitys.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	private String name;
	private double price;
	private String description;
	private int quantity;
	private Category category;
	private String image;

	public ProductDTO(String name, double price, String description, int quantity, Category category,
			String image) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductDTO [name=" + name + ", price=" + price + ", description=" + description + ", quantity="
				+ quantity + ", category=" + category.toString() + ", image=" + image + "]";
	}

}
