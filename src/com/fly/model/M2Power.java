package com.fly.model;

/**
 * @author 作者 hejor
 * @version 
 * 类说明 电池仓关联充电宝 模型
 */
public class M2Power {

	private Integer id;
	private String mId;
	private Integer cId;
	private String powerId;
	private Integer isempty;
	private Integer pLock;
	private Power power;
	private String updateTime;
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId == null ? null : mId.trim();
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId == null ? null : powerId.trim();
	}

	public Integer getIsempty() {
		return isempty;
	}

	public void setIsempty(Integer isempty) {
		this.isempty = isempty;
	}

	public Integer getpLock() {
		return pLock;
	}

	public void setpLock(Integer pLock) {
		this.pLock = pLock;
	}
}
