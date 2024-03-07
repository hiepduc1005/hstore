package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.CategoryDao;
import com.hstore.vn.dao.ProductDao;
import com.hstore.vn.entity.Product;
import com.hstore.vn.exception.product.CreateProductFailuerException;
import com.hstore.vn.exception.product.NotFoundProductException;
import com.hstore.vn.exception.product.UpdateProductFailuer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaProductDao implements ProductDao{
	
	@Autowired
	public EntityManager em;
	
	@Autowired
	public CategoryDao categoryDao;

	@Transactional
	@Override
	public List<Product> getAllProducts() {
		TypedQuery<Product> typedQuery = em.
				createQuery("SELECT p FROM product p",Product.class);
		
		List<Product> productDtos = typedQuery.getResultList();
		
		if(productDtos.isEmpty()) {
			throw new NotFoundProductException("Not found any product");
		}
		
		return productDtos;
	}

	@Transactional
	@Override
	public Product getProductByGuid(String guid) {
		if(guid == null) {
			throw new IllegalArgumentException("Wrong input must be type String");
		}
		TypedQuery<Product> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.guid = :guid",Product.class);
		
		typedQuery.setParameter("guid",guid);
		Product productDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		return productDto;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Product> getProductsLikeName(String query) {
	    Query q = em.createNativeQuery(
				"SELECT * FROM product WHERE UPPER(product_name)"
				+ " LIKE UPPER(CONCAT('%',:query,'%'))",Product.class);
		q.setParameter("query", query);
		List<Product> productDtos =(List<Product>)q.getResultList();
		return productDtos;
	}

	@Transactional
	@Override
	public List<Product> getProductByCategoryName(String name) {
		TypedQuery<Product> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.category.name = :name",Product.class);
		
		typedQuery.setParameter("name",name);
		List<Product> productDtos = typedQuery.getResultList();
		return productDtos;
	}

	@Transactional
	@Override
	public List<Product> getProductByCategoryId(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Wrong input must be type int");
		}
		
		categoryDao.getCategoryById(id); // handle exception khi không tìm thấy category by id
		
		TypedQuery<Product> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.category.id = :id",Product.class);
		
		typedQuery.setParameter("id",id);
		List<Product> productDtos = typedQuery.getResultList();
		return productDtos;
	}

	@Transactional
	@Override
	public List<Product> getProductsByCategoryIdWithPaginationLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		
		if (categoryId == null || page == null || paginationLimit == null || page < 1 || paginationLimit < 1) {
		        throw new IllegalArgumentException("Invalid input parameters");
		    }
		
		if(getProductByCategoryId(categoryId) == null) {
			throw new NotFoundProductException("Not found any product with category id : " + categoryId);
		}
		
		TypedQuery<Product> typedQuery = em.
				createQuery("SELECT p FROM product p WHERE p.category.id = :categoryId",Product.class);
					
		typedQuery.setParameter("categoryId",categoryId);
		typedQuery.setFirstResult((page-1)*paginationLimit);
		typedQuery.setMaxResults(paginationLimit);	
			
		List<Product> productDtos = typedQuery.getResultList();
		if(productDtos == null || productDtos.isEmpty()) {
			throw new NotFoundProductException("Not found any product with category id : " + categoryId + " in page : " + page);
		}
		return productDtos;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Product> getProductLikeNameWithPaginationLimit(String query, Integer page, Integer paginationLimit) {
		
		if ( page == null || paginationLimit == null || page < 1 || paginationLimit < 1) {
	        throw new IllegalArgumentException("Invalid input parameters");
	    }
		
		Query qr = em.
				createNativeQuery("SELECT * FROM product WHERE UPPER(product_name) "
						+ "LIKE UPPER(CONCAT('%',:query,'%')) LIMIT :offset , :limit",
						Product.class);
		qr.setParameter("query", query);
		qr.setParameter("offset",(page-1)*paginationLimit);
		qr.setParameter("limit",paginationLimit);
		
		List<Product> productDtos = (List<Product>)qr.getResultList();
		
		if(productDtos == null || productDtos.isEmpty()) {
			throw new NotFoundProductException("Not found any product with name like : " + query + " in page : " + page);
		}
		
		return productDtos; 
	}

	@Transactional
	@Override
	public Integer getProductCountBySearch(String query) {
		Query qr= em.createNativeQuery("SELECT COUNT(*) FROM product WHERE UPPER(product_name)"
				+ " LIKE UPPER(CONCAT('%',:query,'%'))",Product.class);
		
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
	public Product getProductById(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Wrong input must be type int ");
		}
	    Product productDto =	em.find(Product.class, id);
	    
	    if(productDto == null) {
	    	throw new NotFoundProductException("Can not found product with id : " + id);
	    }
	    
		return productDto;
	}

	@Transactional
	@Override
	@Modifying
	public Product saveProduct(Product product) {
		
		try {
		return em.merge(product);
		}catch(IllegalArgumentException e) {
			throw new CreateProductFailuerException("Create product fail");
		}
		
	}

	@Transactional
	@Modifying
	@Override
	public void update(Product productDto) {
		
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
		if(uuid == null || uuid.isEmpty()) {
			throw new IllegalArgumentException("Product UUID must be type string");
		}
		
		Product product = getProductByGuid(uuid);
		
		if(product == null) {
			throw new NotFoundProductException("Can not found product with uuid : " + uuid);
		}
		em.remove(product);
		
//		Query query = em.createNativeQuery("DELETE FROM product p WHERE p.guid = :uuid");
//	    query.setParameter("uuid", uuid);
//	    		
//		 int rowsAffected = query.executeUpdate();
//	        if (rowsAffected == 0) {
//	            throw new DeleteProductFailuer("Failed to delete product with UUID: " + uuid);
//	        }
//		
	}

	@Transactional
	@Override
	public Product getProductByName(String name) {
		TypedQuery<Product> typedQuery =
				em.createQuery("SELECT p FROM product p WHERE p.name = :name", Product.class);
		typedQuery.setParameter("name", name);
		
		Product productDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		if(productDto == null) {
			throw new NotFoundProductException("Can not found product with name : " + name);
		}
		return productDto;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Product> getAllProductWithPaginationLimit(Integer page, Integer paginationLimit) {
		if ( page == null || paginationLimit == null || page < 1 || paginationLimit < 1) {
	        throw new IllegalArgumentException("Invalid input parameters");
	    }
	
		Query qr = em.
				createNativeQuery("SELECT * FROM product LIMIT :offset , :limit",
						Product.class);
		
		qr.setParameter("offset",(page-1)*paginationLimit);
		qr.setParameter("limit",paginationLimit);
		
		List<Product> productDtos = (List<Product>)qr.getResultList();
		return productDtos; 
	}
	
	
}
