package com.svm4j;

/**
 * Represents hyperplane in a multidimensional space.  The points on the hyperplane satisfy:
 * w . x + b = 0
 *  
 * @author Tommer
 *
 */
public class Hyperplane 
{
	double[] w; // The vector perpendicular to the hyperplane.
	double b; // The offset parameter
	
	public Hyperplane() 
	{
		super();
	}

	public Hyperplane(double[] w, double b)
	{
		this.w = w;
		this.b = b;
	}

	public double[] getW() 
	{
		return w;
	}

	public void setW(double[] w) 
	{
		this.w = w;
	}

	public double getB() 
	{
		return b;
	}

	public void setB(double b) 
	{
		this.b = b;
	}	
}
