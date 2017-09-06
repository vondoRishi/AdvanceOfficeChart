package com.advanceChart.configureChart;

import javax.swing.JLabel;

public class BoxPlotConf extends ChartTypePanel {

	public BoxPlotConf() {
		super();
		setLayout(null);
		JLabel lblNewLabel = new JLabel(createImageIcon("boxplot.jpg","BoxPlot"));
		lblNewLabel.setBounds(12, 12, 180, 120);
		add(lblNewLabel);
	}
	

}
