package com.spring.boot.springbootlab11.Service;

import com.spring.boot.springbootlab11.Api.ApiException;
import com.spring.boot.springbootlab11.Model.Category;
import com.spring.boot.springbootlab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getAlCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Integer category_id, Category category){
        Category oldCategory = categoryRepository.findCategoryByCategoryId(category_id);

        if (oldCategory == null){
            throw new ApiException("Error category not found");
        }

        oldCategory.setName(category.getName());

        categoryRepository.save(oldCategory);

    }

    public void deleteCategory(Integer category_id){
        Category oldCategory = categoryRepository.findCategoryByCategoryId(category_id);

        if (oldCategory == null){
            throw new ApiException("Error category not found");
        }

        categoryRepository.delete(oldCategory);

    }

    // Extra:
    // get all categories containing a word

    public List<Category> filterCategoriesByName(String name){
        return categoryRepository.filterCategoriesByNameLike(name);
    }

}
