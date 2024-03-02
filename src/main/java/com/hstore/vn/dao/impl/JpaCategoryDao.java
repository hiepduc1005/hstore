package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.exception.category.NotFoundCategoryException;
import com.hstore.vn.payload.CategoryDto;

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
	public CategoryDto getCategoryByName(String name) {
		TypedQuery<CategoryDto> typedQuery = entityManager.createQuery(
				"SELECT c FROM category c WHERE c.name = :name",CategoryDto.class);
		
		typedQuery.setParameter("name", name);
		CategoryDto categoryDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		if(categoryDto == null) {
			throw new NotFoundCategoryException("Not found category with name = " + name);
		}
		return categoryDto;
	}

	@Transactional
	@Override
	public CategoryDto getCategoryById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("Not found cagegory");
		}
		CategoryDto categoryDto = entityManager.find(CategoryDto.class, id);
		
		if(categoryDto == null) {
			throw new NotFoundCategoryException("Can not found category with id : " + id);
		}
		
		return categoryDto;
	}

	@Transactional
	@Override
	public List<CategoryDto> getAllCategories() {
		TypedQuery<CategoryDto> typedQuery = entityManager.createQuery(
				"SELECT c FROM category c",CategoryDto.class);
		List<CategoryDto> categoryDtos = typedQuery.getResultList();
		if(categoryDtos.isEmpty()) {
			throw new NotFoundCategoryException("Not found any category");
		}
		return categoryDtos;
	}

	@Transactional
	@Override
	public void createCategory(CategoryDto categoryDto) {
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
