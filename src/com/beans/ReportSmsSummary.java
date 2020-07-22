package com.beans;

public class ReportSmsSummary {
	private String summary;
	private String total="0";
	private String success="0";
	private long pending=0;
	private String notSent="0";
	private String failed="0";
	private String others="0";
	private String refund="0";
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public long getPending() {
		return pending;
	}
	public void setPending(long pending) {
		this.pending = pending;
	}
	public String getNotSent() {
		return notSent;
	}
	public void setNotSent(String notSent) {
		this.notSent = notSent;
	}
	public String getFailed() {
		return failed;
	}
	public void setFailed(String failed) {
		this.failed = failed;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	@Override
	public String toString() {
		return "ReportSmsSummary [summary=" + summary + ", total=" + total + ", success=" + success + ", pending="
				+ pending + ", notSent=" + notSent + ", failed=" + failed + ", others=" + others + ", refund=" + refund
				+ "]";
	}
	
	
	
}
