package com.qizz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qizz.model.Quizz.Category;
import com.qizz.model.Quizz.Quiz;

public interface QuizzRepository extends JpaRepository<Quiz, Long>{
	
	public List<Quiz> findBycategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findBycategoryAndActive(Category c,Boolean b);

}
