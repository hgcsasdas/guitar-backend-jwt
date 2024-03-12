package hgc.demojwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hgc.demojwt.entitys.Category;
import hgc.demojwt.requests.CategoryRequest;
import hgc.demojwt.requests.TokenIdRequestId;
import hgc.demojwt.responses.TokenMessageResponse;
import hgc.demojwt.services.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = { "http://localhost:4200" })
public class CategoryController {

	@Autowired
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@PostMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<TokenMessageResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
		System.out.println(categoryRequest.getCategory().getName());
		TokenMessageResponse tokenMessageResponse = categoryService.createCategory(categoryRequest);
		return ResponseEntity.ok(tokenMessageResponse);
	}

	@PutMapping
	public ResponseEntity<TokenMessageResponse> updateCategory(@RequestBody CategoryRequest categoryRequest) {

		TokenMessageResponse tokenMessageResponse = categoryService.updateCategory(categoryRequest);
		return ResponseEntity.ok(tokenMessageResponse);
	}

	@PostMapping("/delete")
	public ResponseEntity<TokenMessageResponse> deleteCategory(@RequestBody TokenIdRequestId tokenIdRequestId) {
		TokenMessageResponse tokenMessageResponse = categoryService.deleteCategory(tokenIdRequestId);
		return ResponseEntity.ok(tokenMessageResponse);
	}
}
