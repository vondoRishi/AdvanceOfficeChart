package com.advanceChart.configureSteps;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class ConfigurePanel extends JPanel {
	
	protected CommonConfigBean mCommonConfigBean = null;

	public CommonConfigBean getCommonConfigBean() {
		return mCommonConfigBean;
	}

	public void setCommonConfigBean(CommonConfigBean pCommonConfigBean) {
		this.mCommonConfigBean = pCommonConfigBean;
	}

	public ConfigurePanel(CommonConfigBean pCommonConfigBean) {
		super();
		mCommonConfigBean = pCommonConfigBean;
		setBounds(5, 5, 410, 230);
	}

	public ConfigurePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public ConfigurePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public ConfigurePanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

}
