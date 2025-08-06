package com.spring.boot.springbootlab11.Repository;

import com.spring.boot.springbootlab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findCategoryByCategoryId(Integer categoryId);
}
