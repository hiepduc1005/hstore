package com.hstore.vn.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "purchase_status")
@Table(name = "purchase_status")
public class PurchaseStatusDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name = "status_name")
	public String statusName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
}
