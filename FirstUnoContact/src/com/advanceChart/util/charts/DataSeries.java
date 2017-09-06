package com.advanceChart.util.charts;

public class DataSeries {
	private String mDataSeriesName = null;
	private double[] mValues = null;
	private String[] mTextValues = null;
	
	public String[] getTextValues() {
		return mTextValues;
	}
	public void setTextValues(String[] mTextValues) {
		this.mTextValues = mTextValues;
	}
	public String getDataSeriesName() {
		return mDataSeriesName;
	}
	public void setDataSeriesName(String mDataSeriesName) {
		this.mDataSeriesName = mDataSeriesName;
	}
	public double[] getValues() {
		return mValues;
	}
	public void setValues(double[] values) {
		this.mValues = values;
	}

}
