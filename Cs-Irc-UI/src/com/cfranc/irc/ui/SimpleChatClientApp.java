package com.cfranc.irc.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import sun.management.jmxremote.ConnectorBootstrap;
import com.cfranc.irc.client.ClientToServerThread;
import com.cfranc.irc.impl.DbSingleton;
import com.cfranc.irc.server.User;
import com.cfranc.irc.ui.SimpleChatFrameClient;
/**
 * <code>SimpleChatClientApp</code> Frame de connection Client.
 * 
 * @beaninfo
 * description: Permet au client de se connecter.
 *
 * @author Bertrand Wittmer
 */

public class SimpleChatClientApp {
    static String[] ConnectOptionNames = { "Connect" };	
    static String   ConnectTitle = "Connection Information";
    Socket socketClientServer;
    int serverPort;
    public User userConnect;
    String serverName;
    private SimpleChatFrameClient frameClient;
    private ConnectionFrame frameConnect;
	public StyledDocument documentModel=new DefaultStyledDocument();
	DefaultListModel<String> clientListModel=new DefaultListModel<String>();
	private static ClientToServerThread clientToServerThread;
	
    public static final String BOLD_ITALIC = "BoldItalic";
    public static final String GRAY_PLAIN = "Gray";
        
	public static DefaultStyledDocument defaultDocumentModel() {
		DefaultStyledDocument res=new DefaultStyledDocument();
	    
	    Style styleDefault = (Style) res.getStyle(StyleContext.DEFAULT_STYLE);
	    
	    res.addStyle(BOLD_ITALIC, styleDefault);
	    Style styleBI = res.getStyle(BOLD_ITALIC);
	    StyleConstants.setBold(styleBI, true);
	    StyleConstants.setItalic(styleBI, true);
	    StyleConstants.setForeground(styleBI, Color.black);	    

	    res.addStyle(GRAY_PLAIN, styleDefault);
        Style styleGP = res.getStyle(GRAY_PLAIN);
        StyleConstants.setBold(styleGP, false);
        StyleConstants.setItalic(styleGP, false);
        StyleConstants.setForeground(styleGP, Color.lightGray);

		return res;
	}

			
	public SimpleChatClientApp(){
		
	}
	
	public void displayClient() {
		
		// Init GUI
		this.frameClient = new SimpleChatFrameClient(clientToServerThread, clientListModel, documentModel);
		this.frameClient.setTitle(this.frameClient.getTitle() + " : " + userConnect.getLogin() + " connected to " + serverName + ":" + serverPort);
		((JFrame)this.frameClient).setVisible(true);
		this.frameClient.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowClosed(WindowEvent e) {
				quitApp(SimpleChatClientApp.this);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void hideClient() {
		// Init GUI
		((JFrame)this.frameClient).setVisible(false);
	}
	
    boolean displayConnectionDialog() {
    	
    	// ouverture de la Frame de connection
		this.frameConnect = new ConnectionFrame();
		this.frameConnect.getFrame().setModal(true);
		this.frameConnect.getFrame().setVisible(true);
		userConnect = this.frameConnect.getUserConnect();
		this.serverName = this.frameConnect.getServerField();
		this.serverPort = Integer.parseInt(this.frameConnect.getServerPortField());
		return !(userConnect == null);
	}
    
    private void connectClient() {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socketClientServer = new Socket(this.serverName, this.serverPort);
			// Start connection services
			clientToServerThread = new ClientToServerThread(documentModel, clientListModel, socketClientServer, userConnect);
			clientToServerThread.start();

			System.out.println("Connected: " + socketClientServer);
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final SimpleChatClientApp app = new SimpleChatClientApp();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (app.displayConnectionDialog()) {		// Connection à la base
						app.connectClient();					// connection du User
						app.displayClient();					// Ouverture de la frame du Client
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Scanner sc=new Scanner(System.in);
		String line="";
		while(!line.equals(".bye")){
			line=sc.nextLine();			
		}
		
		quitApp(app);
	}

	private static void quitApp(final SimpleChatClientApp app) {
		try {
			app.clientToServerThread.quitServer();
			app.socketClientServer.close();
			app.hideClient();
			System.out.println("SimpleChatClientApp : fermée");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
