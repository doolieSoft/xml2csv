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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    //public static final String FOLDER = "C:\\Users\\c158492\\Desktop\\CREDIT NOTE\\ZHUL\\Nouveau Dossier";
    //public static final String FOLDER = "C:\\Users\\fabrice\\Downloads\\Lot2";
    public static final String FOLDER = "C:\\Users\\fabrice\\Downloads\\Test";
    public static final String CSV_SENDER_ID = "SenderID";
    public static final String CSV_MESSAGE_REFERENCE_NUMBER = "MessageReferenceNumber";
    public static final String CSV_DOCUMENT_DATE = "DocumentDate";
    public static final String CSV_DOCUMENT_NUMBER = "DocumentNumber";
    public static final String CSV_PARTNER_ID = "PartnerID";
    public static final String CSV_BANK_ACCOUNT = "BankAccount";
    public static final String CSV_NAME = "Name";
    public static final String CSV_REFERENCE_NUMBER = "ReferenceNumber";
    public static final String CSV_PATH = "Path";
    public static final String CSV_DOCUMENT_ID = "DocumentID";
    public static final String CSV_FILENAME = "Filename";
    public static final String CSV_TOTAL_INVOICE_AMOUNT = "TotalInvoiceAmount";
    public static final String CSV_TOTAL_VAT_AMOUNT = "TotalVATAmount";
    public static final String CSV_TOTAL_NET_LINE_AMOUNT = "TotalNetLineAmount";
    public static final String XML_SENDER_ID = "/Document/SenderID[1]";
    public static final String XML_MESSAGE_REFERENCE_NUMBER = "/Document/Invoice[1]/MessageReferenceNumber[1]";
    public static final String XML_INVOICE_DATE = "/Document/Invoice[1]/InvoiceHeader[1]/Dates[1]";
    public static final String XML_DOCUMENT_NUMBER = "/Document/DocumentNumber[1]";
    public static final String XML_PARTNER_ID = "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/PartnerID[1]";
    public static final String XML_BANK_ACCOUNT = "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/BankAccount[1]";
    public static final String XML_NAME = "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/Name[1]";
    public static final String XML_REFERENCE_NUMBER = "/Document/Invoice[1]/InvoiceHeader[1]/Partners[1]/Partner[2]/PartnerReferences[1]/ReferenceNumber[1]";
    public static final String XML_PATH = "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/Path[1]";
    public static final String XML_DOCUMENT_ID = "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/DocumentID[1]";
    public static final String XML_FILENAME = "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/Filename[1]";
    public static final String XML_INVOICE_AMOUNT = "/Document/Invoice[1]/InvoiceTotals[1]/TotalInvoiceAmount[1]";
    public static final String XML_TOTAL_VATAMOUNT = "/Document/Invoice[1]/InvoiceTotals[1]/TotalVATAmount[1]";
    public static final String XML_TOTAL_NET_LINE_AMOUNT = "/Document/Invoice[1]/InvoiceTotals[1]/TotalNetLineAmount[1]";
    public static final String XML_MESSAGE_NUMBER = "/Document/Invoice[1]/InvoiceHeader[1]/MessageNumber[1]";
    public static final String XML_DOCUMENT_DATE = "/Document/Invoice[1]/InvoiceHeader[1]/CustomAddendum[1]/MetaData[1]/ScanParty[1]/Document[1]/DocumentDate[1]";
    public static final String XML_MESSAGE_TYPE= "/Document/Invoice[1]/InvoiceHeader[1]/MessageType[1]";

    private static final String KEY_SENDER_ID = "SenderID";
    private static final String KEY_REFERENCE_NUMBER = "ReferenceNumber";
    private static final String KEY_DOCUMENT_DATE = "DocumentDate";
    private static final String KEY_DOCUMENT_NUMBER = "DocumentNumber";
    private static final String KEY_PARTNER_ID = "PartnerID";
    private static final String KEY_BANK_ACCOUNT = "BankAccount";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PATH = "Path";
    private static final String KEY_DOCUMENT_ID = "DocumentID";
    private static final String KEY_FILENAME = "Filename";
    private static final String KEY_TOTAL_INVOICE_AMOUNT = "TotalInvoiceAmount";
    private static final String KEY_TOTAL_VAT_AMOUNT = "TotalVATAmount";
    private static final String KEY_TOTAL_NET_LINE_AMOUNT = "TotalNetLineAmount";
    private static final String KEY_MESSAGE_NUMBER = "MessageNumber";
    private static final String KEY_INVOICE_DATE= "InvoideDate";
    private static final String KEY_MESSAGE_TYPE= "MessageType";

    public static Xml2CsvMapper xml2CsvMapper;
    public static HashMap<String, String> xpathMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        initializeHeaders();

        ArrayList<Invoice> invoices = new ArrayList<>();

        File folder = new File(FOLDER);
        for (File fileEntry : folder.listFiles()) {
            try {
                if (!fileEntry.isDirectory()) {
                    //System.out.println(fileEntry.getAbsoluteFile());
                    File fXmlFile = fileEntry.getAbsoluteFile();

                    Invoice draftInvoice = new Invoice();

                    draftInvoice.set(KEY_SENDER_ID, getValue(fXmlFile, KEY_SENDER_ID));
                    draftInvoice.set(KEY_REFERENCE_NUMBER, getValue(fXmlFile, KEY_REFERENCE_NUMBER));
                    draftInvoice.set(KEY_INVOICE_DATE, getValue(fXmlFile, KEY_INVOICE_DATE));
                    draftInvoice.set(KEY_DOCUMENT_NUMBER, getValue(fXmlFile, KEY_DOCUMENT_NUMBER));
                    draftInvoice.set(KEY_PARTNER_ID, getValue(fXmlFile, KEY_PARTNER_ID));
                    draftInvoice.set(KEY_BANK_ACCOUNT, getValue(fXmlFile, KEY_BANK_ACCOUNT));
                    draftInvoice.set(KEY_NAME, getValue(fXmlFile, KEY_NAME));
                    draftInvoice.set(KEY_REFERENCE_NUMBER, getValue(fXmlFile, KEY_REFERENCE_NUMBER));
                    draftInvoice.set(KEY_PATH, getValue(fXmlFile, KEY_PATH));
                    draftInvoice.set(KEY_DOCUMENT_ID, getValue(fXmlFile, KEY_DOCUMENT_ID));
                    draftInvoice.set(KEY_FILENAME, getValue(fXmlFile, KEY_FILENAME));
                    draftInvoice.set(KEY_TOTAL_INVOICE_AMOUNT, getValue(fXmlFile, KEY_TOTAL_INVOICE_AMOUNT));
                    draftInvoice.set(KEY_TOTAL_VAT_AMOUNT, getValue(fXmlFile, KEY_TOTAL_VAT_AMOUNT));
                    draftInvoice.set(KEY_TOTAL_NET_LINE_AMOUNT, getValue(fXmlFile, KEY_TOTAL_NET_LINE_AMOUNT));
                    draftInvoice.set(KEY_MESSAGE_NUMBER, getValue(fXmlFile, KEY_MESSAGE_NUMBER));
                    draftInvoice.set(KEY_DOCUMENT_DATE, getValue(fXmlFile, KEY_DOCUMENT_DATE));
                    draftInvoice.set(KEY_MESSAGE_TYPE, getValue(fXmlFile, KEY_MESSAGE_TYPE));
                    invoices.add(draftInvoice);
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

//        for (Invoice invoice : invoices) {
//            System.out.println(invoice.get(KEY_SENDER_ID));
//            System.out.println(invoice.get(KEY_REFERENCE_NUMBER));
//            System.out.println(invoice.get(KEY_DOCUMENT_DATE));
//            System.out.println(invoice.get(KEY_DOCUMENT_NUMBER));
//            System.out.println(invoice.get(KEY_PARTNER_ID));
//            System.out.println(invoice.get(KEY_BANK_ACCOUNT));
//            System.out.println(invoice.get(KEY_NAME));
//            System.out.println(invoice.get(KEY_REFERENCE_NUMBER));
//            System.out.println(invoice.get(KEY_PATH));
//            System.out.println(invoice.get(KEY_DOCUMENT_ID));
//            System.out.println(invoice.get(KEY_FILENAME));
//            System.out.println(invoice.get(KEY_TOTAL_INVOICE_AMOUNT));
//            System.out.println(invoice.get(KEY_TOTAL_VAT_AMOUNT));
//            System.out.println(invoice.get(KEY_TOTAL_NET_LINE_AMOUNT));
//        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("test.csv"));
        writer.write(KEY_SENDER_ID);
        writer.write(";");
        writer.write(KEY_REFERENCE_NUMBER);
        writer.write(";");
        writer.write(KEY_DOCUMENT_DATE);
        writer.write(";");
        writer.write(KEY_DOCUMENT_NUMBER);
        writer.write(";");
        writer.write(KEY_PARTNER_ID);
        writer.write(";");
        writer.write(KEY_BANK_ACCOUNT);
        writer.write(";");
        writer.write(KEY_NAME);
        writer.write(";");
        writer.write(KEY_REFERENCE_NUMBER);
        writer.write(";");
        writer.write(KEY_PATH);
        writer.write(";");
        writer.write(KEY_DOCUMENT_ID);
        writer.write(";");
        writer.write(KEY_FILENAME);
        writer.write(";");
        writer.write(KEY_TOTAL_INVOICE_AMOUNT);
        writer.write(";");
        writer.write(KEY_TOTAL_VAT_AMOUNT);
        writer.write(";");
        writer.write(KEY_TOTAL_NET_LINE_AMOUNT);
        writer.write(";");
        writer.write(KEY_MESSAGE_NUMBER);
        writer.write(";");
        writer.write(KEY_INVOICE_DATE);
        writer.write(";");
        writer.write(KEY_MESSAGE_TYPE);
        writer.write("\n");

        for (Invoice invoice : invoices) {
//            System.out.print(invoice.get(KEY_SENDER_ID));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_REFERENCE_NUMBER));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_DOCUMENT_DATE));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_DOCUMENT_NUMBER));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_PARTNER_ID));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_BANK_ACCOUNT));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_NAME));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_REFERENCE_NUMBER));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_PATH));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_DOCUMENT_ID));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_FILENAME));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_TOTAL_INVOICE_AMOUNT));
//            System.out.print(";");
//            System.out.print(invoice.get(KEY_TOTAL_VAT_AMOUNT));
//            System.out.print(";");
//            System.out.println(invoice.get(KEY_TOTAL_NET_LINE_AMOUNT));

            writer.write(invoice.get(KEY_SENDER_ID));
            writer.write(";");
            writer.write(invoice.get(KEY_REFERENCE_NUMBER));
            writer.write(";");
            writer.write(invoice.get(KEY_DOCUMENT_DATE));
            writer.write(";");
            writer.write(invoice.get(KEY_DOCUMENT_NUMBER));
            writer.write(";");
            writer.write(invoice.get(KEY_PARTNER_ID));
            writer.write(";");
            writer.write(invoice.get(KEY_BANK_ACCOUNT));
            writer.write(";");
            writer.write(invoice.get(KEY_NAME));
            writer.write(";");
            writer.write(invoice.get(KEY_REFERENCE_NUMBER));
            writer.write(";");
            writer.write(invoice.get(KEY_PATH));
            writer.write(";");
            writer.write(invoice.get(KEY_DOCUMENT_ID));
            writer.write(";");
            writer.write(invoice.get(KEY_FILENAME));
            writer.write(";");
            writer.write(invoice.get(KEY_TOTAL_INVOICE_AMOUNT));
            writer.write(";");
            writer.write(invoice.get(KEY_TOTAL_VAT_AMOUNT));
            writer.write(";");
            writer.write(invoice.get(KEY_TOTAL_NET_LINE_AMOUNT));
            writer.write(";");
            writer.write(invoice.get(KEY_MESSAGE_NUMBER));
            writer.write(";");
            writer.write(invoice.get(KEY_INVOICE_DATE));
            writer.write(";");
            writer.write(invoice.get(KEY_MESSAGE_TYPE));
            writer.write("\n");
        }
        writer.close();
    }

    private static void initializeHeaders() {

        xml2CsvMapper = new Xml2CsvMapper();
        xml2CsvMapper.map(KEY_SENDER_ID, XML_SENDER_ID, 1);
        xml2CsvMapper.map(KEY_REFERENCE_NUMBER, XML_MESSAGE_REFERENCE_NUMBER, 2);
        xml2CsvMapper.map(KEY_DOCUMENT_DATE, XML_INVOICE_DATE, 3);
        xml2CsvMapper.map(KEY_DOCUMENT_NUMBER, XML_DOCUMENT_NUMBER, 4);
        xml2CsvMapper.map(KEY_PARTNER_ID, XML_PARTNER_ID, 5);
        xml2CsvMapper.map(KEY_BANK_ACCOUNT, XML_BANK_ACCOUNT, 6);
        xml2CsvMapper.map(KEY_NAME, XML_NAME, 7);
        xml2CsvMapper.map(KEY_REFERENCE_NUMBER, XML_REFERENCE_NUMBER, 8);
        xml2CsvMapper.map(KEY_PATH, XML_PATH, 9);
        xml2CsvMapper.map(KEY_DOCUMENT_ID, XML_DOCUMENT_ID, 10);
        xml2CsvMapper.map(KEY_FILENAME, XML_FILENAME, 11);
        xml2CsvMapper.map(KEY_TOTAL_INVOICE_AMOUNT, XML_INVOICE_AMOUNT, 12);
        xml2CsvMapper.map(KEY_TOTAL_VAT_AMOUNT, XML_TOTAL_VATAMOUNT, 13);
        xml2CsvMapper.map(KEY_TOTAL_NET_LINE_AMOUNT, XML_TOTAL_NET_LINE_AMOUNT, 14);
        xml2CsvMapper.map(KEY_MESSAGE_NUMBER, XML_MESSAGE_NUMBER, 15);
        xml2CsvMapper.map(KEY_INVOICE_DATE, XML_DOCUMENT_DATE, 16);
        xml2CsvMapper.map(KEY_MESSAGE_TYPE, XML_MESSAGE_TYPE, 17);

    }

    private static String getValue(File fXmlFile, String key) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        Element root = doc.getDocumentElement();
        XPathFactory xpf = XPathFactory.newInstance();
        XPath path = xpf.newXPath();
        String expression = xml2CsvMapper.getXMLPath(key);
        String str = (String) path.evaluate(expression, root);
        return str.trim();
    }
}

