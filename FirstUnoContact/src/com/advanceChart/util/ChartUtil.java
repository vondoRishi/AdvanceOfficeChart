package com.advanceChart.util;

import java.awt.Color;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.RectangleEdge;

import com.advanceChart.configureSteps.CommonConfigBean;
import com.advanceChart.util.charts.BoxplotChart;
import com.advanceChart.util.charts.HistogramChart;
import com.advanceChart.util.charts.VennDiagramChart;

public class ChartUtil {

	private static final int HISTOGRAM = 0;
	private static final int BOXPLOT = 1;
	private static final int VennDiagram = 2;

	public static void plot(CommonConfigBean pCommonConfigBean) {
		switch (pCommonConfigBean.getSelectedChartIndex()) {
		case HISTOGRAM:
				new HistogramChart(pCommonConfigBean);
			break;
			
		case BOXPLOT:
				new BoxplotChart(pCommonConfigBean);
			break;
			
		case VennDiagram:
			new VennDiagramChart(pCommonConfigBean);
		break;

		default:
			break;
		}
		
	}
	
	public static void main(String[] args) {
		double[] value = new double[100];
	       Random generator = new Random();
	       for (int i=0; i < 100; i++) 
	       value[i] = generator.nextDouble();
	       
	       double[] value1 = new double[100];
	      
	       for (int i=0; i < 100; i++) 
	       value1[i] = 1+generator.nextDouble();
	       
	       int number = 10;
	       HistogramDataset dataset = new HistogramDataset();
	       dataset.setType(HistogramType.FREQUENCY);
	       dataset.addSeries("Histogram",value,number);
	       dataset.addSeries("Histogram1",value1,number);
	       String plotTitle = "Histogram"; 
	       String xaxis = "number";
	       String yaxis = "value"; 
	       PlotOrientation orientation = PlotOrientation.VERTICAL; 
	       boolean showLegend = true; 
	       boolean toolTips = false;
	       boolean urls = false; 
	       JFreeChart chart = ChartFactory.createHistogram( plotTitle, xaxis, yaxis, 
	                dataset, orientation, showLegend, toolTips, urls);
	       
	       LegendTitle legend = chart.getLegend();
	       legend.setPosition(RectangleEdge.RIGHT);

	       chart.setBackgroundPaint(Color.white);
	       
	       Plot plot = chart.getPlot();
	       plot.setBackgroundPaint(Color.white);
	       chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
	       chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
	       ChartFrame frame = new ChartFrame("First", chart);
	       frame.pack();
	       frame.setVisible(true);
	}

}
