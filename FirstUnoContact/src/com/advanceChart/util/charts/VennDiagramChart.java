package com.advanceChart.util.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYAnnotationEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleInsets;

import com.advanceChart.configureSteps.CommonConfigBean;
import com.advanceChart.util.CoustomChartPanel;
import com.advanceChart.util.CoustomXYTextAnnotation;
import com.advanceChart.util.OfficeUtil;

public class VennDiagramChart {

	private static final Font LABEL_FONT = new Font("Jokerman", Font.PLAIN, 15);

	public VennDiagramChart(CommonConfigBean pCommonConfigBean) {
		String lTitle = pCommonConfigBean.getTitle();
		List<DataSeries> values = OfficeUtil.getTextSeries(pCommonConfigBean
				.isFirstRowLabel());

		switch (values.size()) {
		case 2:
			twoSet(values);
			break;

		case 3:
			threeSet(values);
			break;

		case 4:
			fourSet(values);
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		fourSet(null);
	}

	private static void twoSet(List<DataSeries> values) {

		final JFreeChart chart = ChartFactory.createXYAreaChart(null, null,
				null, null);

		final CoustomChartPanel chartPanel = new CoustomChartPanel(chart);
		drawTwoVenn(chart);

		Set<String> setA = getSet(values.get(0));
		Set<String> setB = getSet(values.get(1));

		addTextAnnotation(chart, setA, 0.2, 0.9, values.get(0)
				.getDataSeriesName() + " ", Color.BLUE, values.get(0)
				.getDataSeriesName(), chartPanel);

		addTextAnnotation(chart, setB, 0.7, 0.9, values.get(1)
				.getDataSeriesName() + " ", Color.RED, values.get(1)
				.getDataSeriesName(), chartPanel);

		addTextAnnotation(chart, difference(setA, setB), 0.25, 0.5, "",
				Color.BLACK, "Unique to " + values.get(0).getDataSeriesName(),
				chartPanel);
		addTextAnnotation(chart, difference(setB, setA), 0.85, 0.5, "",
				Color.BLACK, "Unique to " + values.get(1).getDataSeriesName(),
				chartPanel);
		addTextAnnotation(chart, intersection(setA, setB), 0.55, 0.5, "",
				Color.BLACK, "Common to " + values.get(0).getDataSeriesName()
						+ " and " + values.get(1).getDataSeriesName(),
				chartPanel);

		chart.getXYPlot().setBackgroundPaint(Color.white);

		displayChart(chartPanel);

	}

	private static void threeSet(List<DataSeries> values) {
		final JFreeChart chart = ChartFactory.createXYAreaChart(null, null,
				null, null);
		final CoustomChartPanel chartPanel = new CoustomChartPanel(chart);
		drawThreeVenn(chart);

		Set<String> setA = getSet(values.get(0));
		Set<String> setB = getSet(values.get(1));
		Set<String> setC = getSet(values.get(2));

		
		String A = values.get(0).getDataSeriesName();
		
		String B = values.get(1).getDataSeriesName();
		
		String C = values.get(2).getDataSeriesName();
		
		addTextAnnotation(chart, setA , 0.2, 1.0,
					 A+" ", Color.BLUE,A,chartPanel);
		addTextAnnotation(chart, setB , 0.8, 1.0,
				  B+" ", Color.RED,B,chartPanel);
		addTextAnnotation(chart, setC , 0.8, 0.05,
		  C+" ", Color.green,C,chartPanel);
		  
		  //A-(B U C) 
		  addTextAnnotation(chart, difference(setA, union(setB,
		  setC)) , 0.26, 0.7, " ", Color.BLACK,"Unique to "+A,chartPanel);
		 
		 //B-(A U C) 
		  addTextAnnotation(chart, difference(setB, union(setA,
		  setC)) , 0.85, 0.7, " ", Color.BLACK,"Unique to "+B,chartPanel);
		 
		 //C-(A U B) 
		  addTextAnnotation(chart, difference(setC, union(setA,
		  setB)) , 0.55, 0.2, " ", Color.BLACK,"Unique to "+C,chartPanel);
		 
		 //A*B*C 
		  Set<String> A_B_C = intersection(setA, intersection(setB,
		  setC)); 
		  addTextAnnotation(chart, A_B_C , 0.55, 0.55, " ",
		  Color.BLACK,"Common to "+A+","+B+" and "+C,chartPanel);
		  
		  //A*B - A*B*C 
		  addTextAnnotation(chart, difference(intersection(setA,
		  setB),A_B_C) , 0.55, 0.7, " ", Color.BLACK,"Only common to "+A+" and "+B,chartPanel);
		  
		  //A*C - A*B*C 
		  addTextAnnotation(chart, difference(intersection(setA,
		  setC),A_B_C) , 0.4, 0.4, " ", Color.BLACK,"Only common to "+A+" and "+C,chartPanel);
		  
		 //B*C - A*B*C 
		  addTextAnnotation(chart, difference(intersection(setC,
		  setB),A_B_C) , 0.7, 0.4, " ", Color.BLACK,"Only common to "+B+" and "+C,chartPanel);
		 

		chart.getXYPlot().setBackgroundPaint(Color.white);
		displayChart(chartPanel);
	}

	private static void fourSet(List<DataSeries> values) {
		final JFreeChart chart = ChartFactory.createXYAreaChart(null, null,
				null, null);
		final CoustomChartPanel chartPanel = new CoustomChartPanel(chart);
		drawFourVenn(chart);

		Set<String> setA = getSet(values.get(0));
		Set<String> setB = getSet(values.get(1));
		Set<String> setC = getSet(values.get(2));
		Set<String> setD = getSet(values.get(3));

		
		String A = values.get(0).getDataSeriesName();
		String B = values.get(1).getDataSeriesName();
		String C = values.get(2).getDataSeriesName();
		String D = values.get(3).getDataSeriesName();

		addTextAnnotation(chart, setA, 0.1, 0.65, A + " ", Color.BLUE, A,
				chartPanel);

		addTextAnnotation(chart, setB, 0.4, 0.75, B + " ", Color.RED, B,
				chartPanel);

		addTextAnnotation(chart, setC, 0.7, 0.75, C + " ", Color.MAGENTA, C,
				chartPanel);

		addTextAnnotation(chart, setD, 0.95, 0.65, D + " ", Color.GREEN, D,
				chartPanel);
  
		 //A*B*C*D 
		Set<String> A_B_C_D = intersection(intersection(setA, setB),
				intersection(setC, setD));
		addTextAnnotation(chart, A_B_C_D, 0.525, 0.25, "", Color.BLACK,
				"Common to " + A + "," + B + "," + C + " and " + D, chartPanel);
		 
		  //A*B*C - A*B*C*D (common to A,B and C) 
		 Set<String> A_B_C = difference(intersection(intersection(setA, setB), setC), setD);
		 addTextAnnotation(chart, A_B_C , 0.45, 0.35, " ", Color.BLACK,"Only common to " + A + "," + B + " and " + C, chartPanel);
		  
		  //A*B*D - A*B*C*D (common to A,B and D) 
		 Set<String> A_B_D = difference(intersection(intersection(setA, setB), setD), setC);
		 addTextAnnotation(chart, A_B_D , 0.57, 0.16, " ", Color.BLACK,"Only common to " + A + "," + B + " and " + D, chartPanel);
		  
		  //A*C*D - A*B*C*D (common to A,C and D) 
		 Set<String> A_C_D =  difference(intersection(intersection(setA, setC), setD), setB);
		  addTextAnnotation(chart, A_C_D , 0.47, 0.16, " ", Color.BLACK,"Only common to " + A + "," + C + " and " + D, chartPanel);
		 
		  //B*C*D - A*B*C*D (common to B,C and D) 
		  Set<String> B_C_D =  difference(intersection(intersection(setB, setC), setD), setA);
		 addTextAnnotation(chart, B_C_D , 0.6, 0.35, " ", Color.BLACK,"Only common to " + B + "," + C + " and " + D, chartPanel);
		  
		  
		 //A*B - (C U D) (common to A and B) 
		  Set<String> A_B =  difference(intersection(setA, setB),union( setC, setD));
		  addTextAnnotation(chart, A_B , 0.3, 0.47, " ", Color.BLACK,"Only common to " + A + " and " + B, chartPanel);
		  
		  //A*C - (B U D) (common to A and C) 
		  Set<String> A_C =  difference(intersection(setA, setC),union( setB, setD));
		 addTextAnnotation(chart, A_C , 0.375, 0.2, " ", Color.BLACK,"Only common to " + A + " and " + C, chartPanel);
		  
		  //A*D - (B U C) (common to A and D) 
		 Set<String> A_D = difference(intersection(setA, setD),union( setB, setC));
		 addTextAnnotation(chart, A_D , 0.52, 0.12, " ", Color.BLACK,"Only common to " + A + " and " + D, chartPanel);
		  
		  //B*C - (D U A) (common to B and C) 
		 Set<String> B_C = difference(intersection(setB, setC),union( setD, setA));
		  addTextAnnotation(chart, B_C , 0.52, 0.47, " ", Color.BLACK,"Only common to " + B + " and " + C, chartPanel);
		  
		  //B*D - (C U A) (common to B and D) 
		  Set<String> B_D =
		  difference(intersection(setB, setD),union( setC, setA));
		  addTextAnnotation(chart, B_D , 0.65, 0.2, " ", Color.BLACK,"Only common to " + B + " and " + D, chartPanel);
		  
		  //C*D - (A U B) (common to C and D) 
		  Set<String> C_D =
		  difference(intersection(setC, setD),union( setA, setB));
		  addTextAnnotation(chart, C_D , 0.7, 0.47, " ", Color.BLACK,"Only common to " + C + " and " + D, chartPanel);
		  
		 //Unique to A 
		  Set<String> uniqueA = difference(setA,union(union( setB,
		  setC), setD)); addTextAnnotation(chart, uniqueA , 0.2, 0.35, " ",
		  Color.BLACK,"Unique to "+uniqueA, chartPanel);
		  
		  //Unique to B 
		  Set<String> uniqueB = difference(setB,union( setC,union(
		  setA, setD))); addTextAnnotation(chart, uniqueB , 0.33, 0.6, " ",
		  Color.BLACK,"Unique to "+uniqueB, chartPanel);
		  
		  //Unique to C 
		  Set<String> uniqueC = difference(setC,union(union( setB,
		  setA),setD)); addTextAnnotation(chart, uniqueC , 0.7, 0.6, " ",
		  Color.BLACK,"Unique to "+uniqueC, chartPanel);
		  
		  //Unique to D 
		  Set<String> uniqueD = difference( setD,union(union( setA,
		  setB),setC)); addTextAnnotation(chart, uniqueD , 0.8, 0.35, " ",
		  Color.BLACK,"Unique to "+uniqueD, chartPanel);
		 

		 chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().getRangeAxis().setRange(0, 0.8);
		chart.getXYPlot().getDomainAxis().setRange(0, 1);
		displayChart(chartPanel);

	}

	private static void drawFourVenn(JFreeChart chart) {
		// chart.getXYPlot().set
		double width = 0.3;
		double height = 0.7;
		double start_X = 0.2;
		double start_Y = 0.0;

		Ellipse2D.Double oldTest = new Ellipse2D.Double(start_X, start_Y,
				width, height);
		// XYShapeAnnotation oldSet = new XYShapeAnnotation(oldTest,new
		// BasicStroke(4f),Color.BLUE,Color.RED);
		Shape firstSetEllipse = AffineTransform.getRotateInstance(Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		XYShapeAnnotation firstSet = new XYShapeAnnotation(firstSetEllipse,
				new BasicStroke(4f), Color.BLACK, Color.BLUE);
		chart.getXYPlot().addAnnotation(firstSet);

		Shape secondSetEllipse = AffineTransform.getRotateInstance(Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		secondSetEllipse = AffineTransform.getTranslateInstance(start_X - 0.09,
				start_Y + 0.05).createTransformedShape(secondSetEllipse);
		XYShapeAnnotation secondSet = new XYShapeAnnotation(secondSetEllipse,
				new BasicStroke(4f), Color.BLACK, Color.RED);
		chart.getXYPlot().addAnnotation(secondSet);

		Shape tmpSetEllipse = AffineTransform.getRotateInstance(-Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		Shape thirdSetEllipse = AffineTransform.getTranslateInstance(
				start_X + 0.05, start_Y + 0.05).createTransformedShape(
				tmpSetEllipse);
		XYShapeAnnotation fourthSet = new XYShapeAnnotation(thirdSetEllipse,
				new BasicStroke(4f), Color.BLACK, Color.YELLOW);
		chart.getXYPlot().addAnnotation(fourthSet);

		tmpSetEllipse = AffineTransform.getRotateInstance(-Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		Shape fourthSetEllipse = AffineTransform.getTranslateInstance(0.36,
				start_Y).createTransformedShape(tmpSetEllipse);
		XYShapeAnnotation thirdSet = new XYShapeAnnotation(fourthSetEllipse,
				new BasicStroke(4f), Color.BLACK, Color.GREEN);
		chart.getXYPlot().addAnnotation(thirdSet);

		// redraw border
		firstSetEllipse = AffineTransform.getRotateInstance(Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		firstSet = new XYShapeAnnotation(firstSetEllipse, new BasicStroke(4f),
				Color.BLACK);
		chart.getXYPlot().addAnnotation(firstSet);

		secondSetEllipse = AffineTransform.getRotateInstance(Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		secondSetEllipse = AffineTransform.getTranslateInstance(start_X - 0.09,
				start_Y + 0.05).createTransformedShape(secondSetEllipse);
		secondSet = new XYShapeAnnotation(secondSetEllipse,
				new BasicStroke(4f), Color.BLACK);
		chart.getXYPlot().addAnnotation(secondSet);

		tmpSetEllipse = AffineTransform.getRotateInstance(-Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		thirdSetEllipse = AffineTransform.getTranslateInstance(start_X + 0.05,
				start_Y + 0.05).createTransformedShape(tmpSetEllipse);
		fourthSet = new XYShapeAnnotation(thirdSetEllipse, new BasicStroke(4f),
				Color.BLACK);
		chart.getXYPlot().addAnnotation(fourthSet);

		tmpSetEllipse = AffineTransform.getRotateInstance(-Math.PI / 4,
				start_X + width / 2, start_Y + height / 2)
				.createTransformedShape(oldTest);
		fourthSetEllipse = AffineTransform.getTranslateInstance(0.36, start_Y)
				.createTransformedShape(tmpSetEllipse);
		thirdSet = new XYShapeAnnotation(fourthSetEllipse, new BasicStroke(4f),
				Color.BLACK);
		chart.getXYPlot().addAnnotation(thirdSet);
		// System.out.println(firstSet.getBounds2D().getCenterX());

		// chart.getXYPlot().addAnnotation(oldSet);

	}

	private static void drawThreeVenn(final JFreeChart chart) {
		double width = 0.6;
		double start_X = 0.1;
		double start_Y = 0.35;
		XYShapeAnnotation firstSet = new XYShapeAnnotation(
				new Ellipse2D.Double(start_X, start_Y, width, width),
				new BasicStroke(4f), Color.BLUE, Color.BLUE);
		chart.getXYPlot().addAnnotation(firstSet);

		XYShapeAnnotation secondSet = new XYShapeAnnotation(
				new Ellipse2D.Double(start_X + width / 2, start_Y, width, width),
				new BasicStroke(4f), Color.RED, Color.RED);
		chart.getXYPlot().addAnnotation(secondSet);

		XYShapeAnnotation thirdSet = new XYShapeAnnotation(
				new Ellipse2D.Double(start_X + width / 4, start_Y - width / 2,
						width, width), new BasicStroke(4f), Color.GREEN,
				Color.GREEN);
		chart.getXYPlot().addAnnotation(thirdSet);
	}

	private static void displayChart(final CoustomChartPanel chartPanel) {

		XYPlot plot = (XYPlot)chartPanel.getChart().getPlot();
		plot.getRangeAxis().setVisible(false);
		plot.getDomainAxis().setVisible(false);
		plot.setOutlineVisible(false);

		JFrame lJFrame = new JFrame();
		chartPanel.setFrame(lJFrame);
		lJFrame.setContentPane(chartPanel);
		lJFrame.pack();
		lJFrame.setSize(600, 400);
		lJFrame.setLocationRelativeTo(null);
		lJFrame.setVisible(true);

	}

	private static void drawTwoVenn(final JFreeChart chart) {
		double width = 0.6;
		double start_X = 0.1;
		double start_Y = 0.2;

		XYShapeAnnotation firstSet = new XYShapeAnnotation(
				new Ellipse2D.Double(start_X, start_Y, width, width),
				new BasicStroke(4f), Color.BLUE, Color.BLUE);
		chart.getXYPlot().addAnnotation(firstSet);

		XYShapeAnnotation secondSet = new XYShapeAnnotation(
				new Ellipse2D.Double(start_X + width / 2, start_Y, width, width),
				new BasicStroke(4f), Color.RED, Color.RED);
		chart.getXYPlot().addAnnotation(secondSet);
	}

	private static void addTextAnnotation(final JFreeChart chart,
			Set<String> setA, double pX, double pY, String pText, Paint pColor,
			String pSetName, CoustomChartPanel chartPanel) {
		XYTextAnnotation lTA = new XYTextAnnotation(pText
				+ setA.size(), pX, pY);
		lTA.setFont(LABEL_FONT);
		lTA.setPaint(pColor);
		lTA.setURL(pSetName);
		// lTA.setToolTipText(pText+setA.size());
		chart.getXYPlot().addAnnotation(lTA);
		chartPanel.addSet(pSetName, setA);

	}

	private static Set<String> getSet(DataSeries dataSeries) {
		String[] values = dataSeries.getTextValues();
		Set<String> lSet = new HashSet<String>();
		for (String string : values) {
			if (string != null && string.length() > 0) {
				lSet.add(string.trim());
			}
		}
		return lSet;
	}

	public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>(setA);
		tmp.addAll(setB);
		return tmp;
	}

	public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>();
		for (T x : setA)
			if (setB.contains(x))
				tmp.add(x);
		return tmp;
	}

	public static <T> Set<T> difference(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>(setA);
		tmp.removeAll(setB);
		return tmp;
	}

	public static <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
		Set<T> tmpA;
		Set<T> tmpB;

		tmpA = union(setA, setB);
		tmpB = intersection(setA, setB);
		return difference(tmpA, tmpB);
	}

	public static <T> boolean isSubset(Set<T> setA, Set<T> setB) {
		return setB.containsAll(setA);
	}

	public static <T> boolean isSuperset(Set<T> setA, Set<T> setB) {
		return setA.containsAll(setB);
	}
}
