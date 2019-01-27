package br.com.testes;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.testes.dto.ClassWithList;
import br.com.testes.dto.SimpleClass;

public class MainTests {

	
	@Test
	public void simpleXMLToClass() throws JsonParseException, JsonMappingException, IOException {
		
		String xml = "<simpleObject>"
						+ "<a>a</a>"
						+ "<b>b</b>"
					+ "</simpleObject>";
		
		
		
		SimpleClass expectedSimpleObject = new SimpleClass();
		expectedSimpleObject.setA("a");
		expectedSimpleObject.setB("b");
		
		SimpleClass createdSimpleObject = Converter.convertXMLToSimpleClass(xml, "simpleObject");
		
		assertEquals(expectedSimpleObject.getA(), createdSimpleObject.getA());
		assertEquals(expectedSimpleObject.getB(), createdSimpleObject.getB());

	}

	@Test
	public void nestedXMLToClass() throws JsonParseException, JsonMappingException, IOException {
		
		String xml = "<outerObject>"
						+ "<simpleObject>"
							+ "<a>a</a>"
							+ "<b>b</b>"
						+ "</simpleObject>"
					+ "</outerObject>";

		SimpleClass expectedSimpleObject = new SimpleClass();
		expectedSimpleObject.setA("a");
		expectedSimpleObject.setB("b");
		
		SimpleClass createdSimpleObject = Converter.convertXMLToSimpleClass(xml, "simpleObject");
		
		assertEquals(expectedSimpleObject.getA(), createdSimpleObject.getA());
		assertEquals(expectedSimpleObject.getB(), createdSimpleObject.getB());

		String xml2 = "<outerObject1>"
						+ "<outerObject2>"
							+ "<simpleObject>"
								+ "<a>a</a>"
								+ "<b>b</b>"
							+ "</simpleObject>"
						+ "<outerObject2>"
					+ "</outerObject1>";
	
		SimpleClass expectedSimpleObject2 = new SimpleClass();
		expectedSimpleObject2.setA("a");
		expectedSimpleObject2.setB("b");
		
		SimpleClass createdSimpleObject2 = Converter.convertXMLToSimpleClass(xml2, "simpleObject");
		
		assertEquals(expectedSimpleObject2.getA(), createdSimpleObject2.getA());
		assertEquals(expectedSimpleObject2.getB(), createdSimpleObject2.getB());
		
	}
	
	@Test
	public void xmlToClassWithList() throws IOException {
		
		String xml = "<object>"
						+ "<value>value</value>"
						+ "<items>"
							+ "<item><a>a1</a><b>b1</b></item>"
							+ "<item><a>a2</a><b>b2</b></item>"
						+ "</items>"
					+ "</object>";
		
		ClassWithList actualClassWithList = Converter.convertXMLToClassWithList(xml, "object");
		
		assertEquals("value", actualClassWithList.getValue());
		assertEquals("a1", actualClassWithList.getListItems().get(0).getA());
		assertEquals("b1", actualClassWithList.getListItems().get(0).getB());
		assertEquals("a2", actualClassWithList.getListItems().get(1).getA());
		assertEquals("b2", actualClassWithList.getListItems().get(1).getB());
	}
	
	@Test
	public void nestedXmlToClassWithList() throws IOException {
		
		String xml = "<outer1>"
						+ "<outer2>"
							+ "<object>"
								+ "<value>value</value>"
								+ "<items>"
									+ "<item><a>a1</a><b>b1</b></item>"
									+ "<item><a>a2</a><b>b2</b></item>"
								+ "</items>"
							+ "</object>"
						+ "</outer2>"
					+ "</outer1>";
		
		ClassWithList actualClassWithList = Converter.convertXMLToClassWithList(xml, "object");
		
		assertEquals("value", actualClassWithList.getValue());
		assertEquals("a1", actualClassWithList.getListItems().get(0).getA());
		assertEquals("b1", actualClassWithList.getListItems().get(0).getB());
		assertEquals("a2", actualClassWithList.getListItems().get(1).getA());
		assertEquals("b2", actualClassWithList.getListItems().get(1).getB());
	}

	@Test
	public void nestedXmlToClassWithListAndUnknownProperties() throws IOException {
		
		String xml = "<outer1>"
						+ "<outer2>"
							+ "<object>"
								+ "<value>value</value>"
								+ "<unknownProperty>unknown</unknownProperty>"
								+ "<items>"
									+ "<item><a>a1</a><b>b1</b></item>"
									+ "<item><a>a2</a><b>b2</b></item>"
								+ "</items>"
							+ "</object>"
						+ "</outer2>"
					+ "</outer1>";
		
		ClassWithList actualClassWithList = Converter.convertXMLToClassWithList(xml, "object");
		
		assertEquals("value", actualClassWithList.getValue());
		assertEquals("a1", actualClassWithList.getListItems().get(0).getA());
		assertEquals("b1", actualClassWithList.getListItems().get(0).getB());
		assertEquals("a2", actualClassWithList.getListItems().get(1).getA());
		assertEquals("b2", actualClassWithList.getListItems().get(1).getB());
	}
	
}
