package com.fly.model;

public class Power {

	private String powerId;
	private Integer isEnable;
	private Integer pQuantity;
	private Integer pCount;
	private Integer isBack;
	private Integer id;

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId == null ? null : powerId.trim();
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(Integer pQuantity) {
		this.pQuantity = pQuantity;
	}

	public Integer getpCount() {
		return pCount;
	}

	public void setpCount(Integer pCount) {
		this.pCount = pCount;
	}

	public Integer getIsBack() {
		return isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
