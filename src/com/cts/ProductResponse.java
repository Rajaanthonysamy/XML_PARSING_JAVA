package com.cts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ProductResponse {

	public static void main(String[] args) {

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities
			// (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File("sample.xml"));

			doc.getDocumentElement().normalize();

			System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());

			// get <Data Area>
			NodeList list = doc.getElementsByTagName("DataArea");
			System.out.println(list.getLength());

			for (int temp = 0; temp < list.getLength(); temp++) {

				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					// get text
					String BusinessNumber = element.getElementsByTagName("BusinessNumber").item(0).getTextContent();
					String BusinessName = element.getElementsByTagName("BusinessName").item(0).getTextContent();
					String AddressLines = element.getElementsByTagName("AddressLines").item(0).getTextContent();

					System.out.println("Current Element : \t\t" + node.getNodeName());
					System.out.println("BusinessIdentificationNumber :\t " + BusinessNumber);
					System.out.println("Business Name : \t\t" + BusinessName);
					System.out.println("AddressLines : \t" + AddressLines);

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

}