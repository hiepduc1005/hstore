package com.hstore.vn.payload.response;


public class  ApiResponse<T>{
	
	public String EM;
	public T DT;
	public Integer EC;
	
	
	public ApiResponse(String EM ,T DT, Integer EC) {
		this.DT = DT;
		this.EC = EC;
		this.EM = EM;
	}
	
}
