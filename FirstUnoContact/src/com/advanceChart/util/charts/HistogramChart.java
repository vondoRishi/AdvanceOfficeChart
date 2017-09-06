package com.advanceChart.util.charts;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.RectangleEdge;

import com.advanceChart.configureSteps.CommonConfigBean;
import com.advanceChart.util.OfficeUtil;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JSeparator;

public class HistogramChart implements ActionListener, ChangeListener {

	
	private JRadioButton mFrequencyRadioButton;
	private JRadioButton mRelativeFrequencyRadioButton;
	private JSpinner mBinSpinner;
	private int mNumberBin;
	private List<DataSeries> mValues;
	private SpinnerNumberModel mSpinnerNumberModel;
	private HistogramType mHistType= HistogramType.FREQUENCY;
	private String mTitle;
	private String mXaxisLabel;
	private String mYaxisLabel;
	private int mLegendPosition;
	private TextTitle mSubtitle1;
	private JPanel mPanel;
	private boolean mShowLegend;
	private boolean mToolTips;
	private boolean mUrls;
	private GridBagConstraints gbc_chartPanel;
	private String mWinTitle ;
	

	public HistogramChart(CommonConfigBean pCommonConfigBean) {
		   mNumberBin = 30;
		   
	       mValues = OfficeUtil.getTextSeries(pCommonConfigBean.isFirstRowLabel());
	       mTitle = pCommonConfigBean.getTitle(); 
	       mXaxisLabel = pCommonConfigBean.getXaxisLabel();
	       mSubtitle1 = new TextTitle(pCommonConfigBean.getSubTitle());
	       mLegendPosition = pCommonConfigBean.getLegendPosition();
	       mShowLegend = (mLegendPosition>0); 
	       mToolTips = false;
	       mUrls = false; 
	       mWinTitle = pCommonConfigBean.getSelectedRangeAsText();
	    		   
	       JPanel panel = new JPanel();
	       GridBagLayout gbl_panel = new GridBagLayout();
	       gbl_panel.columnWeights = new double[]{1.0};
	       panel.setLayout(gbl_panel);
	       
	       mPanel = new JPanel();
	       GridBagLayout gbl_mPanel = new GridBagLayout();
	       mPanel.setLayout(gbl_mPanel);
	       
	       ChartPanel chartPanel = createChartPanel();
	       
	       gbc_chartPanel = new GridBagConstraints();
	       gbc_chartPanel.weighty = 1.0;
	       gbc_chartPanel.gridheight = 0;
	       gbc_chartPanel.gridwidth = 0;
	       gbc_chartPanel.weightx = 1.0;
	       gbc_chartPanel.anchor = GridBagConstraints.PAGE_START;
	       gbc_chartPanel.fill = GridBagConstraints.BOTH;
	       gbc_chartPanel.gridx = 0;
	       gbc_chartPanel.gridy = 0;
	       mPanel.add(chartPanel,gbc_chartPanel);
	       
	       panel.setBounds(0, 0, 560, 316);
	       GridBagConstraints gbc_mPanel = new GridBagConstraints();
	       gbc_mPanel.anchor = GridBagConstraints.PAGE_START;
	       gbc_mPanel.weighty = 1.0;
	       gbc_mPanel.fill = GridBagConstraints.BOTH;
	       gbc_mPanel.gridy = 0;
	       gbc_mPanel.gridx = 0;
	       panel.add(mPanel, gbc_mPanel);
	       
	       JPanel panel_1 = new JPanel();
	       panel_1.setBounds(440, 45, 120, 202);
	       GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	       gbc_panel_1.anchor = GridBagConstraints.PAGE_START;
	       gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
	       gbc_panel_1.gridy = 1;
	       gbc_panel_1.gridx = 0;
	       panel.add(panel_1, gbc_panel_1);
	       GridBagLayout gbl_panel_1 = new GridBagLayout();
	       /*gbl_panel_1.columnWidths = new int[]{120, 0, 0, 0, 0};
	       gbl_panel_1.rowHeights = new int[]{23, 23, 15, 81, 15, 20, 0};
	       gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	       gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};*/
	       panel_1.setLayout(gbl_panel_1);
	       
	       mFrequencyRadioButton = new JRadioButton("Count");
	       GridBagConstraints gbc_mFrequencyRadioButton = new GridBagConstraints();
	       gbc_mFrequencyRadioButton.anchor = GridBagConstraints.SOUTH;
	       gbc_mFrequencyRadioButton.fill = GridBagConstraints.BOTH;
	       gbc_mFrequencyRadioButton.insets = new Insets(0, 0, 0, 5);
	       gbc_mFrequencyRadioButton.gridx = 0;
	       gbc_mFrequencyRadioButton.gridy = 0;
	       panel_1.add(mFrequencyRadioButton, gbc_mFrequencyRadioButton);
	       mFrequencyRadioButton.setSelected(true);
	       mFrequencyRadioButton.addActionListener(this);
	       
	       
	       mRelativeFrequencyRadioButton = new JRadioButton("Frequency");
	       GridBagConstraints gbc_mRelativeFrequencyRadioButton = new GridBagConstraints();
	       gbc_mRelativeFrequencyRadioButton.anchor = GridBagConstraints.NORTHWEST;
	       gbc_mRelativeFrequencyRadioButton.fill = GridBagConstraints.HORIZONTAL;
	       gbc_mRelativeFrequencyRadioButton.insets = new Insets(0, 0, 0, 5);
	       gbc_mRelativeFrequencyRadioButton.gridx = 1;
	       gbc_mRelativeFrequencyRadioButton.gridy = 0;
	       panel_1.add(mRelativeFrequencyRadioButton, gbc_mRelativeFrequencyRadioButton);
	       mRelativeFrequencyRadioButton.addActionListener(this);
	       
	       JSeparator separator = new JSeparator();
	       separator.setOrientation(SwingConstants.VERTICAL);
	       GridBagConstraints gbc_separator = new GridBagConstraints();
	       gbc_separator.insets = new Insets(0, 0, 0, 5);
	       gbc_separator.fill = GridBagConstraints.BOTH;
	       gbc_separator.gridx = 2;
	       gbc_separator.gridy = 0;
	       panel_1.add(separator, gbc_separator);
	       
	       JLabel lblNumberOfBins = new JLabel("Number of bins");
	       GridBagConstraints gbc_lblNumberOfBins = new GridBagConstraints();
	       gbc_lblNumberOfBins.insets = new Insets(0, 0, 0, 5);
	       gbc_lblNumberOfBins.gridx = 3;
	       gbc_lblNumberOfBins.gridy = 0;
	       panel_1.add(lblNumberOfBins, gbc_lblNumberOfBins);
	       
	       mBinSpinner = new JSpinner();
	       GridBagConstraints gbc_mBinSpinner = new GridBagConstraints();
	       gbc_mBinSpinner.insets = new Insets(0, 5, 0, 0);
	       gbc_mBinSpinner.gridx = 4;
	       gbc_mBinSpinner.gridy = 0;
	       panel_1.add(mBinSpinner, gbc_mBinSpinner);
	       mSpinnerNumberModel = new SpinnerNumberModel(mNumberBin, 10, 100, 10);
	       mBinSpinner.setModel(mSpinnerNumberModel);
	       mBinSpinner.addChangeListener(this);
	      
	       
	       ButtonGroup radioGroup = new ButtonGroup();
	       radioGroup.add(mFrequencyRadioButton);
	       radioGroup.add(mRelativeFrequencyRadioButton);
	       
	       JFrame lFrame = new JFrame();
	       lFrame.getContentPane().setLayout(new GridBagLayout());
	       GridBagConstraints gbc_rootPanel = new GridBagConstraints();
	       gbc_rootPanel.fill = GridBagConstraints.BOTH;
	       gbc_rootPanel.weighty = 1.0;
	       gbc_rootPanel.weightx = 1.0;
	       gbc_rootPanel.anchor = GridBagConstraints.NORTHEAST;
	       gbc_rootPanel.gridx = 0;
	       gbc_rootPanel.gridy = 0;
	       lFrame.getContentPane().add(panel,gbc_rootPanel);
	       lFrame.setSize(568, 344);
	       lFrame.setLocationRelativeTo(null);
	       lFrame.setTitle(mWinTitle);
	       lFrame.setVisible(true);
	}



	private ChartPanel createChartPanel() {
		HistogramDataset mDataset = new HistogramDataset();
	       for (DataSeries dataSeries : mValues) {
	    	    List<Double> list = new ArrayList<Double>();
				String[] textValues = dataSeries.getTextValues();
				for (String d : textValues) {
					
					try {
						list.add(Double.parseDouble(d));
					} catch (NumberFormatException e) {
						System.out.println(d);
					}
				}
				double[] doubleValues = new double[list.size()];
				for (int i = 0; i < doubleValues.length; i++) {
					doubleValues[i] = list.get(i);
				}
	    	   mDataset.addSeries(dataSeries.getDataSeriesName(),doubleValues,mNumberBin);
	       }
	       
	       mDataset.setType(mHistType);
	       if(mHistType == HistogramType.RELATIVE_FREQUENCY){
	    	   mYaxisLabel = "Frequency";//pCommonConfigBean.getYaxisLabel();
	       }else{
	    	   mYaxisLabel = "Count";//pCommonConfigBean.getYaxisLabel();
	       }
	        
	       PlotOrientation orientation = PlotOrientation.VERTICAL; 
	       JFreeChart chart = ChartFactory.createHistogram( mTitle, mXaxisLabel, mYaxisLabel, 
	                mDataset, orientation, mShowLegend, mToolTips, mUrls);
	       chart.addSubtitle(mSubtitle1);
	       
	       if(mLegendPosition>0){
		       LegendTitle legend = chart.getLegend();
		       if(mLegendPosition==CommonConfigBean.LEGEND_RIGHT){
		    	   legend.setPosition(RectangleEdge.RIGHT);
		       }else if(mLegendPosition==CommonConfigBean.LEGEND_LEFT){
		    	   legend.setPosition(RectangleEdge.LEFT);
		       }else if(mLegendPosition==CommonConfigBean.LEGEND_TOP){
		    	   legend.setPosition(RectangleEdge.TOP);
		       }else if(mLegendPosition==CommonConfigBean.LEGEND_BOTTOM){
		    	   legend.setPosition(RectangleEdge.BOTTOM);
		       }
	       }
	       
	       Plot plot = chart.getPlot();
	       plot.setBackgroundPaint(Color.white);
	       plot.setForegroundAlpha(0.9F);
	      	       
	       ChartPanel chartPanel_1 = new ChartPanel(chart);
	       //chartPanel_1.setBounds(12, 12, 329, 274);
	       chartPanel_1.setLayout(null);
	       
		return chartPanel_1;
	}

	

	public void actionPerformed(ActionEvent evnt) {
		if(evnt.getSource()==mFrequencyRadioButton){
			mHistType = HistogramType.FREQUENCY;
		}else if(evnt.getSource()==mRelativeFrequencyRadioButton){
			mHistType = HistogramType.RELATIVE_FREQUENCY;
		}
		mPanel.remove(0);
		mPanel.add(createChartPanel(),gbc_chartPanel);
		mPanel.updateUI();
	}

	public void stateChanged(ChangeEvent evnt) {
		mNumberBin=(Integer) mSpinnerNumberModel.getValue();
		mPanel.remove(0);
		mPanel.add(createChartPanel(),gbc_chartPanel);
		mPanel.updateUI();
		
	}
}
