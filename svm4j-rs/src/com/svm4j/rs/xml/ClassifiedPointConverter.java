package com.svm4j.rs.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="point")
@XmlType(name = "Point", propOrder = {"x", "y"})
public class ClassifiedPointConverter {

	private String x;
	private double y;
	
	ClassifiedPointConverter() {	
	}

	public ClassifiedPointConverter(String x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@XmlElement
	public String getX() {
		return x;
	}

	@XmlElement
	public double getY() {
		return y;
	}

	public void setX(String x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
