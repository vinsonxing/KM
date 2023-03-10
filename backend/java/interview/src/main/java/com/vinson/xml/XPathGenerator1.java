package com.vinson.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XPathGenerator1 {

	final Map<String, IncrementableInteger> startedQNames = new HashMap<>();
	final Map<String, String> generatedXPaths = new HashMap<String, String>(); 
	final Pattern prefix;
	
	public XPathGenerator1(String prefix) {
		if (prefix == null || prefix.trim().length() == 0) {
			this.prefix = null;
		} else {
			this.prefix = Pattern.compile(prefix);
		}
	}
	
	public XPathGenerator1() {
		this("");
	}
	
	public Map<String, String> generate(InputStream stream) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(new XPathGenerator1.FragmentContentHandler(xr));
			xr.parse(new InputSource(stream));
		} catch (Exception e) {
			System.out.println(String.format("Failed to generate xpath for given document: %s", e));
		}
		return generatedXPaths;
	}
	
	public Map<String, String> generate(String fileName) {
		return generate(FragmentContentHandler.class.getClassLoader().getResourceAsStream(fileName));
	}
	
	private void increase() {
		startedQNames.values().stream().forEach(IncrementableInteger::increase);
	}
	
	private static class IncrementableInteger {
		int value;
		private IncrementableInteger(int v) {
			value = v;
		}
		public void increase() {
			value ++;
		}
		public static IncrementableInteger of(int v) {
			return new IncrementableInteger(v);
		}
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	private class FragmentContentHandler extends DefaultHandler {
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
			startedQNames.put(qName, IncrementableInteger.of(0));
			Integer count = elementNameCount.get(qName);
			if (null == count) {
				count = 0;
			} else {
				count++;
			}
			elementNameCount.put(qName, count);
			String childXPath = xPath + "/" + qName + (count == 0 ? "" : "[" + count + "]");

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
			increase();
			String value = characters.toString().trim();
			if (prefix == null || prefix.matcher(xPath).matches()) {
				if (value.length() > 0) {
					generatedXPaths.put(xPath, characters.toString());
				} else {
					if (startedQNames.get(qName).value == 1) {
						generatedXPaths.put(xPath, null);
					}
				}
			}
			startedQNames.remove(qName);
			xmlReader.setContentHandler(parent);
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			characters.append(ch, start, length);
		}
	}

	public static void main(String[] args) throws Exception {
		XPathGenerator1 generator = new XPathGenerator1(".*\\/edit-config.*\\/config.*");
		generator.generate("input.xml").entrySet().stream().forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));;
	}
}
