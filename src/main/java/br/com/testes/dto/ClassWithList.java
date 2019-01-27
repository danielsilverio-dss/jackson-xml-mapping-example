package br.com.testes.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonIgnoreProperties( ignoreUnknown=true )
public class ClassWithList {
	
	private String value;
	
	@JacksonXmlElementWrapper( localName="items" )
	//@JacksonXmlProperty( localName="item" )
	private List<ListItem> listItems;

	public ClassWithList() {}

	public ClassWithList(String value, List<ListItem> listItems) {
		this.value = value;
		this.listItems = listItems;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ListItem> getListItems() {
		return listItems;
	}

	public void setListItems(List<ListItem> listItems) {
		this.listItems = listItems;
	}

}
