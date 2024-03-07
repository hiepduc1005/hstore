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

@Entity(name = "role")
@Table(name = "role")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name = "role_name")
	public String name;
	
	@OneToMany(
			mappedBy = "role" ,
			cascade =  CascadeType.ALL ,
			orphanRemoval = true 
			)
	public List<RolesPrivileges> privileges = new ArrayList<RolesPrivileges>();
	
	@OneToMany(mappedBy = "role" , cascade = CascadeType.ALL , orphanRemoval = true)
	public List<UsersRoles> users = new ArrayList<UsersRoles>();
	
	
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
	public void addPrivilege( Privilege privilege) {
		RolesPrivileges rolesPrivileges = new RolesPrivileges(this , privilege);
		privileges.add(rolesPrivileges);
		privilege.getRolesPrivileges().add(rolesPrivileges);
	}
	public void removePrivilege(Privilege privilege) {
		RolesPrivileges rolesPrivileges = new RolesPrivileges(this , privilege);
		privilege.getRolesPrivileges().remove(rolesPrivileges);
		privileges.remove(rolesPrivileges);
		rolesPrivileges.setPrivilege(null);
		rolesPrivileges.setRole(null);
	}
	
	public List<UsersRoles> getRoles() {
		return users;
	}
	
	public void setRoles(List<UsersRoles> roles) {
		this.users = roles;
	}
	
	
}
