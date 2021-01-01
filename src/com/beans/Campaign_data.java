package com.beans;

public class Campaign_data {

	private int id;
	private String account;
	private long sub;
	private long del;
	private String timee;
	public String getTimee() {
		return timee;
	}
	public void setTimee(String timee) {
		this.timee = timee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public long getPer() {
		return per;
	}
	public void setPer(long per) {
		this.per = per;
	}
	public long getPending() {
		return pending;
	}
	public void setPending(long pending) {
		this.pending = pending;
	}
	public long getSub_total() {
		return sub_total;
	}
	public void setSub_total(long sub_total) {
		this.sub_total = sub_total;
	}
	public long getDel_total() {
		return del_total;
	}
	public void setDel_total(long del_total) {
		this.del_total = del_total;
	}
	public long getPer_total() {
		return per_total;
	}
	public void setPer_total(long per_total) {
		this.per_total = per_total;
	}
	public long getPending_total() {
		return pending_total;
	}
	public void setPending_total(long pending_total) {
		this.pending_total = pending_total;
	}
	public long getAdd_on() {
		return add_on;
	}
	public void setAdd_on(long add_on) {
		this.add_on = add_on;
	}
	private long per;
	private long pending;
	private long sub_total;
	private long del_total;
	private long per_total;
	private long pending_total;
	private long add_on;
}
