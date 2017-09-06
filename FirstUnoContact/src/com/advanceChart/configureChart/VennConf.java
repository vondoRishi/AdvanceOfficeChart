package com.advanceChart.configureChart;

import javax.swing.JLabel;

public class VennConf extends ChartTypePanel {
	public VennConf(){
		super();
		setLayout(null);
		JLabel lblNewLabel = new JLabel(createImageIcon("Venn.jpg","Venn Diagam"));
		lblNewLabel.setBounds(12, 12, 180, 120);
		add(lblNewLabel);
	}
}
