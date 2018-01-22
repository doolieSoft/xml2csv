package com.dooliesoft;

import java.util.*;

class Invoice {
    HashMap<String, String> csvColumnToXPathMap = null;
    String senderID = null;
    String messageReferenceNumber = null;
    String documentDate = null;
    String documentNumber = null;
    String partnerID = null;
    String bankAccount = null;
    String name = null;
    String referenceNumber = null;
    String path = null;
    String documentID = null;
    String filename = null;
    String totalInvoiceAmount = null;
    String totalVATAmount = null;
    String totalNetLineAmount = null;

    HashMap<String, String> invoiceMap;

    public Invoice() {
        invoiceMap = new HashMap<>();
    }

    public void set(String key, String value) {
        invoiceMap.put(key, value);
    }
    public String get(String key) {
        return invoiceMap.get(key);
    }

    public void printInCSV(LinkedHashMap<String, Integer> csvMap) {
        for(String key: csvMap.keySet()) {
            System.out.print(csvMap.get(key)+";");
        }
    }
}