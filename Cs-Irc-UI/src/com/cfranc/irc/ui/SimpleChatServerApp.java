package com.cfranc.irc.ui;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.cfranc.irc.impl.DbSingleton;
import com.cfranc.irc.server.ClientConnectThread;
import com.cfranc.irc.server.User;

public class SimpleChatServerApp {

	private SimpleChatFrameServer frame;
	public StyledDocument model=new DefaultStyledDocument();
	private ClientConnectThread clientConnectThread;
	public static DefaultMutableTreeNode root = new DefaultMutableTreeNode("Liste des connectés");
	public static DefaultTreeModel clientTreeModel = new DefaultTreeModel(root);
	public static SimpleChatServerApp app;
    
	public SimpleChatServerApp(int port) {
		
		// Init GUI
		SimpleChatFrameServer simpleChatFrameServer= new SimpleChatFrameServer(port, this.model, clientTreeModel); //, clientTreeModel);
		this.frame=simpleChatFrameServer; 	
		try {
			this.model.insertString(this.model.getLength(), "Welcome into IRC Server Manager\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JFrame)this.frame).setVisible(true);
		
		// Start connection services
		this.clientConnectThread=new ClientConnectThread(port, this.model,clientTreeModel);
		this.clientConnectThread.start();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app = new SimpleChatServerApp(4567);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}