package com.vinson.xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class FragmentContentHandler extends DefaultHandler {
	String xPath = "/";
	XMLReader xmlReader;
	FragmentContentHandler parent;
	StringBuilder characters = new StringBuilder();
	Map<String, Integer> elementNameCount = new HashMap<String, Integer>();

	public FragmentContentHandler(XMLReader xmlReader) {
		this.xmlReader = xmlReader;
	}

	private FragmentContentHandler(String xPath, XMLReader xmlReader, FragmentContentHandler parent) {
		this(xmlReader);
		this.xPath = xPath;
		this.parent = parent;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		Integer count = elementNameCount.get(qName);
		if (null == count) {
			count = 1;
		} else {
			count++;
		}
		elementNameCount.put(qName, count);
		String childXPath = xPath + "/" + qName + "[" + count + "]";

		int attsLength = atts.getLength();
		StringBuilder sb = new StringBuilder();
		sb.append(childXPath);
		if (attsLength > 0) {
			sb.append('[');
		}
		for (int x = 0; x < attsLength; x++) {
			sb.append('@').append(atts.getQName(x)).append('=').append(atts.getValue(x));
			if (x != attsLength - 1) {
				sb.append(',');
			}
		}
		if (attsLength > 0) {
			sb.append(']');
		}
		FragmentContentHandler child = new FragmentContentHandler(sb.toString(), xmlReader, this);
		xmlReader.setContentHandler(child);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String value = characters.toString().trim();
		if (value.length() > 0) {
			System.out.println(xPath + "='" + characters.toString() + "'");
		} else {
			System.out.println(xPath + "= null");
		}
		xmlReader.setContentHandler(parent);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		characters.append(ch, start, length);
	}
	
	public void endPrefixMapping(String prefix) throws SAXException {
		System.out.println("prefix===>" + prefix);
	}
	
	
	public static void main(String[] args) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		xr.setContentHandler(new FragmentContentHandler(xr));
		
		xr.parse(new InputSource(FragmentContentHandler.class.getClassLoader().getResourceAsStream("input.xml")));
		
		
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(FragmentContentHandler.class.getClassLoader().getResourceAsStream("input.xml")))) {
//			String line = reader.readLine();
//			while (line != null) {
//				// read next line
//				line = reader.readLine();
//				System.out.println(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
