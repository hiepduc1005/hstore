package com.hstore.vn.payload;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "role")
@Table(name = "role")
public class RoleDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name = "role_name")
	public String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_privileges",
	           joinColumns = @JoinColumn(name = "role_id"),
	           inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	public List<PrivilegeDto> privileges;
	
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
	public List<PrivilegeDto> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeDto> privileges) {
		this.privileges = privileges;
	}
	
	
}
