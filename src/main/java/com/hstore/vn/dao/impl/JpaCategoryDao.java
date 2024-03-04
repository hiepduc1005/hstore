package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.entity.Category;
import com.hstore.vn.exception.category.NotFoundCategoryException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaCategoryDao implements CategoryDao{
	
	@Autowired
	public EntityManager entityManager;

	@Transactional
	@Override
	public Category getCategoryByName(String name) {
		TypedQuery<Category> typedQuery = entityManager.createQuery(
				"SELECT c FROM category c WHERE c.name = :name",Category.class);
		
		typedQuery.setParameter("name", name);
		Category categoryDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		if(categoryDto == null) {
			throw new NotFoundCategoryException("Not found category with name = " + name);
		}
		return categoryDto;
	}

	@Transactional
	@Override
	public Category getCategoryById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Not found cagegory");
		}
		Category categoryDto = entityManager.find(Category.class, id);
		
		if(categoryDto == null) {
			throw new NotFoundCategoryException("Can not found category with id : " + id);
		}
		
		return categoryDto;
	}

	@Transactional
	@Override
	public List<Category> getAllCategories() {
		TypedQuery<Category> typedQuery = entityManager.createQuery(
				"SELECT c FROM category c",Category.class);
		List<Category> categoryDtos = typedQuery.getResultList();
		if(categoryDtos.isEmpty()) {
			throw new NotFoundCategoryException("Not found any category");
		}
		return categoryDtos;
	}

	@Transactional
	@Override
	public void createCategory(Category categoryDto) {
		try {
			entityManager.merge(categoryDto);
		}catch (Exception e) {
			throw new IllegalArgumentException("Failed to create category");
		}
		
	}

	@Transactional
	@Override
	public void deleteCategory(Integer id) {
		Query query = entityManager.createNativeQuery("DELETE FROM category c WHERE c.id = :id");
		
		query.setParameter("id", id);
		
		 int rowsAffected = query.executeUpdate();
	        if (rowsAffected == 0) {
	        	throw new NotFoundCategoryException("Can not found category with id : " + id);
	        }
	}
	

}
