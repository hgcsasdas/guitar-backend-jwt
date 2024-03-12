package hgc.demojwt.services;

import java.util.List;

import hgc.demojwt.entitys.Category;
import hgc.demojwt.requests.CategoryRequest;
import hgc.demojwt.requests.TokenIdRequestId;
import hgc.demojwt.responses.TokenMessageResponse;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Integer categoryId);

    TokenMessageResponse createCategory(CategoryRequest categoryRequest);

    TokenMessageResponse updateCategory(CategoryRequest categoryRequest);

    TokenMessageResponse deleteCategory(TokenIdRequestId tokenIdRequestId);
}
