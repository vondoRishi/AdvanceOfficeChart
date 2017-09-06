package com.advanceChart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.advanceChart.configureChart.ChartTypePanel;
import com.advanceChart.configureSteps.ChartElement;
import com.advanceChart.configureSteps.ChartType;
import com.advanceChart.configureSteps.CommonConfigBean;
import com.advanceChart.configureSteps.ConfigurePanel;
import com.advanceChart.configureSteps.DataRange;
import com.advanceChart.util.ChartUtil;
import com.advanceChart.util.OfficeUtil;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.XComponentContext;

public class AOCgui implements ActionListener {
	
	private static final String APPLICATION_NAME = "Advance Office Chart";
	private JFrame frame;
	private JPanel mPanelConfigure;
	private List<ConfigurePanel> mConfigurePanel = new ArrayList<ConfigurePanel>();
	private JButton mBtnBack;
	private JButton mBtnForward;
	private JButton mBtnFinish;
	private JButton mBtnCancel;
	private JComboBox mComboHelp;
	private CommonConfigBean mCommonConfigBean;
	private int mPrsntStepIndex;
	private JList mStepList; 

	public AOCgui(XComponentContext pXComponentContext) {
		
		OfficeUtil.initialize(pXComponentContext);
		frame = new JFrame();
		frame.setAlwaysOnTop(false);
		frame.setResizable(false);
		frame.setBounds(100, 100, 604, 352);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(createImage().getImage());
		
		JPanel panelSide = new JPanel();
		panelSide.setBounds(12, 12, 152, 242);
		frame.getContentPane().add(panelSide);
		panelSide.setLayout(null);
		
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(12, 12, 69, 15);
		panelSide.add(lblSteps);
		
		String[] values = new String[] {"1. Data Range", "2. Chart Type", "3. Chart Elements"};
		mStepList = new JList(values);
		mStepList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mStepList.setSelectedIndex(0);
		mStepList.setVisibleRowCount(-1);
		ListSelectionModel listSelectionModel = mStepList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new StepSelectionListener());
		mStepList.setBounds(12, 39, 134, 76);
		
		panelSide.add(mStepList);
		
		mPanelConfigure = new JPanel();
		mPanelConfigure.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		mPanelConfigure.setBounds(164, 12, 420, 242);
		frame.getContentPane().add(mPanelConfigure);
		mPanelConfigure.setLayout(null);
		
		/*JLabel label = new JLabel("\"\"");
		label.setBounds(130, 64, 69, 15);
		mPanelConfigure.add(label);*/
									
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(12, 265, 572, 47);
		frame.getContentPane().add(panelButtons);
		panelButtons.setLayout(null);
		
		mBtnBack = new JButton("<< Back");
		mBtnBack.addActionListener(this);
		mBtnBack.setBounds(144, 12, 101, 25);
		panelButtons.add(mBtnBack);
		
		mBtnForward = new JButton("Forward >>");
		mBtnForward.addActionListener(this);
		mBtnForward.setBounds(259, 12, 118, 25);
		panelButtons.add(mBtnForward);
		
		mBtnFinish = new JButton("Finish");
		mBtnFinish.addActionListener(this);
		mBtnFinish.setBounds(384, 12, 76, 25);
		panelButtons.add(mBtnFinish);
		
		mBtnCancel = new JButton("Cancel");
		mBtnCancel.addActionListener(this);
		mBtnCancel.setBounds(472, 12, 88, 25);
		panelButtons.add(mBtnCancel);
		
		mComboHelp = new JComboBox();
		mComboHelp.setEnabled(false);
		mComboHelp.addActionListener(this);
		mComboHelp.setBounds(12, 12, 104, 24);
		panelButtons.add(mComboHelp);
		mComboHelp.setModel(new DefaultComboBoxModel(new String[] {"Help", "About AOC"}));
		
		initialize();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setTitle(APPLICATION_NAME);
		frame.setVisible(true);
	}
	
	private ImageIcon createImage() {
		java.net.URL imgURL = ChartTypePanel.class.getResource("/com/advanceChart/images/iconadvanceofficechart.png");
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: iconadvanceofficechart.png" );
            return null;
        }
	}

	private void showPanel(int stepIndex) {
		mPrsntStepIndex = stepIndex;
		mStepList.setSelectedIndex(mPrsntStepIndex);
		mBtnBack.setEnabled(true);
		mBtnForward.setEnabled(true);
		if(stepIndex==0){
			mBtnBack.setEnabled(false);
			
		}else if((stepIndex+1)== mConfigurePanel.size()){
			mBtnForward.setEnabled(false);
			
		}
		mPanelConfigure.removeAll();
		mPanelConfigure.add(mConfigurePanel.get(stepIndex));
		mPanelConfigure.repaint();
		
	}

	private void initialize() {
		mCommonConfigBean = new CommonConfigBean(); 
		mConfigurePanel.add(new DataRange(mCommonConfigBean));
		mConfigurePanel.add(new ChartType(mCommonConfigBean));
		mConfigurePanel.add(new ChartElement(mCommonConfigBean));
		showPanel(0);
	}

	public static void main(String[] args) throws BootstrapException {
		com.sun.star.uno.XComponentContext xContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
		new AOCgui(xContext);
	}

	public void actionPerformed(ActionEvent pEvnt) {
		if(pEvnt.getSource()==mBtnFinish){
			ChartUtil.plot(mCommonConfigBean);
			frame.dispose();
		}else if(pEvnt.getSource()==mBtnForward){
			showPanel(mPrsntStepIndex+1);
		}else if(pEvnt.getSource()==mBtnBack){
			showPanel(mPrsntStepIndex-1);
		}else if(pEvnt.getSource()==mBtnCancel){
			frame.dispose();
		}
		
	}
	
	public class StepSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {
			ListSelectionModel lsm = (ListSelectionModel)event.getSource();

	        if (lsm.isSelectionEmpty()) {
	           //TODO
	        } else {
	            // Find out which indexes are selected.
	            int minIndex = lsm.getMinSelectionIndex();
	            int maxIndex = lsm.getMaxSelectionIndex();
	            for (int i = minIndex; i <= maxIndex; i++) {
	                if (lsm.isSelectedIndex(i)) {
	                    showPanel(i);
	                }
	            }
	        }
	        
	    }

	}
}
