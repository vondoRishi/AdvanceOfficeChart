package com.advanceChart.configureSteps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import com.advanceChart.util.OfficeUtil;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XCellRange;
import java.awt.Color;

public class DataRange extends ConfigurePanel implements ActionListener {
	private JTextField textField;
	private JButton mBtnRescan;
	private JCheckBox mChckbxFirstRowAs;
	private JCheckBox chckbxFirstColumnAs;
	private JRadioButton rdbtnDataSeriesInRows;
	public DataRange(CommonConfigBean pCommonConfigBean) {
		super(pCommonConfigBean);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose a data range");
		lblNewLabel.setBounds(12, 12, 165, 15);
		add(lblNewLabel);
		
		JLabel lblDataRange = new JLabel("Data Range");
		lblDataRange.setBounds(12, 39, 100, 15);
		add(lblDataRange);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(12, 61, 294, 25);
		add(textField);
		textField.setColumns(10);
		
		mBtnRescan = new JButton("Rescan");
		mBtnRescan.setBackground(new Color(250, 128, 114));
		mBtnRescan.setToolTipText("Resan after selecting new data range.");
		mBtnRescan.setBounds(318, 61, 86, 25);
		mBtnRescan.addActionListener(this);
		add(mBtnRescan);
		
		rdbtnDataSeriesInRows = new JRadioButton("Data series in columns");
		rdbtnDataSeriesInRows.setSelected(true);
		rdbtnDataSeriesInRows.setBounds(12, 105, 191, 23);
		add(rdbtnDataSeriesInRows);
		
		JRadioButton rdbtnDataSeriesIn_1 = new JRadioButton("Data series in rows");
		rdbtnDataSeriesIn_1.setEnabled(false);
		rdbtnDataSeriesIn_1.setBounds(12, 132, 206, 23);
		add(rdbtnDataSeriesIn_1);
		
		mChckbxFirstRowAs = new JCheckBox("First row as label");
		mChckbxFirstRowAs.setSelected(true);
		mChckbxFirstRowAs.setBounds(12, 175, 177, 23);
		mChckbxFirstRowAs.addActionListener(this);
		add(mChckbxFirstRowAs);
		
		chckbxFirstColumnAs = new JCheckBox("First column as label");
		chckbxFirstColumnAs.setEnabled(false);
		chckbxFirstColumnAs.setBounds(12, 202, 191, 23);
		add(chckbxFirstColumnAs);
		initialize();
	}

	private void initialize() {
		configBean();
		textField.setText(mCommonConfigBean.getSelectedRangeAsText());	
	}

	private void configBean() {
		mCommonConfigBean.setSelectedRangeAsText(OfficeUtil.getSelRangeText());
		mCommonConfigBean.setDataInRows(rdbtnDataSeriesInRows.isSelected());
		mCommonConfigBean.setFirstRowLabel(mChckbxFirstRowAs.isSelected());
		//mCommonConfigBean.setNumericSeries(OfficeUtil.getNumericSeries(mCommonConfigBean.isFirstRowLabel()));

	}

	public void actionPerformed(ActionEvent pEvent) {
		if(pEvent.getSource()==mBtnRescan){
			mCommonConfigBean.setSelectedRangeAsText(OfficeUtil.getSelRangeText());
			
			textField.setText(mCommonConfigBean.getSelectedRangeAsText());	
		}else if(pEvent.getSource()==mChckbxFirstRowAs){
			mCommonConfigBean.setFirstRowLabel(mChckbxFirstRowAs.isSelected());
		}

	}
}
