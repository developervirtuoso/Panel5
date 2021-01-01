package com.beans;

public class Server4VfReport {
	private String Account;
	private Long Id;
	private Long SUB;
	private Long sub_per;
	private Long DEL;
	private Long mtd;
	public Long getMtd() {
		return mtd;
	}
	public void setMtd(Long mtd) {
		this.mtd = mtd;
	}
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
	public Long getSub_per() {
		return sub_per;
	}
	public void setSub_per(Long sub_per) {
		this.sub_per = sub_per;
	}
	public Long getDEL() {
		return DEL;
	}
	public void setDEL(Long dEL) {
		DEL = dEL;
	}
	public Long getDel_per() {
		return del_per;
	}
	public void setDel_per(Long del_per) {
		this.del_per = del_per;
	}
	public Long getFAILED() {
		return FAILED;
	}
	public void setFAILED(Long fAILED) {
		FAILED = fAILED;
	}
	public Long getFailed_per() {
		return failed_per;
	}
	public void setFailed_per(Long failed_per) {
		this.failed_per = failed_per;
	}
	public Long getWAITING() {
		return WAITING;
	}
	public void setWAITING(Long wAITING) {
		WAITING = wAITING;
	}
	public Long getWaiting_per() {
		return waiting_per;
	}
	public void setWaiting_per(Long waiting_per) {
		this.waiting_per = waiting_per;
	}
	private Long del_per;
	private Long FAILED;
	private Long failed_per;
	private Long WAITING;
	private Long waiting_per;
}
