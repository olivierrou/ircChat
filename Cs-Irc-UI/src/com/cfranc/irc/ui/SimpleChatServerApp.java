package com.cfranc.irc.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.cfranc.irc.server.ClientConnectThread;

/**
 * 
 * @author Administrateur
 * Contrôleur côté Serveur
 * 
 */
class SimpleChatServerApp {

	// Ecran principal
	private SimpleChatFrameServer frame;
	
	// Console du serveur
	public StyledDocument model = new DefaultStyledDocument();
	
	
	private ClientConnectThread clientConnectThread;
	public static DefaultMutableTreeNode root = new DefaultMutableTreeNode("Liste des connectés");
	public static DefaultTreeModel clientTreeModel = new DefaultTreeModel(root);
	public static SimpleChatServerApp app;

	private SimpleChatServerApp(int port) {

		// Init GUI
		SimpleChatFrameServer simpleChatFrameServer = 
				new SimpleChatFrameServer(port, this.model, clientTreeModel);
		
		this.frame = simpleChatFrameServer;
		
		// Insère le message d'accueil
		try {
			this.model.insertString(this.model.getLength(),"Welcome into IRC Server Manager\n", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		// Affiche l'écran
		((JFrame) this.frame).setVisible(true);

		// Démarre le connection service
		this.clientConnectThread = new ClientConnectThread(port, this.model, clientTreeModel);
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