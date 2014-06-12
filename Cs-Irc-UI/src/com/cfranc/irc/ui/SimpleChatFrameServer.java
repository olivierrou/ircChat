package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JPanel;

public class SimpleChatFrameServer extends JFrame{

	public StyledDocument model=null;
	//public DefaultListModel<Object> clientListModel=null;
	public DefaultTreeModel clientTreeModel = null;
			
	public SimpleChatFrameServer(int port, StyledDocument model,  DefaultTreeModel clientTreeModel) {
		super("ISM - IRC Server Manager");
		this.model=model;
		this.clientTreeModel=clientTreeModel;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 702, 339);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane(model);
		scrollPane.setViewportView(textPane);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				
			}
		});

		final JLabel statusBar=new JLabel("");
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		
		JTree tree = new JTree(clientTreeModel);
		JScrollPane scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, BorderLayout.WEST);
		scrollPane_1.setViewportView(tree);
		
//		JPanel panel = new JPanel();
//		panel.setMinimumSize(new Dimension(200, 200));
//		getContentPane().add(panel, BorderLayout.WEST);
//		panel.setLayout(new BorderLayout(0, 0));
//		
//		JScrollPane scrollPaneTree = new JScrollPane();
//		panel.add(scrollPaneTree, BorderLayout.NORTH);
//		JTree tree = new JTree(clientTreeModel);
//		panel.add(tree, BorderLayout.CENTER);
//		scrollPaneTree.setViewportView(tree);
//		tree.setMinimumSize(new Dimension(200,0));
//		scrollPaneTree.setViewportView(tree);

		
//		final JList<String> list = new JList<String>(clientListModel);
//		list.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				String clientSelected=list.getSelectedValue().toString();
//				statusBar.setText(clientSelected);
//			}
//		});
//		list.setMinimumSize(new Dimension(200,0));
//		scrollPaneList.setViewportView(list);
	}	
}