package com.hstore.vn.payload.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.impl.DefaultPrivilege;
import com.hstore.vn.payload.PrivilegeDto;


@Service
public class PrivilegeConvert {
	
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

}
