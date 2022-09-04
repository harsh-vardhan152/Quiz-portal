package com.qizz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qizz.model.Quizz.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
