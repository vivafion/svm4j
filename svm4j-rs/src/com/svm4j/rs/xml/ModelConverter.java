package com.svm4j.rs.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.svm4j.rs.entity.Model;

@XmlRootElement(name = "model")
@XmlType(name = "Model", propOrder = {"id", "w", "b"})
public class ModelConverter {
	private final Long id;
	private final Double b;
	private final String w;
	
	public ModelConverter() {
		id = null;
		b = null;
		w = null;
	}

	public ModelConverter(Long id, Double b, String w) {
		super();
		this.id = id;
		this.b = b;
		this.w = w;
	}

	@XmlElement
	public Long getId() {
		return id;
	}

	@XmlElement
	public Double getB() {
		return b;
	}

	@XmlElement
	public String getW() {
		return w;
	}
	
	public static ModelConverter fromModel(Model model) {
		return new ModelConverter(model.getId(), model.getB(), model.getW());
	}
	
}
