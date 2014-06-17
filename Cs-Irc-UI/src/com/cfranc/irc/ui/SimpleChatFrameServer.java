package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultTreeModel;

public class SimpleChatFrameServer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private StyledDocument model;
	private DefaultTreeModel clientTreeModel;
    

	SimpleChatFrameServer(int port, StyledDocument model,  DefaultTreeModel clientTreeModel) {
		
		super("ISM - IRC Server Manager");
		
		// Init. avec les modèles du contrôleur 
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
		
		// l'arbre est init. avec clientTreeModel
		JTree tree = new JTree(clientTreeModel);
		JScrollPane scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, BorderLayout.WEST);
		scrollPane_1.setViewportView(tree);
	}	

}