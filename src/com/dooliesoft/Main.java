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
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    //public static final String FOLDER = "C:\\Users\\c158492\\Desktop\\CREDIT NOTE\\ZHUL\\Nouveau Dossier";
    public static final String FOLDER = "C:\\Users\\fabrice\\Downloads\\Lot2";
    //public static final String FOLDER = "C:\\Users\\fabrice\\Downloads\\Lot2\\New folder";
    public static final LinkedHashMap<String, String> column_to_xml_xpath = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        loadParameters();

        ArrayList<Invoice> invoices = new ArrayList<>();

        File folder = new File(FOLDER);
        for (File fileEntry : folder.listFiles()) {
            try {
                if (!fileEntry.isDirectory()) {
                    //System.out.println(fileEntry.getAbsoluteFile());
                    File fXmlFile = fileEntry.getAbsoluteFile();

                    Invoice draftInvoice = new Invoice();
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);
                    Element root = doc.getDocumentElement();
                    XPathFactory xpf = XPathFactory.newInstance();
                    XPath path = xpf.newXPath();

                    for (Map.Entry<String, String> entry : column_to_xml_xpath.entrySet()) {
                        String xpath_evaluated = getValue(fXmlFile, entry.getValue(), path, root);
                        draftInvoice.set(entry.getKey(), xpath_evaluated);
                    }
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
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.csv"));

        String toWrite = "";
        for (String key : column_to_xml_xpath.keySet()) {
            toWrite += key + ";";
        }
        writer.write(toWrite.substring(0, toWrite.length() - 1) + "\n");

        for (Invoice invoice : invoices) {
            toWrite = "";
            for (String key : column_to_xml_xpath.keySet()) {
                toWrite += invoice.get(key) + ";";
            }
            writer.write(toWrite.substring(0, toWrite.length() - 1) + "\n");
        }
        writer.close();
    }

    private static void loadParameters() {
        String csvFile = "parameter.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int i = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] parameter = line.split(cvsSplitBy);
                if (i != 0) {
                    column_to_xml_xpath.put(parameter[0], parameter[1]);
                    System.out.println(parameter[0] + " , " + parameter[1]);
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getValue(File fXmlFile, String key, XPath path, Element root) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        String expression = key;
        String str = (String) path.evaluate(expression, root);
        return str.trim();
    }
}