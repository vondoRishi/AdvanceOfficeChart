package com.advanceChart.configureChart;

import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class ChartTypePanel extends JPanel {
	private static final String IMAGES_DIR = "/com/advanceChart/images/";

	public ChartTypePanel() {
		super();
		setBounds(0, 0, 227, 184);
		setLayout(null);
	}

	protected static ImageIcon createImageIcon(String pFileName, String pDesc) {
        java.net.URL imgURL = ChartTypePanel.class.getResource(IMAGES_DIR+pFileName);
        if (imgURL != null) {
            return new ImageIcon(imgURL,pDesc);
        } else {
            System.err.println("Couldn't find file: " + pFileName);
            return null;
        }
    }
}
