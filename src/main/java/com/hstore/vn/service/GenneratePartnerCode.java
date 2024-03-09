package com.hstore.vn.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hstore.vn.dao.UserDao;


@Service
public class GenneratePartnerCode {
	
	@Autowired
	public UserDao userDao;

	private static final char[] CHARACTER_AND_NUMBER ={'Q','W','E','R','T','Y','U','I',
			                                           'O','P','A','S','D','F','G','H',
			                                           'J','K','L','Z','X','C','V','B',
			                                           'N','M','0','1','2','3','4','5',
			                                           '6','7','8','9'};
	
	public static final int PARTNER_CODE_LENGTH = 6;
	
	public String genneratePartnerCode() {
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		
		for(int i = 1 ; i <= 6 ; i++) {
			sb.append(CHARACTER_AND_NUMBER[rd.nextInt(CHARACTER_AND_NUMBER.length)]);
		}
		String res = sb.toString();
//		if(userDao.getUserByPartnerCode(res) != null) {
//			return this.genneratePartnerCode();
//		}
		
			return res;
		
		
		
	}
	
}
