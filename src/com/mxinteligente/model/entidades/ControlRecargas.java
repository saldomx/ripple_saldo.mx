package com.mxinteligente.model.entidades;

import java.util.Date;

public class ControlRecargas {
	
	private int invoiceId;
	private String productId;
	private String accountId;
	private String amount;
	private Date transactionDateTimeLocal;
	private Date transactionDateTime;
	private String resultCode;
	private String transactionId;
	private String responseMessage;
	private String siteID;
	private String carrierControl;
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getSiteID() {
		return siteID;
	}
	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}
	public String getCarrierControl() {
		return carrierControl;
	}
	public void setCarrierControl(String carrierControl) {
		this.carrierControl = carrierControl;
	}
	public Date getTransactionDateTimeLocal() {
		return transactionDateTimeLocal;
	}
	public void setTransactionDateTimeLocal(Date transactionDateTimeLocal) {
		this.transactionDateTimeLocal = transactionDateTimeLocal;
	}
	

}
