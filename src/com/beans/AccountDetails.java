package com.beans;

public class AccountDetails {
	private int id;
	private int accountid;
	private String accountname;
	private String companyname;
	private String accounttype;
	private String PM;
	private String server;
	private String pwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getPM() {
		return PM;
	}
	public void setPM(String pM) {
		PM = pM;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "AccountDetails [id=" + id + ", accountid=" + accountid + ", accountname=" + accountname
				+ ", companyname=" + companyname + ", accounttype=" + accounttype + ", PM=" + PM + ", server=" + server
				+ ", pwd=" + pwd + "]";
	}
	
	
}
