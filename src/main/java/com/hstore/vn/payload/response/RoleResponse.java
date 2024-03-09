package com.hstore.vn.payload.response;

import java.util.List;

public class RoleResponse {
	
	public Integer id;
	
	public String name;
	
	public List<PrivilegeResponse> privileges;

	public RoleResponse(Integer id, String name, List<PrivilegeResponse> privilegeResponses) {
		
		this.id = id;
		this.name = name;
		this.privileges = privilegeResponses;
	}
	
}
