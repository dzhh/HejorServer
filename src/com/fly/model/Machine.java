package com.fly.model;

public class Machine {
	
	private String m_id;

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	private boolean m_state;
	
	public boolean isM_state() {
		return m_state;
	}

	public void setM_state(boolean m_state) {
		this.m_state = m_state;
	}
	private String sta_id;
	
	private String m_wifi;
	
	private String m_4G;
	
	public String getSta_id() {
		return sta_id;
	}

	public void setSta_id(String sta_id) {
		this.sta_id = sta_id;
	}

	public String getM_wifi() {
		return m_wifi;
	}

	public void setM_wifi(String m_wifi) {
		this.m_wifi = m_wifi;
	}

	public String getM_4G() {
		return m_4G;
	}

	public void setM_4G(String m_4g) {
		m_4G = m_4g;
	}
}
