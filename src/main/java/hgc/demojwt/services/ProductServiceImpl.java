package hgc.demojwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import hgc.demojwt.Jwt.JwtService;
import hgc.demojwt.entitys.Product;
import hgc.demojwt.repository.ProductRepository;
import hgc.demojwt.requests.ProductRequest;
import hgc.demojwt.requests.TokenIdRequestId;
import hgc.demojwt.responses.TokenMessageResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private final ProductRepository productRepository;
	private final JwtService jwtService;

	public ProductServiceImpl(ProductRepository productRepository, JwtService jwtService) {
		this.productRepository = productRepository;
		this.jwtService = jwtService;

	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Integer productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public TokenMessageResponse deleteProduct(TokenIdRequestId tokenIdRequestId) {
		TokenMessageResponse tokenMessageResponse = new TokenMessageResponse();
		String token = jwtService.refreshToken(tokenIdRequestId.getToken());
		tokenMessageResponse.setToken(token);
		if (productRepository.existsById(tokenIdRequestId.getId())) {
			try {
				productRepository.deleteById(tokenIdRequestId.getId());

				tokenMessageResponse.setMessage("Eliminado con éxito");
				tokenMessageResponse.setDone(true);
				return tokenMessageResponse;
			} catch (Exception e) {
				tokenMessageResponse.setMessage("Ha ocurrido un error");
				return tokenMessageResponse;
			}
		} else {
			tokenMessageResponse.setMessage("No se ha encontrado el producto");
		}
		return tokenMessageResponse;

	}

	@Override
	public TokenMessageResponse createProduct(ProductRequest productRequest) {
		TokenMessageResponse tokenMessageResponse = new TokenMessageResponse();
		String token = jwtService.refreshToken(productRequest.getToken());
		tokenMessageResponse.setToken(token);

		System.out.println(productRequest.getProductDTO());
		try {
			Product productToSave = new Product(productRequest.getProductDTO().getName(),
					productRequest.getProductDTO().getPrice(), productRequest.getProductDTO().getImage(),
					productRequest.getProductDTO().getDescription(),
					productRequest.getProductDTO().getQuantity(),
					productRequest.getProductDTO().getCategory());

			productRepository.save(productToSave);

			tokenMessageResponse.setMessage("Creado con éxito");
			tokenMessageResponse.setDone(true);

			return tokenMessageResponse;
		} catch (Exception e) {
			System.out.println("error:");
			System.out.println(e);
			tokenMessageResponse.setMessage("Ha ocurrido un error");
			return tokenMessageResponse;
		}
	}

	@Override
	public TokenMessageResponse updateProduct(Integer productId, ProductRequest productRequest) {
		TokenMessageResponse tokenMessageResponse = new TokenMessageResponse();
		String token = jwtService.refreshToken(productRequest.getToken());
		Product product = productRepository.findById(productId).orElse(null);
		tokenMessageResponse.setToken(token);

		if (product != null) {
			try {
				product.updateFromDTO(productRequest.getProductDTO());
				productRepository.save(product);

				tokenMessageResponse.setMessage("Actualizado con éxito");
				tokenMessageResponse.setDone(true);

			} catch (DataAccessException e) {
				System.err.println("Error al actualizar el producto: " + e.getMessage());
				tokenMessageResponse.setMessage("Ha ocurrido un error al actualizar el producto");

			} catch (Exception e) {
				System.err.println("Excepción no manejada: " + e.getMessage());
				tokenMessageResponse.setMessage("Ha ocurrido un error inesperado");
			}

		} else {
			tokenMessageResponse.setMessage("No se ha encontrado el producto");
		}
		return tokenMessageResponse;

	}
	
    public void deleteProductsByCategoryId(Integer categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        for (Product product : products) {
            productRepository.deleteById(product.getId());
        }
    }

}
