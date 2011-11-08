package com.svm4j.rs.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.svm4j.Hyperplane;

@PersistenceCapable
public class Model {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private Double b;
	
	@Persistent
	private String w;

	public Model() {	
	}
	
	public Model(String w, Double b) {
		super();
		this.b = b;
		this.w = w;
	}
	
	/**
	 * Build a model object from the Hyperplane object.
	 * 
	 * @param hyperplane
	 * @return
	 */
	public static Model fromHyperplane(Hyperplane hyperplane) {
		return new Model(buildWString(hyperplane.getW()), hyperplane.getB());
	}
	
	/**
	 * Create a Hyperplane object from the model.
	 * @return
	 */
	public Hyperplane toHyperplane() {
		return new Hyperplane(Converter.buildArray(this.w), this.b);
	}
	
	/**
	 * Combine the w vector into a single string for serialization.
	 * 
	 * @param w
	 * @return
	 */
	private static String buildWString(double[] w) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < w.length; i++) {
			sb.append(w[i]);
			if (i < w.length - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getB() {
		return b;
	}

	public void setB(Double b) {
		this.b = b;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}
	
}
