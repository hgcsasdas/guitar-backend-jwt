package hgc.demojwt.services;

import java.util.List;

import hgc.demojwt.entitys.Product;
import hgc.demojwt.requests.ProductRequest;
import hgc.demojwt.requests.TokenIdRequestId;
import hgc.demojwt.responses.TokenMessageResponse;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductById(Integer productId);

	TokenMessageResponse createProduct(ProductRequest productRequest);

	TokenMessageResponse updateProduct(Integer productId, ProductRequest productRequest);

	TokenMessageResponse deleteProduct(TokenIdRequestId tokenIdRequestId);

	public void deleteProductsByCategoryId(Integer categoryId);
}
