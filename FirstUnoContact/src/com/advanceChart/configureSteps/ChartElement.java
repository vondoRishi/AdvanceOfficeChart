package com.advanceChart.configureSteps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JSeparator;

public class ChartElement extends ConfigurePanel implements DocumentListener, ActionListener {
	private JTextField mTxtTitle;
	private JTextField mTextSubTitle;
	private JTextField mTxtXaxis;
	private JTextField mTxtYaxis;
	private JRadioButton mRdbtnNone;
	private JRadioButton mRdbtnLeft;
	private JRadioButton mRdbtnRight;
	private JRadioButton mRdbtnTop;
	private JRadioButton mRdbtnBottom;
	public ChartElement(CommonConfigBean pCommonConfigBean) {
		super(pCommonConfigBean);
		setLayout(null);
		
		JLabel label = new JLabel("Choose Titles");
		label.setBounds(12, 12, 99, 15);
		add(label);
		
		mTxtTitle = new JTextField();
		mTxtTitle.getDocument().addDocumentListener(this);
		mTxtTitle.setBounds(77, 37, 197, 19);
		add(mTxtTitle);
		mTxtTitle.setColumns(10);
		
		JLabel lblTiltle = new JLabel("Title");
		lblTiltle.setBounds(12, 39, 69, 15);
		add(lblTiltle);
		
		mTextSubTitle = new JTextField();
		mTextSubTitle.getDocument().addDocumentListener(this);
		mTextSubTitle.setColumns(10);
		mTextSubTitle.setBounds(77, 68, 197, 19);
		add(mTextSubTitle);
		
		JLabel lblSublitle = new JLabel("Subtitle");
		lblSublitle.setBounds(12, 70, 69, 15);
		add(lblSublitle);
		
		JLabel lblXAxis = new JLabel("X axis");
		lblXAxis.setBounds(12, 112, 69, 19);
		add(lblXAxis);
		
		mTxtXaxis = new JTextField();
		mTxtXaxis.getDocument().addDocumentListener(this);
		mTxtXaxis.setColumns(10);
		mTxtXaxis.setBounds(77, 112, 197, 19);
		add(mTxtXaxis);
		
		JLabel lblYAxis = new JLabel("Y axis");
		lblYAxis.setBounds(12, 143, 69, 15);
		add(lblYAxis);
		
		mTxtYaxis = new JTextField();
		mTxtYaxis.getDocument().addDocumentListener(this);
		mTxtYaxis.setColumns(10);
		mTxtYaxis.setBounds(77, 141, 197, 19);
		add(mTxtYaxis);
		
		JLabel lblLegend = new JLabel("Legend");
		lblLegend.setBounds(12, 183, 69, 15);
		add(lblLegend);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 170, 372, 1);
		add(separator);
		
		mRdbtnNone = new JRadioButton("None");
		mRdbtnNone.setBounds(75, 179, 69, 23);
		add(mRdbtnNone);
		mRdbtnNone.addActionListener(this);
		
		mRdbtnLeft = new JRadioButton("Left");
		mRdbtnLeft.setBounds(140, 179, 56, 23);
		add(mRdbtnLeft);
		mRdbtnLeft.addActionListener(this);
		
		mRdbtnRight = new JRadioButton("Right");
		mRdbtnRight.setBounds(194, 179, 63, 23);
		add(mRdbtnRight);
		mRdbtnRight.addActionListener(this);
		
		mRdbtnTop = new JRadioButton("Top");
		mRdbtnTop.setBounds(261, 179, 56, 23);
		add(mRdbtnTop);
		mRdbtnTop.addActionListener(this);
		
		mRdbtnBottom = new JRadioButton("Bottom");
		mRdbtnBottom.setSelected(true);
		mCommonConfigBean.setLegendChoice(mCommonConfigBean.LEGEND_BOTTOM);
		mRdbtnBottom.setBounds(313, 179, 83, 23);
		add(mRdbtnBottom);
		mRdbtnBottom.addActionListener(this);
		
		ButtonGroup legendGroup = new ButtonGroup();
		legendGroup.add(mRdbtnNone);
		legendGroup.add(mRdbtnLeft);
		legendGroup.add(mRdbtnRight);
		legendGroup.add(mRdbtnTop);
		legendGroup.add(mRdbtnBottom);
	}

	public void changedUpdate(DocumentEvent arg0) {
		update();
	}

	private void update() {
		mCommonConfigBean.setTitle(mTxtTitle.getText());
		mCommonConfigBean.setSubTitle(mTextSubTitle.getText());
		mCommonConfigBean.setXaxisLabel(mTxtXaxis.getText());
		mCommonConfigBean.setYaxisLabel(mTxtYaxis.getText());
	}

	public void insertUpdate(DocumentEvent arg0) {
		update();		
	}

	public void removeUpdate(DocumentEvent arg0) {
		update();		
	}

	public void actionPerformed(ActionEvent arg0) {
		if(mRdbtnNone.isSelected()){
			mCommonConfigBean.setLegendChoice(mCommonConfigBean.LEGEND_NONE);
		}else if(mRdbtnLeft.isSelected()){
			mCommonConfigBean.setLegendChoice(mCommonConfigBean.LEGEND_LEFT);
		}else if(mRdbtnRight.isSelected()){
			mCommonConfigBean.setLegendChoice(mCommonConfigBean.LEGEND_RIGHT);
		}else if(mRdbtnTop.isSelected()){
			mCommonConfigBean.setLegendChoice(mCommonConfigBean.LEGEND_TOP);
		}else if(mRdbtnBottom.isSelected()){
			mCommonConfigBean.setLegendChoice(mCommonConfigBean.LEGEND_BOTTOM);
		}
	}
}
