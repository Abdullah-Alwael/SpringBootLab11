package com.spring.boot.springbootlab11.Controller;

import com.spring.boot.springbootlab11.Api.ApiResponse;
import com.spring.boot.springbootlab11.Model.Category;
import com.spring.boot.springbootlab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category added successfully"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAlCategories());
    }

    @PutMapping("/update/{category_id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer category_id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        categoryService.updateCategory(category_id, category);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category updated successfully"));
    }

    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer category_id) {
        categoryService.deleteCategory(category_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category deleted successfully"));
    }
}
