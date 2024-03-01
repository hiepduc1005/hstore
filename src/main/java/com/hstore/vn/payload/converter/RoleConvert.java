package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.impl.DefaultRole;
import com.hstore.vn.payload.RoleDto;
import com.hstore.vn.payload.request.RoleRequest;



@Service
public class RoleConvert {
	
	@Autowired
	public PrivilegeConvert privilegeConvert;
	
	@Autowired
	public RoleDao roleDao;
	
	public Role roleDtoConvertToRole(RoleDto roleDto) {
		if(roleDto == null) {
			return null;
		}
		
		Role role = new DefaultRole();
		
		role.setId(roleDto.getId());
		role.setName(roleDto.getName());
		role.setPrivilege(privilegeConvert.convertPrivilegeDtosToPrivileges(roleDto.getPrivileges()));
		
		return role;
	}
	
	public RoleDto roleConvertToRoleDto(Role role) {
		if(role == null) {
			return null;
		}
		
		RoleDto roleDto = new RoleDto();
		
		roleDto.setId(role.getId());
		roleDto.setName(role.getName());
		roleDto.setPrivileges(privilegeConvert.convertPrivilegesToPrivilegeDtos(role.getPrivileges()));
		
		return roleDto;
	}
	
	public List<Role> convertRolesDtoToRoles(List<RoleDto> roleDtos){
		if(roleDtos == null) {
			return null;
		}
		
		
		List<Role> roles = new ArrayList<Role>();
		
		for(RoleDto roleDto : roleDtos) {
			roles.add(roleDtoConvertToRole(roleDto));
		}
		
		return roles;
	}
	
	public List<RoleDto> convertRolesToRolesDto(List<Role> roles){
		if(roles == null) {
			return null;
		}
		
		List<RoleDto> rolesDto = new ArrayList<RoleDto>();
		
		for(Role role : roles) {
			rolesDto.add(roleConvertToRoleDto(role));
		}
		
		return rolesDto;
	}
	
	
	public RoleDto roleRequestConvertToRoleDto(RoleRequest roleRequest) {
		RoleDto roleDto =roleDao.getRoleByName(roleRequest.getName());
		
		return roleDto;
	}
	
	public List<RoleDto> rolesRequestConvertToRolesDto(List<RoleRequest> roleRequests){
		List<RoleDto> rolesDto = new ArrayList<RoleDto>();
		
		for(RoleRequest roleRequest : roleRequests) {
			rolesDto.add(roleRequestConvertToRoleDto(roleRequest));
		}
		
		return rolesDto;
		
	}

}
