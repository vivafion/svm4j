package com.svm4j.rs.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "result")
@XmlType(name = "Result", propOrder = {"status", "modelId"})
public class ResultConverter {
	
	public enum Status {PASSED, FAILED}
	
	private final Long modelId;
	private final Status status;
	
	public ResultConverter() {
		this.status = null;
		this.modelId = null;
	}

	public ResultConverter(Status status, Long modelId) {
		this.status = status;
		this.modelId = modelId;
	}

	@XmlElement
	public Long getModelId() {
		return modelId;
	}

	@XmlElement
	public Status getStatus() {
		return status;
	}
	
}
