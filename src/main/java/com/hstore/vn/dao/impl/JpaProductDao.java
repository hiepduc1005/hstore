package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.exception.product.CreateProductFailuerException;
import com.hstore.vn.exception.product.DeleteProductFailuer;
import com.hstore.vn.exception.product.UpdateProductFailuer;
import com.hstore.vn.payload.ProductDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaProductDao implements ProductDao{
	
	@Autowired
	public EntityManager em;

	@Transactional
	@Override
	public List<ProductDto> getAllProducts() {
		TypedQuery<ProductDto> typedQuery = em.
				createQuery("SELECT p FROM product p",ProductDto.class);
			
		return typedQuery.getResultList();
	}

	@Transactional
	@Override
	public ProductDto getProductByGuid(String guid) {
		TypedQuery<ProductDto> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.guid = :guid",ProductDto.class);
		
		typedQuery.setParameter("guid",guid);
		ProductDto productDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		return productDto;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ProductDto> getProductsLikeName(String query) {
	    Query q = em.createNativeQuery(
				"SELECT * FROM product WHERE UPPER(product_name)"
				+ " LIKE UPPER(CONCAT('%',:query,'%'))",ProductDto.class);
		q.setParameter("query", query);
		List<ProductDto> productDtos =(List<ProductDto>)q.getResultList();
		return productDtos;
	}

	@Transactional
	@Override
	public List<ProductDto> getProductByCategoryName(String name) {
		TypedQuery<ProductDto> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.category.name = :name",ProductDto.class);
		
		typedQuery.setParameter("name",name);
		List<ProductDto> productDtos = typedQuery.getResultList();
		return productDtos;
	}

	@Transactional
	@Override
	public List<ProductDto> getProductByCategoryId(Integer id) {
		TypedQuery<ProductDto> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.category.id = :id",ProductDto.class);
		
		typedQuery.setParameter("id",id);
		List<ProductDto> productDtos = typedQuery.getResultList();
		return productDtos;
	}

	@Transactional
	@Override
	public List<ProductDto> getProductsByCategoryIdWithPaginationLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		TypedQuery<ProductDto> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.category.id = :categoryId",ProductDto.class);
		
		typedQuery.setParameter("categoryId",categoryId);
		typedQuery.setFirstResult((page-1)*paginationLimit);
		typedQuery.setMaxResults(paginationLimit);
		
		
		List<ProductDto> productDtos = typedQuery.getResultList();
		return productDtos;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ProductDto> getProductLikeNameWithPaginationLimit(String query, Integer page, Integer paginationLimit) {
		Query qr = em.
				createNativeQuery("SELECT * FROM product WHERE UPPER(product_name) "
						+ "LIKE UPPER(CONCAT('%',:query,'%')) LIMIT :offset, :limit",
						ProductDto.class);
		qr.setParameter("query", query);
		qr.setParameter("offset",(page-1)*paginationLimit);
		qr.setParameter("limit",paginationLimit);
		List<ProductDto> productDtos = (List<ProductDto>)qr.getResultList();
		return productDtos; 
	}

	@Transactional
	@Override
	public Integer getProductCountBySearch(String query) {
		Query qr= em.createNativeQuery("SELECT COUNT(*) FROM product WHERE UPPER(product_name)"
				+ " LIKE UPPER(CONCAT('%',:query,'%'))",ProductDto.class);
		
		qr.setParameter("query", query);
		Integer count = (Integer)qr.getSingleResult();
			
		return count;
	}

	@Transactional
	@Override
	public Integer getProductCountByCategoryId(Integer id) {
		TypedQuery<Integer> typedQuery = em.
				createQuery("SELECT COUNT(p) FROM product p WHERE p.category.id = :id",Integer.class);
		
		typedQuery.setParameter("id",id);
		
		Integer count = typedQuery.getResultList().stream().findFirst().orElse(null);
		
		return count;
	}

	@Transactional
	@Override
	public Integer getProductCountByCategoryName(String categoryName) {
		TypedQuery<Integer> typedQuery = em.
				createQuery("SELECT COUNT(p) FROM product p WHERE p.category.name = :categoryName",Integer.class);
		
		typedQuery.setParameter("categoryName",categoryName);
		Integer count = typedQuery.getResultList().stream().findFirst().orElse(null);

		return count;
	}

	@Transactional
	@Override
	public ProductDto getProductById(Integer id) {
	    ProductDto productDto =	em.find(ProductDto.class, id);
		return productDto;
	}

	@Transactional
	@Override
	@Modifying
	public void saveProduct(ProductDto product) {
		try {
			em.merge(product);
		}catch(IllegalArgumentException e) {
			throw new CreateProductFailuerException("Create product fail");
		}
		
	}

	@Transactional
	@Modifying
	@Override
	public void update(ProductDto productDto) {
		
		try {
			em.merge(productDto);
		}catch (IllegalArgumentException e) {
			throw new UpdateProductFailuer("Can not update product");
		}
		
	}

	@Transactional
	@Override
	@Modifying
	public void deleteProduct(String uuid) {
		TypedQuery<ProductDto> typedQuery =
				em.createQuery("DELETE FROM product p WHERE p.guid = :uuid", ProductDto.class);
		
		typedQuery.setParameter("uuid", uuid);
		
		 int rowsAffected = typedQuery.executeUpdate();
	        if (rowsAffected == 0) {
	            throw new DeleteProductFailuer("Failed to delete product with UUID: " + uuid);
	        }
		
	}
	
	
}
