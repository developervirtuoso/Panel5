package com.beans;

public class ReportRecord {


	private String account;
	private long sub;
	private long del;
	private long del_per;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public long getSub() {
		return sub;
	}
	public void setSub(long sub) {
		this.sub = sub;
	}
	public long getDel() {
		return del;
	}
	public void setDel(long del) {
		this.del = del;
	}
	public long getDel_per() {
		return del_per;
	}
	public void setDel_per(long del_per) {
		this.del_per = del_per;
	}
	public long getFailed() {
		return failed;
	}
	public void setFailed(long failed) {
		this.failed = failed;
	}
	public long getFailed_per() {
		return failed_per;
	}
	public void setFailed_per(long failed_per) {
		this.failed_per = failed_per;
	}
	public long getWaiting() {
		return waiting;
	}
	public void setWaiting(long waiting) {
		this.waiting = waiting;
	}
	public long getWaiting_per() {
		return waiting_per;
	}
	public void setWaiting_per(long waiting_per) {
		this.waiting_per = waiting_per;
	}
	public long getMtd() {
		return mtd;
	}
	public void setMtd(long mtd) {
		this.mtd = mtd;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	private long failed;
	private long failed_per;
	private long waiting;
	private long waiting_per;
	private long mtd;
	private String report_date;
	

}
