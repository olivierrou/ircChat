package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JPanel;

import com.cfranc.irc.impl.DbSingleton;
import com.cfranc.irc.server.User;

public class SimpleChatFrameServer extends JFrame{

	/**
	 * 
	 */
	private StyledDocument model=null;
	private DefaultTreeModel clientTreeModel;;
	

	
    private static JTree tree;
    

	public SimpleChatFrameServer(int port, StyledDocument model,  DefaultTreeModel clientTreeModel) {
		
		super("ISM - IRC Server Manager");
		this.model=model;
		
		this.clientTreeModel = clientTreeModel;
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
		
		
		tree = new JTree(clientTreeModel);
		JScrollPane scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, BorderLayout.WEST);
		scrollPane_1.setViewportView(tree);

	}	
	

    
    public DefaultMutableTreeNode getRoot() {
    	return (DefaultMutableTreeNode) clientTreeModel.getRoot();
    }
 
	
}