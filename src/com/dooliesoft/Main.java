package com.dooliesoft;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static final String FOLDER = "C:\\Users\\c158492\\Desktop\\CREDIT NOTE\\ZHUL\\Nouveau Dossier";
    public static final String SENDER_ID = "SenderID";
    public static final String MESSAGE_REFERENCE_NUMBER = "MessageReferenceNumber";
    public static final String DOCUMENT_DATE = "DocumentDate";
    public static final String DOCUMENT_NUMBER = "DocumentNumber";
    public static final String PARTNER_ID = "PartnerID";
    public static final String BANK_ACCOUNT = "BankAccount";
    public static final String NAME = "Name";
    public static final String REFERENCE_NUMBER = "ReferenceNumber";
    public static final String PATH = "Path";
    public static final String DOCUMENT_ID = "DocumentID";
    public static final String FILENAME = "Filename";
    public static final String TOTAL_INVOICE_AMOUNT = "TotalInvoiceAmount";
    public static final String TOTAL_VAT_AMOUNT = "TotalVATAmount";
    public static final String TOTAL_NET_LINE_AMOUNT = "TotalNetLineAmount";

    public static HashMap<String, String> xpathMap = new HashMap<>();

    public static ArrayList<String> header;

    public static void main(String[] args) {

        header = new ArrayList<>();
        header.add(SENDER_ID);
        header.add(MESSAGE_REFERENCE_NUMBER);
        header.add(DOCUMENT_DATE);
        header.add(DOCUMENT_NUMBER);
        header.add(PARTNER_ID);
        header.add(BANK_ACCOUNT);
        header.add(NAME);
        header.add(REFERENCE_NUMBER);
        header.add(PATH);
        header.add(DOCUMENT_ID);
        header.add(FILENAME);
        header.add(TOTAL_INVOICE_AMOUNT);
        header.add(TOTAL_VAT_AMOUNT);
        header.add(TOTAL_NET_LINE_AMOUNT);

        ArrayList<Invoice> invoices = new ArrayList<>();

        xpathMap.put(SENDER_ID, "/Document/SenderID[1]");
        xpathMap.put(MESSAGE_REFERENCE_NUMBER, "/Document/Invoice[1]/MessageReferenceNumber[1]");
        xpathMap.put(DOCUMENT_DATE, "/Document/Invoice[1]/InvoiceHeader[1]/Dates[1]");
        xpathMap.put(DOCUMENT_NUMBER, "/Document/DocumentNumber[1]");
        xpathMap.put(PARTNER_ID, "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/PartnerID[1]");
        xpathMap.put(BANK_ACCOUNT, "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/BankAccount[1]");
        xpathMap.put(NAME, "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/Name[1]");
        xpathMap.put(REFERENCE_NUMBER, "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/PartnerReferences[1]/ReferenceNumber[1]");
        xpathMap.put(PATH, "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/Path[1]");
        xpathMap.put(DOCUMENT_ID, "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/DocumentID[1]");
        xpathMap.put(FILENAME, "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/Filename[1]");
        xpathMap.put(TOTAL_INVOICE_AMOUNT, "/Document/Invoice[1]/InvoiceTotals[1]/TotalInvoiceAmount[1]");
        xpathMap.put(TOTAL_VAT_AMOUNT, "/Document/Invoice[1]/InvoiceTotals[1]/TotalVATAmount[1]");
        xpathMap.put(TOTAL_NET_LINE_AMOUNT, "/Document/Invoice[1]/InvoiceTotals[1]/TotalNetLineAmount[1]");

        File folder = new File(FOLDER);
        for (File fileEntry : folder.listFiles()) {
            try {
                if (!fileEntry.isDirectory()) {
                    System.out.println(fileEntry.getAbsoluteFile());

                    File fXmlFile = fileEntry.getAbsoluteFile();
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);
                    Element root = doc.getDocumentElement();

                    Invoice draftInvoice = new Invoice(header);

                    draftInvoice.setSenderID(getValue(root, SENDER_ID));
                    draftInvoice.setMessageReferenceNumber(getValue(root, MESSAGE_REFERENCE_NUMBER));
                    draftInvoice.setDocumentDate(getValue(root, DOCUMENT_DATE));
                    draftInvoice.setDocumentNumber(getValue(root, DOCUMENT_NUMBER));
                    draftInvoice.setPartnerID(getValue(root, PARTNER_ID));
                    draftInvoice.setBankAccount(getValue(root, BANK_ACCOUNT));
                    draftInvoice.setName(getValue(root, NAME));
                    draftInvoice.setReferenceNumber(getValue(root, REFERENCE_NUMBER));
                    draftInvoice.setPath(getValue(root, PATH));
                    draftInvoice.setDocumentID(getValue(root, DOCUMENT_ID));
                    draftInvoice.setFilename(getValue(root, FILENAME));
                    draftInvoice.setTotalInvoiceAmount(getValue(root, TOTAL_INVOICE_AMOUNT));
                    draftInvoice.setTotalVATAmount(getValue(root, TOTAL_VAT_AMOUNT));
                    draftInvoice.setTotalNetLineAmount(getValue(root, TOTAL_NET_LINE_AMOUNT));

                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getValue(Element root, String key) throws XPathExpressionException {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath path = xpf.newXPath();
        String expression = xpathMap.get(key);
        String str = (String)path.evaluate(expression, root);
        return str.trim();
    }
}

