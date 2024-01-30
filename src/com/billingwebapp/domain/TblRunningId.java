package com.billingwebapp.domain;

// Generated Jun 13, 2015 2:39:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * TblRunningId generated by hbm2java
 */
public class TblRunningId implements java.io.Serializable {

	private Integer runningIdPkid;
	private String runningIdName;
	private int runningIdNumber;
	private String runningIdString;

	public TblRunningId() {
	}

	public TblRunningId(String runningIdName, int runningIdNumber,
			String runningIdString) {
		this.runningIdName = runningIdName;
		this.runningIdNumber = runningIdNumber;
		this.runningIdString = runningIdString;
	}

	public Integer getRunningIdPkid() {
		return this.runningIdPkid;
	}

	public void setRunningIdPkid(Integer runningIdPkid) {
		this.runningIdPkid = runningIdPkid;
	}

	public String getRunningIdName() {
		return this.runningIdName;
	}

	public void setRunningIdName(String runningIdName) {
		this.runningIdName = runningIdName;
	}

	public int getRunningIdNumber() {
		return this.runningIdNumber;
	}

	public void setRunningIdNumber(int runningIdNumber) {
		this.runningIdNumber = runningIdNumber;
	}

	public String getRunningIdString() {
		return this.runningIdString;
	}

	public void setRunningIdString(String runningIdString) {
		this.runningIdString = runningIdString;
	}

}