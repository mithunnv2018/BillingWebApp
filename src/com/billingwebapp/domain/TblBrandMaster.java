package com.billingwebapp.domain;

// Generated Jun 13, 2015 2:39:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * TblBrandMaster generated by hbm2java
 */
public class TblBrandMaster implements java.io.Serializable {

	private Integer brandId;
	private String brandName;
	private String brandAliasname;

	public TblBrandMaster() {
	}

	public TblBrandMaster(String brandName, String brandAliasname) {
		this.brandName = brandName;
		this.brandAliasname = brandAliasname;
	}

	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandAliasname() {
		return this.brandAliasname;
	}

	public void setBrandAliasname(String brandAliasname) {
		this.brandAliasname = brandAliasname;
	}

}