package com.cfranc.irc.ui;

import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.cfranc.irc.server.ClientConnectThread;

public class SimpleChatServerApp {

	private SimpleChatFrameServer frame;
	public StyledDocument model=new DefaultStyledDocument();
//	DefaultListModel<Object> clientListModel=new DefaultListModel<Object>();
	DefaultTreeModel clientTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Utilisateurs connect�s"));
//	DefaultTreeModel clientTreeModel;
	private ClientConnectThread clientConnectThread;
			
	public SimpleChatServerApp(int port) {
		
		// Init GUI
		this.frame=new SimpleChatFrameServer(port, this.model, clientTreeModel);		
		try {
			this.model.insertString(this.model.getLength(), "Wellcome into IRC Server Manager\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JFrame)this.frame).setVisible(true);
		
		// Start connection services
		this.clientConnectThread=new ClientConnectThread(port, this.model, clientTreeModel);
		this.clientConnectThread.start();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleChatServerApp app = new SimpleChatServerApp(4567);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}