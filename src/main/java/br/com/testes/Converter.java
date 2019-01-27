package br.com.testes;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.testes.dto.ClassWithList;
import br.com.testes.dto.SimpleClass;

public class Converter {

	public static SimpleClass convertXMLToSimpleClass(String xml, String objectRootElement) throws JsonParseException, JsonMappingException, IOException {
		XMLStreamReader xmlStreamReader = getXMLStreamReaderForFile(xml);
		reachElementByLocalName(xmlStreamReader, objectRootElement);
		SimpleClass simpleClass = new XmlMapper().readValue(xmlStreamReader, SimpleClass.class);
		return simpleClass;
	}
	
	public static ClassWithList convertXMLToClassWithList(String xml, String objectRootElement) throws IOException {
		XMLStreamReader xmlStreamReader = getXMLStreamReaderForFile(xml);
		reachElementByLocalName(xmlStreamReader, objectRootElement);
		ClassWithList classWithList = new XmlMapper().readValue(xmlStreamReader, ClassWithList.class);
		return classWithList;
	}

	private static void reachElementByLocalName(XMLStreamReader xmlStreamReader, String objectRootElement) {
		try {
			while( xmlStreamReader.hasNext() ) {
				xmlStreamReader.next();
				if( xmlStreamReader.getEventType() == XMLStreamReader.START_ELEMENT ) {
					if( xmlStreamReader.getLocalName() == objectRootElement ) {
						break;
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private static XMLStreamReader getXMLStreamReaderForFile(String xml) {
		
		Reader reader = new StringReader(xml);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader = null;
		try {
			xmlStreamReader = factory.createXMLStreamReader(reader);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return xmlStreamReader;
	}
	
}
