package com.advanceChart.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYAnnotationEntity;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class CoustomChartPanel extends ChartPanel {

	private Map<String, Set<String>> mSetValues = new HashMap<String, Set<String>>();
	private JFrame mFrame;

	public CoustomChartPanel(JFreeChart chart) {
		super(chart);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblClickOnA = new JLabel("Click on a number to get elements");
		lblClickOnA.setVerticalAlignment(SwingConstants.BOTTOM);
		lblClickOnA.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClickOnA);

		this.addChartMouseListener(new org.jfree.chart.ChartMouseListener() {

			public void chartMouseClicked(ChartMouseEvent evt) {
				ChartEntity cie = evt.getEntity();
				try {
					if (cie != null & cie instanceof XYAnnotationEntity) {
						XYAnnotationEntity ll = (XYAnnotationEntity) cie;
						String keyText = ll.getURLText();
						System.out.println(keyText);

						List<String> lValues = new ArrayList<String>(mSetValues
								.get(keyText));
						Collections.sort(lValues);
						StringBuilder lSB = new StringBuilder();
						for (String string : lValues) {
							// System.out.println(string);
							lSB.append(string + "\n");
						}

						JTextArea textArea = new JTextArea(6, 20);
						textArea.setText(lSB.toString());

						// wrap a scrollpane around it
						JScrollPane scrollPane = new JScrollPane(textArea);
						// textArea.setEditable(false);
						JOptionPane.showMessageDialog(mFrame, scrollPane,
								keyText, JOptionPane.INFORMATION_MESSAGE);

					} else
						return;
				} catch (Exception ce) {
					System.out.println("Error" + ce);
				}

			}

			public void chartMouseMoved(ChartMouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addSet(String pName, Set<String> set) {
		mSetValues.put(pName, set);

	}

	public void setFrame(JFrame lJFrame) {
		mFrame = lJFrame;

	}

}
