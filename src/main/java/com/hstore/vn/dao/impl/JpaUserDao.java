package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hstore.vn.dao.UserDao;
import com.hstore.vn.exception.user.UserNotFoundException;
import com.hstore.vn.payload.UserDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaUserDao implements UserDao {
	
	@Autowired
	public EntityManager em;

	@Transactional
	@Override
	public UserDto getUserById(Integer id) {
		
	UserDto userDto = em.find(UserDto.class, id);
		
		return userDto;
	}

	@Transactional
	@Override
	public boolean saveUser(UserDto user) {
		em.merge(user);
		return true;
	}

	@Transactional
	@Override
	@Modifying
	public void updateUser(UserDto user) {
		// TODO Auto-generated method stub
		em.merge(user);
	}

	@Transactional
	@Override
	public UserDto getUserByEmail(String email) {
	    TypedQuery<UserDto> typedQuery = em.createQuery("SELECT u FROM user u WHERE u.email = :email",UserDto.class);
		
	    typedQuery.setParameter("email",email); 
	    UserDto userDto = typedQuery.getResultList().stream().findFirst().orElse(null);
	    if(userDto != null) {
	    	userDto.enabled = true;
	    }
	    
		return userDto;
	}
	

	@Transactional
	@Override
	public List<UserDto> getAllUsers() {
		TypedQuery<UserDto> typedQuery = em.createQuery("SELECT u FROM user u",UserDto.class);
		List<UserDto> userDtos = typedQuery.getResultList();
		
		if(userDtos == null) {
	    	throw new UserNotFoundException("Can not found any user");
	    }
		
		return userDtos;
	}

	@Transactional
	@Override
	public UserDto getUserByPartnerCode(String partnerCode) {
		TypedQuery<UserDto> typedQuery = em.createQuery("SELECT u FROM user u WHERE u.partnerCode = :partnerCode",UserDto.class);
		typedQuery.setParameter("partnerCode",partnerCode);
		UserDto userDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		return userDto;
	}

	@Transactional
	@Override
	public List<UserDto> getRefferedByUserId(Integer id) {
		TypedQuery<UserDto> typedQuery = em.createQuery("SELECT u FROM user u WHERE u.reffererUser.id = :id",UserDto.class);
		typedQuery.setParameter("id", id);
		List<UserDto> userDtos = typedQuery.getResultList();
		return userDtos;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public void deleteUser(Integer id) {
		if(id == null ) {
			throw new IllegalArgumentException("User Id must not be null");
		}
		
		if(getUserById(id) == null) {
			throw new UserNotFoundException("Can not found user with id : " + id);
		}
		
		Query cartId = em.createNativeQuery("SELECT cart_id FROM user u WHERE u.id = :id", Integer.class);
		cartId.setParameter("id",id);	
		Integer cartIdWithUser = (Integer) cartId.getResultList().stream().findFirst().orElse(null); 
		
		Query queryDeleteFKRole = em.createNativeQuery("DELETE FROM users_roles ur WHERE ur.user_id = :id");
		queryDeleteFKRole.setParameter("id", id);
		queryDeleteFKRole.executeUpdate();
				
		Query query = em.createNativeQuery("DELETE FROM user u WHERE u.id = :id" , UserDto.class);
		
		query.setParameter("id", id);
		
		int rowsAffected = query.executeUpdate();      
		if (rowsAffected == 0) {
        	throw new UserNotFoundException("User with id " + id + " not found.");
        }
                  
        Query queryDeleteFKCart = 
				em.createNativeQuery("DELETE FROM cart c WHERE c.id = :cartId");
		queryDeleteFKCart.setParameter("cartId", cartIdWithUser);
		queryDeleteFKCart.executeUpdate();
		
	}
	
	

}
