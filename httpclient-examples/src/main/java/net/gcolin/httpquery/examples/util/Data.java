package net.gcolin.httpquery.examples.util;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public @XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Data{
	private List<App> app;

	public List<App> getApp() {
		return app;
	}
}