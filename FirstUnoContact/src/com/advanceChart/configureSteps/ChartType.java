package com.advanceChart.configureSteps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.advanceChart.configureChart.BoxPlotConf;
import com.advanceChart.configureChart.ChartTypePanel;
import com.advanceChart.configureChart.HistogramConf;
import com.advanceChart.configureChart.VennConf;

public class ChartType extends ConfigurePanel implements ActionListener {
	
	private JPanel mChartPanel;
	private List<ChartTypePanel> mChartConfigurePanel = new ArrayList<ChartTypePanel>();

	public ChartType(CommonConfigBean pCommonConfigBean) {
		super(pCommonConfigBean);
		setLayout(null);
		
		JLabel lblChooseAChart = new JLabel("Choose a chart Type");
		lblChooseAChart.setBounds(12, 12, 150, 21);
		add(lblChooseAChart);
		
		JList lChartList = new JList(mCommonConfigBean.getChartAvailableTypes());
		lChartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lChartList.setSelectedIndex(mCommonConfigBean.getDefaultChartType());
		lChartList.setVisibleRowCount(-1);
		
		ListSelectionModel listSelectionModel = lChartList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ChartSelectionListener());
		lChartList.setBounds(12, 37, 144, 69);
		add(lChartList);
		
		mChartPanel = new JPanel();
		mChartPanel.setBounds(168, 37, 227, 184);
		add(mChartPanel);
		mChartPanel.setLayout(null);
		
		/*JLabel lblCommingSoon = new JLabel("Comming Soon");
		lblCommingSoon.setBounds(12, 128, 150, 15);
		add(lblCommingSoon);
		
		JLabel lblNewLabel = new JLabel("Venn Diagram");
		lblNewLabel.setBounds(42, 145, 120, 15);
		add(lblNewLabel);*/
		/*ChartTypePanel histogramConf = new BoxPlotConf();
		histogramConf.setBounds(0, 0, 215, 172);
		histogramConf.setLayout(null);
		mChartPanel.add(histogramConf);*/
		
		initialize();
	}

	private void initialize() {
		mChartConfigurePanel.add(new HistogramConf());
		mChartConfigurePanel.add(new BoxPlotConf());
		mChartConfigurePanel.add(new VennConf());
		/*mChartPanel.add(mChartConfigurePanel.get(0));
		mChartPanel.add(mChartConfigurePanel.get(1));*/
		showConf(0);
	}

	private void showConf(int pConfIndex) {
		mChartPanel.removeAll();
		mChartPanel.add(mChartConfigurePanel.get(pConfIndex));
		mChartPanel.repaint();
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public class ChartSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {
			ListSelectionModel lsm = (ListSelectionModel)event.getSource();

	        int firstIndex = event.getFirstIndex();
	        int lastIndex = event.getLastIndex();
	        boolean isAdjusting = event.getValueIsAdjusting();
	       

	        if (lsm.isSelectionEmpty()) {
	           //TODO
	        } else {
	            // Find out which indexes are selected.
	            int minIndex = lsm.getMinSelectionIndex();
	            int maxIndex = lsm.getMaxSelectionIndex();
	            for (int i = minIndex; i <= maxIndex; i++) {
	                if (lsm.isSelectedIndex(i)) {
	                    mCommonConfigBean.setSelectedChartType(i);
	                    showConf(i);
	                }
	            }
	        }
	        
	    }

	}
}
