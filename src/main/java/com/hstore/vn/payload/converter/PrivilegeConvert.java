package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.PrivilegeDao;
import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.impl.DefaultPrivilege;
import com.hstore.vn.payload.PrivilegeDto;
import com.hstore.vn.payload.request.PrivilegeRequest;


@Service
public class PrivilegeConvert {
	
	@Autowired
	public PrivilegeDao privilegeDao;
	
	public Privilege privilegeDtoConvertToPrivilege(PrivilegeDto privilegeDto) {
		if(privilegeDto == null) {
			return null;
		}
		
		Privilege privilege = new DefaultPrivilege();
		
		privilege.setId(privilegeDto.getId());
		privilege.setName(privilegeDto.getName());
		
		
		return privilege;
	}
	
	public PrivilegeDto privilegeConvertToPrivilegeDto(Privilege privilege) {
		if(privilege == null) {
			return null;
		}
		
		PrivilegeDto privilegeDto = new PrivilegeDto();
		
		privilegeDto.setId(privilege.getId());
		privilegeDto.setName(privilege.getName());
		
		
		return privilegeDto;
	}
	
	public List<Privilege> convertPrivilegeDtosToPrivileges(List<PrivilegeDto> privilegeDtos){
		if(privilegeDtos == null) {
			return null;
		}
		
		
		List<Privilege> privileges = new ArrayList<Privilege>();
		
		for(PrivilegeDto privilegeDto : privilegeDtos) {
			privileges.add(privilegeDtoConvertToPrivilege(privilegeDto));
		}
		
		return privileges;
	}
	
	public List<PrivilegeDto> convertPrivilegesToPrivilegeDtos(List<Privilege> privileges){
		if(privileges == null) {
			return null;
		}
		
		List<PrivilegeDto> privilegeDtos = new ArrayList<PrivilegeDto>();
		
		for(Privilege privilege : privileges) {
			privilegeDtos.add(privilegeConvertToPrivilegeDto(privilege));
		}
		
		return privilegeDtos;
	}
	
	public PrivilegeDto privilegeRequestConvertToPrivilegeDto(PrivilegeRequest privilegeRequest) {
		PrivilegeDto privilegeDto = privilegeDao.getPrivilegeByName(privilegeRequest.getName());
		
		return privilegeDto;
	}
	
	public List<PrivilegeDto> privilegesRequestConvertToPrivilegesDto(List<PrivilegeRequest> privilegeRequests){
		List<PrivilegeDto> privileges = new ArrayList<PrivilegeDto>();
		
		for(PrivilegeRequest privilegeRequest : privilegeRequests) {
			privileges.add(privilegeRequestConvertToPrivilegeDto(privilegeRequest));
		}
		
		return privileges;
		
//		return privilegeRequests.stream()
//				.map(this :: privilegeRequestConvertToPrivilegeDto )
//				.collect(Collectors.toList());
	}

}
