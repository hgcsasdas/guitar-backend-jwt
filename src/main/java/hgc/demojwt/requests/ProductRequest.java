package hgc.demojwt.requests;


import hgc.demojwt.entitys.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
	private String token;
	private ProductDTO productDTO;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}



}
