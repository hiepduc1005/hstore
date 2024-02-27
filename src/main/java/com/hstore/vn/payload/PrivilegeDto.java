package com.hstore.vn.payload;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "privilege")
@Table(name = "privilege")
public class PrivilegeDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "privilege_name")
	public String name;
	
	@ManyToMany(mappedBy = "privileges")
	public List<RoleDto> roles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	
	

}
