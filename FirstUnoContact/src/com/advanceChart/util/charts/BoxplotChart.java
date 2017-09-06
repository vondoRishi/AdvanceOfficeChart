package com.advanceChart.util.charts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.advanceChart.configureSteps.CommonConfigBean;
import com.advanceChart.util.OfficeUtil;

public class BoxplotChart {

	public BoxplotChart(CommonConfigBean pCommonConfigBean) {
		DefaultBoxAndWhiskerCategoryDataset dataset = createSampleDataset(pCommonConfigBean);
		
		String lTitle = pCommonConfigBean.getTitle(); 
		String xaxis = pCommonConfigBean.getXaxisLabel();
		String yaxis = pCommonConfigBean.getYaxisLabel();
		int legendPosition = pCommonConfigBean.getLegendPosition();
		boolean showLegend = (legendPosition > 0);
		
		final JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(lTitle,
				yaxis, xaxis, dataset, showLegend);
		
		 TextTitle subtitle1 = new TextTitle(pCommonConfigBean.getSubTitle());
	       chart.addSubtitle(subtitle1);
	       
	       if(legendPosition>0){
		       LegendTitle legend = chart.getLegend();
		       if(legendPosition==pCommonConfigBean.LEGEND_RIGHT){
		    	   legend.setPosition(RectangleEdge.RIGHT);
		       }else if(legendPosition==pCommonConfigBean.LEGEND_LEFT){
		    	   legend.setPosition(RectangleEdge.LEFT);
		       }else if(legendPosition==pCommonConfigBean.LEGEND_TOP){
		    	   legend.setPosition(RectangleEdge.TOP);
		       }else if(legendPosition==pCommonConfigBean.LEGEND_BOTTOM){
		    	   legend.setPosition(RectangleEdge.BOTTOM);
		       }
	       }
		
		BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        CategoryPlot xyplot = chart.getCategoryPlot();
        xyplot.setRenderer(renderer);
       
        renderer.setMeanVisible(false);

        xyplot.setRenderer(renderer);
        CategoryAxis domainAxis = xyplot.getDomainAxis();
        domainAxis.setLowerMargin(0.03);
        domainAxis.setUpperMargin(0.03);
        domainAxis.setCategoryMargin(0.25);

        xyplot.setBackgroundPaint(Color.white);
       /* xyplot.setDomainGridlinePaint(Color.black);
        xyplot.setDomainGridlinesVisible(true);
        xyplot.setRangeGridlinePaint(Color.black);*/
        
        
		ChartFrame frame = new ChartFrame(
				pCommonConfigBean.getSelectedRangeAsText(), chart);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	private DefaultBoxAndWhiskerCategoryDataset createSampleDataset(
			CommonConfigBean pCommonConfigBean) {
//		mCommonConfigBean.setNumericSeries(OfficeUtil.getNumericSeries(mCommonConfigBean.isFirstRowLabel()));
		//List<DataSeries> listDataSeries = pCommonConfigBean.getNumericValues();
		List<DataSeries> listDataSeries = OfficeUtil.getTextSeries(pCommonConfigBean.isFirstRowLabel());

		final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		for (DataSeries dataSeries : listDataSeries) {
			final List<Double> list = new ArrayList<Double>();
			String[] values = dataSeries.getTextValues();
			for (String d : values) {
				
				try {
					list.add(Double.parseDouble(d));
				} catch (NumberFormatException e) {
					System.out.println(d);
					
				}
			}
			dataset.add(list, dataSeries.getDataSeriesName(), "");
		}
		return dataset;

	}

}
