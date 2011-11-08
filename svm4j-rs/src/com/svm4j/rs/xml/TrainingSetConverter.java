package com.svm4j.rs.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="trainingset")
@XmlType(name = "TrainingSet", propOrder = {"points"})
public class TrainingSetConverter {
	private List<ClassifiedPointConverter> points;
	
	TrainingSetConverter() {
	}

	public TrainingSetConverter(List<ClassifiedPointConverter> points) {
		super();
		this.points = points;
	}

	@XmlElement(name="point")
	public List<ClassifiedPointConverter> getPoints() {
		return points;
	}

	public void setPoints(List<ClassifiedPointConverter> points) {
		this.points = points;
	}
	
	/**
	 * Convert a list of ClassifiedPoint objects to a form accepted by the 
	 * svm package.
	 * 
	 * @param points
	 * @return
	 */
	public Object[] toXYLists() {
		List<double[]> x = new ArrayList<double[]>();
		List<Double> y = new ArrayList<Double>();
		for (ClassifiedPointConverter point : points) {
			y.add(point.getY());
			String[] xStrArr = point.getX().split(",\\s+");
			double[] xDoubleArr = new double[xStrArr.length];
			for (int i = 0; i < xStrArr.length; i++) {
				xDoubleArr[i] = Double.parseDouble(xStrArr[i]);
			}
			x.add(xDoubleArr);
		}
		return new Object[] { x, y };
	}
	
}
