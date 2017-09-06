package com.advanceChart.configureChart;
import javax.swing.JLabel;

public class HistogramConf extends ChartTypePanel {
	public HistogramConf() {
		super();
		setLayout(null);
		JLabel lblNewLabel = new JLabel(createImageIcon("histogram.jpg","Histogram"));
		lblNewLabel.setBounds(12, 12, 180, 108);
		add(lblNewLabel);
	}

	@Override
	public void repaint() {
		System.out.println("hist");
		super.repaint();
	}
}
