package com.advanceChart.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.annotations.XYTextAnnotation;

public class CoustomXYTextAnnotation extends XYTextAnnotation implements ActionListener {

	public CoustomXYTextAnnotation(String text, double x, double y) {
		super(text, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void actionPerformed(ActionEvent evnt) {
		System.out.println("from coustom "+evnt.getActionCommand());
		
	}

}
