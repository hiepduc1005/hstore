package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.payload.CategoryDto;

import jakarta.persistence.EntityManager;
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
		return categoryDto;
	}

	@Transactional
	@Override
	public CategoryDto getCategoryById(Integer id) {
		CategoryDto categoryDto = entityManager.find(CategoryDto.class, id);
		return categoryDto;
	}

	@Transactional
	@Override
	public List<CategoryDto> getAllCategories() {
		TypedQuery<CategoryDto> typedQuery = entityManager.createQuery(
				"SELECT c FROM category c",CategoryDto.class);
		List<CategoryDto> categoryDtos = typedQuery.getResultList();
		return categoryDtos;
	}
	
	

}
