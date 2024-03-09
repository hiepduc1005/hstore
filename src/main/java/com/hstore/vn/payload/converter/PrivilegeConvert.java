package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.PrivilegeDao;
import com.hstore.vn.entity.Privilege;
import com.hstore.vn.payload.request.PrivilegeRequest;
import com.hstore.vn.payload.response.PrivilegeResponse;


@Service
public class PrivilegeConvert {
	
	@Autowired
	public PrivilegeDao privilegeDao;
	
	
	
	public Privilege privilegeRequestConvertToPrivilege(PrivilegeRequest privilegeRequest) {
		Privilege privilege = privilegeDao.getPrivilegeByName(privilegeRequest.getName());
		
		return privilege;
	}
	
	public List<Privilege> privilegesRequestConvertToPrivileges(List<PrivilegeRequest> privilegeRequests){
		List<Privilege> privileges = new ArrayList<Privilege>();
		
		for(PrivilegeRequest privilegeRequest : privilegeRequests) {
			privileges.add(privilegeRequestConvertToPrivilege(privilegeRequest));
		}
		
		return privileges;
		
//		return privilegeRequests.stream()
//				.map(this :: privilegeRequestConvertToPrivilegeDto )
//				.collect(Collectors.toList());
	}
	
	public PrivilegeResponse privilegeConvertToPrivilegeResponse(Privilege privilege) {
		PrivilegeResponse privilegeResponse = new PrivilegeResponse(
				privilege.getId(),
				privilege.getName()
				);
		
		return privilegeResponse;
	}
	
	public List<PrivilegeResponse> privilegesConvertToPrivilegesResponse(List<Privilege> privileges){
		List<PrivilegeResponse> privilegeResponses = new ArrayList<PrivilegeResponse>();
		
		for(Privilege privilege : privileges) {
			privilegeResponses.add(privilegeConvertToPrivilegeResponse(privilege));
		}
		
		return privilegeResponses;
	}

}
