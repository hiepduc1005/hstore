package com.hstore.vn.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class BlackListToken {
	
	private Set<String> blackListToken = new HashSet<String>();
	
	public boolean isTokenBlackListed(String token) {
		return blackListToken.contains(token);
	}
	
	public void addBlackListToken(String token) {
		blackListToken.add(token);
	}

	public Set<String> getBlackListToken() {
		return blackListToken;
	}

	public void setBlackListToken(Set<String> blackListToken) {
		this.blackListToken = blackListToken;
	}
	
	
}
