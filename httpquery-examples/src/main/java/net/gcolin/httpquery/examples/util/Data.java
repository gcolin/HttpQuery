package net.gcolin.httpquery.examples.util;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<App> app;

	public List<App> getApp() {
		return app;
	}

	public void setApp(List<App> app) {
		this.app = app;
	}
	
	
}