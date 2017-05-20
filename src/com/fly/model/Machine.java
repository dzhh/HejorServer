package com.fly.model;

public class Machine {
	
	private String mId;

	private String staId;

	private Integer mState;

	private Integer mWifi;

	private Integer m4g;

	private Integer id;

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId == null ? null : mId.trim();
	}

	public String getStaId() {
		return staId;
	}

	public void setStaId(String staId) {
		this.staId = staId == null ? null : staId.trim();
	}

	public Integer getmState() {
		return mState;
	}

	public void setmState(Integer mState) {
		this.mState = mState;
	}

	public Integer getmWifi() {
		return mWifi;
	}

	public void setmWifi(Integer mWifi) {
		this.mWifi = mWifi;
	}

	public Integer getM4g() {
		return m4g;
	}

	public void setM4g(Integer m4g) {
		this.m4g = m4g;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
