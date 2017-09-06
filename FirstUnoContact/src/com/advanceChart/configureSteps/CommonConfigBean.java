package com.advanceChart.configureSteps;

import java.util.List;

import com.advanceChart.util.charts.DataSeries;


public class CommonConfigBean {

	public static final int LEGEND_NONE = 0;
	public static final int LEGEND_LEFT = 1;
	public static final int LEGEND_RIGHT = 2;
	public static final int LEGEND_TOP = 3;
	public static final int LEGEND_BOTTOM = 4;
	private String mSelectedRangeAsText;
	private boolean mDataInRows;
	private boolean mFirstRowLabel=true;
	private String[] mChartTypes = new String[] {"Histogram", "Boxplot", "Venn Diagram"};
	private int mSelectedChartIndex = 0;
	private List<DataSeries> mNumericSeries;
	private String mTitle;
	private String mSubTitle;
	private String mXaxisLabel;
	private String mYaxisLabel;
	private int mLegendPosition;

	public void setSelectedRangeAsText(String selRangeText) {
		mSelectedRangeAsText = selRangeText;
	}

	public String getSelectedRangeAsText() {
		return mSelectedRangeAsText;
	}

	public void setDataInRows(boolean pDataInRows) {
		mDataInRows=pDataInRows;		
	}

	public void setFirstRowLabel(boolean pFirstRowLabel) {
		mFirstRowLabel = pFirstRowLabel;
	}

	public boolean isDataInRows() {
		return mDataInRows;
	}

	public boolean isFirstRowLabel() {
		return mFirstRowLabel;
	}

	public String[] getChartAvailableTypes() {
		return mChartTypes;
	}

	public void setSelectedChartType(int pIndex) {
		mSelectedChartIndex = pIndex;
	}

	public int getSelectedChartIndex() {
		return mSelectedChartIndex;
	}

	public int getDefaultChartType() {
		return 0;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public List<DataSeries> getNumericValues() {
		return mNumericSeries;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public void setNumericSeries(List<DataSeries> pNumericSeries) {
		mNumericSeries = pNumericSeries;
	}

	public void setTitle(String pTitle) {
		mTitle=pTitle;
	}

	public void setSubTitle(String pSubTitle) {
		mSubTitle=pSubTitle;
	}

	public void setYaxisLabel(String pYaxisLabel) {
		mYaxisLabel = pYaxisLabel;
	}

	public void setXaxisLabel(String pXaxisLabel) {
		mXaxisLabel = pXaxisLabel;
	}

	/**
	 *  One of LEGEND_NONE, LEGEND_LEFT ,LEGEND_RIGHT ,LEGEND_TOP ,	LEGEND_BOTTOM 
	 * @param pLegendPosition
	 */
	public void setLegendChoice(int pLegendPosition) {
		mLegendPosition = pLegendPosition;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getSubTitle() {
		return mSubTitle==null?"":mSubTitle;
	}

	public String getXaxisLabel() {
		return mXaxisLabel;
	}

	public String getYaxisLabel() {
		return mYaxisLabel;
	}

	public int getLegendPosition() {
		return mLegendPosition;
	}

}
