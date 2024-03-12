package hgc.demojwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hgc.demojwt.entitys.Product;
import hgc.demojwt.requests.ProductRequest;
import hgc.demojwt.requests.TokenIdRequestId;
import hgc.demojwt.responses.TokenMessageResponse;
import hgc.demojwt.services.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ProductController {

	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@CrossOrigin(origins = { "http://localhost:4200" })
	@GetMapping
	public ResponseEntity<List<Product>> getAllCategories() {
		List<Product> product = productService.getAllProducts();
		return ResponseEntity.ok(product);
	}

	@PostMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
		Product product = productService.getProductById(productId);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(value = "create")
	public ResponseEntity<TokenMessageResponse> createProduct(@RequestBody ProductRequest productRequest) {
//		System.out.println(productRequest.toString());

		TokenMessageResponse tokenMessageResponse = productService.createProduct(productRequest);

		if (tokenMessageResponse != null) {
			return ResponseEntity.ok(tokenMessageResponse);
		} else {
			tokenMessageResponse.setMessage("Ha ocurrido un error, contacte al administrador");
			tokenMessageResponse.setDone(false);
			return ResponseEntity.ok(tokenMessageResponse);
		}
	}

	@PutMapping("/{productId}")
	public ResponseEntity<TokenMessageResponse> updateProduct(@PathVariable Integer productId,
			@RequestBody ProductRequest productRequest) {
		TokenMessageResponse tokenMessageResponse = productService.updateProduct(productId, productRequest);

		if (tokenMessageResponse != null) {
			return ResponseEntity.ok(tokenMessageResponse);
		} else {
			tokenMessageResponse.setMessage("Ha ocurrido un error, contacte al administrador");
			tokenMessageResponse.setDone(false);
			return ResponseEntity.ok(tokenMessageResponse);
		}
	}

	@DeleteMapping
	public ResponseEntity<TokenMessageResponse> deleteProduct(@RequestBody TokenIdRequestId tokenIdRequestId) {
		TokenMessageResponse tokenMessageResponse = productService.deleteProduct(tokenIdRequestId);

		if (tokenMessageResponse != null) {
			return ResponseEntity.ok(tokenMessageResponse);
		} else {
			tokenMessageResponse.setMessage("Ha ocurrido un error, contacte al administrador");
			tokenMessageResponse.setDone(false);
			return ResponseEntity.ok(tokenMessageResponse);
		}
	}
}
