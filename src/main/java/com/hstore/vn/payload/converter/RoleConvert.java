package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.entity.Role;
import com.hstore.vn.payload.request.RoleRequest;
import com.hstore.vn.payload.response.RoleResponse;



@Service
public class RoleConvert {
	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public PrivilegeConvert privilegeConvert;
	
	
	public Role roleRequestConvertToRoleDto(RoleRequest roleRequest) {
		Role roleDto =roleDao.getRoleByName(roleRequest.getName());
		
		return roleDto;
	}
	
	public List<Role> rolesRequestConvertToRolesDto(List<RoleRequest> roleRequests){
		List<Role> rolesDto = new ArrayList<Role>();
		
		for(RoleRequest roleRequest : roleRequests) {
			rolesDto.add(roleRequestConvertToRoleDto(roleRequest));
		}
		
		return rolesDto;
		
	}
	
	public RoleResponse roleConvertToRoleResponse(Role role) {
		RoleResponse roleResponse = new RoleResponse(
				role.getId(),
				role.getName(),

				privilegeConvert.privilegesConvertToPrivilegesResponse(role.getPrivileges())

				);
		return roleResponse;
	}
	
	public List<RoleResponse> rolesConvertToRolesResponse(List<Role> roles){
        List<RoleResponse> rolesResponses = new ArrayList<RoleResponse>();
		
		for(Role role : roles) {
			rolesResponses.add(roleConvertToRoleResponse(role));
		}
		
		return rolesResponses;
		
	}

}
