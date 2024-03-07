package com.hstore.vn.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "privilege")
@Table(name = "privilege")
public class Privilege implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "privilege_name")
	public String name;
	
	@OneToMany(mappedBy = "privilege" , cascade =  CascadeType.ALL , orphanRemoval =  true )
	public List<RolesPrivileges> roles = new ArrayList<RolesPrivileges>();
	
	public Integer getId() {
		return id;
	}
	public List<RolesPrivileges> getRolesPrivileges() {
		return roles;
	}
	public void setRolesPrivileges(List<RolesPrivileges> rolesPrivileges) {
		this.roles = rolesPrivileges;
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
		
	

}
