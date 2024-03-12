package hgc.demojwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hgc.demojwt.Jwt.JwtService;
import hgc.demojwt.entitys.Category;
import hgc.demojwt.repository.CategoryRepository;
import hgc.demojwt.repository.ProductRepository;
import hgc.demojwt.requests.CategoryRequest;
import hgc.demojwt.requests.TokenIdRequestId;
import hgc.demojwt.responses.TokenMessageResponse;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private final ProductService productService;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	private final JwtService jwtService;

	public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService,
			ProductRepository productRepository, JwtService jwtService) {
		this.categoryRepository = categoryRepository;
		this.jwtService = jwtService;
		this.productRepository = productRepository;
		this.productService = productService;

	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}

	@Override
	public TokenMessageResponse createCategory(CategoryRequest categoryRequest) {
		TokenMessageResponse tokenMessageResponse = new TokenMessageResponse();
		String token = jwtService.refreshToken(categoryRequest.getToken());
		tokenMessageResponse.setToken(token);
		try {
			categoryRepository.save(categoryRequest.getCategory());

			tokenMessageResponse.setMessage("Creado con éxito");
			tokenMessageResponse.setDone(true);

			return tokenMessageResponse;

		} catch (Exception e) {

		}

		return tokenMessageResponse;
	}

	@Override
	public TokenMessageResponse updateCategory(CategoryRequest categoryRequest) {
		TokenMessageResponse tokenMessageResponse = new TokenMessageResponse();
		Category existingCategory = categoryRepository.findById(categoryRequest.getCategory().getId()).orElse(null);

		String token = jwtService.refreshToken(categoryRequest.getToken());
		tokenMessageResponse.setToken(token);

		if (existingCategory != null) {
			existingCategory.setName(categoryRequest.getCategory().getName());
			try {
				categoryRepository.save(existingCategory);

				tokenMessageResponse.setMessage("Actualizado con éxito");
				tokenMessageResponse.setDone(true);

				return tokenMessageResponse;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				tokenMessageResponse.setMessage("Ha ocurrido un error");
				return tokenMessageResponse;
			}
		} else {
			tokenMessageResponse.setMessage("No se ha encontrado la categoría");
			return tokenMessageResponse;
		}

	}

	@Override
	public TokenMessageResponse deleteCategory(TokenIdRequestId tokenIdRequestId) {
		TokenMessageResponse tokenMessageResponse = new TokenMessageResponse();

		String token = jwtService.refreshToken(tokenIdRequestId.getToken());
		tokenMessageResponse.setToken(token);

		productService.deleteProductsByCategoryId(tokenIdRequestId.getId());
		
		if (categoryRepository.existsById(tokenIdRequestId.getId())) {
			categoryRepository.deleteById(tokenIdRequestId.getId());
			tokenMessageResponse.setMessage("Eliminado con éxito");
			tokenMessageResponse.setDone(true);

			return tokenMessageResponse;
		} else {
			tokenMessageResponse.setMessage("Ha ocurrido un error");
			return tokenMessageResponse;
		}
	}
}
