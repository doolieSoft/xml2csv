package com.dooliesoft;

import java.util.ArrayList;

public class Invoice {

    private ArrayList<String> m_header = new ArrayList<>();
    private String senderID;
    private String messageReferenceNumber;
    private String documentDate;
    private String documentNumber;
    private String partnerID;
    private String bankAccount;
    private String name;
    private String referenceNumber;
    private String path;
    private String documentID;
    private String filename;
    private String totalInvoiceAmount;
    private String totalVATAmount;
    private String totalNetLineAmount;

    public Invoice(ArrayList<String> header) {
        m_header = header;
    }


    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setMessageReferenceNumber(String messageReferenceNumber) {
        this.messageReferenceNumber = messageReferenceNumber;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setTotalInvoiceAmount(String totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public void setTotalVATAmount(String totalVATAmount) {
        this.totalVATAmount = totalVATAmount;
    }

    public void setTotalNetLineAmount(String totalNetLineAmount) {
        this.totalNetLineAmount = totalNetLineAmount;
    }


    public String getSenderID() {
        return senderID;
    }

    public String getMessageReferenceNumber() {
        return messageReferenceNumber;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getName() {
        return name;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getPath() {
        return path;
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getFilename() {
        return filename;
    }

    public String getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public String getTotalVATAmount() {
        return totalVATAmount;
    }

    public String getTotalNetLineAmount() {
        return totalNetLineAmount;
    }
}
