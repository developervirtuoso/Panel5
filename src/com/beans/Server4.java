package com.beans;

public class Server4 {
	private String Account;
	private Long Id;
	private Long SUB;
	private Long DEL;
	private Long percentage;
	private Long Pending;
	public Long getPending_per() {
		return Pending_per;
	}
	public void setPending_per(Long pending_per) {
		Pending_per = pending_per;
	}
	private Long Pending_per;
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getSUB() {
		return SUB;
	}
	public void setSUB(Long sUB) {
		SUB = sUB;
	}
	public Long getDEL() {
		return DEL;
	}
	public void setDEL(Long dEL) {
		DEL = dEL;
	}
	public Long getPercentage() {
		return percentage;
	}
	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}
	public Long getPending() {
		return Pending;
	}
	public void setPending(Long pending) {
		Pending = pending;
	}
	@Override
	public String toString() {
		return "Server4 [Account=" + Account + ", Id=" + Id + ", SUB=" + SUB + ", DEL=" + DEL + ", percentage="
				+ percentage + ", Pending=" + Pending + "]";
	}
	
	
	
}
