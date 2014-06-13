package com.cfranc.irc.ui;

import java.awt.Color;
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
	public static SimpleChatClientApp app = new SimpleChatClientApp();
	
    static String[] ConnectOptionNames = { "Connect" };	
    static String   ConnectTitle = "Connection Information";
    Socket socketClientServer;
    int serverPort;
    User oUser;
    String serverName;
 // Commentaire pour test git
 // Commentaire pour test git
	private SimpleChatFrameClient frame;
	public StyledDocument documentModel=new DefaultStyledDocument();
	DefaultListModel<String> clientListModel=new DefaultListModel<String>();
	
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

	private static ClientToServerThread clientToServerThread;
			
	public SimpleChatClientApp(){
		
	}
	
	public void displayClient() {
		
		// Init GUI
		this.frame = new SimpleChatFrameClient(clientToServerThread, clientListModel, documentModel);
		this.frame.setTitle(this.frame.getTitle() + " : " + oUser.getLogin() + " connected to " + serverName + ":" + serverPort);
		((JFrame)this.frame).setVisible(true);
		this.frame.addWindowListener(new WindowListener() {
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
				//quitApp(SimpleChatClientApp.this);
				quitApp();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void hideClient() {
		// Init GUI
		((JFrame)this.frame).setVisible(false);
	}
	
    boolean displayConnectionDialog() {
    	ConnectionPanel connectionPanel=new ConnectionPanel();
		if (JOptionPane.showOptionDialog(null, connectionPanel, ConnectTitle,
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, ConnectOptionNames, ConnectOptionNames[0]) == 0) {
			
			serverPort = Integer.parseInt(connectionPanel.getServerPortField().getText());
			serverName = connectionPanel.getServerField().getText();
			String login, pwd, nom, prenom;
			login	= (connectionPanel.getUserLoginField().getText() == null) ? "" : connectionPanel.getUserLoginField().getText() ;
			pwd		= (connectionPanel.getPwdField().getText() == null) ? "" : connectionPanel.getPwdField().getText() ;
			nom		= (connectionPanel.getNomField().getText() == null) ? "" : connectionPanel.getNomField().getText() ;
			prenom	= (connectionPanel.getPrenomField().getText() == null) ? "" : connectionPanel.getPrenomField().getText() ;
			oUser = new User(login, pwd, nom, prenom);
//			oUser = new User(	connectionPanel.getUserLoginField().getText(), 
//								connectionPanel.getPwdField().getText(), 
//								connectionPanel.getNomField().getText(), 
//								connectionPanel.getPrenomField().getText());
			return true;
		}
		return false;
	}
    
    private void connectClient() {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socketClientServer = new Socket(this.serverName, this.serverPort);
			// Start connection services
			clientToServerThread = new ClientToServerThread(documentModel, clientListModel, socketClientServer, oUser);
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
		
		
		// Connexion à la base
		DbSingleton.getInstance().connectSqlLite("db/ircdb.sqlite");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (app.displayConnectionDialog()) {
						app.connectClient();
						app.displayClient();
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
		
		quitApp();
	}

	public static void quitApp() {
		try {
			app.clientToServerThread.quitServer();
			app.socketClientServer.close();
			app.hideClient();
			System.out.println("SimpleChatClientApp : fermée");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
