package com.cfranc.irc.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.cfranc.irc.IfClientServerProtocol;


/**
 * Ecoute l'arrivée d'un client sur ServerApp
 */

public class ClientConnectThread extends Thread implements
		IfClientServerProtocol {

	StyledDocument model = null;
	static DefaultTreeModel clientTreeModel;

	private boolean canStop = false;
	private ServerSocket server = null;

	/**
	 * 
	 * @param msg
	 * 
	 *            Affiche le message dans le log du ServerApp
	 */
	private void printMsg(String msg) {
		try {
			if (model != null) {
				model.insertString(model.getLength(), msg + "\n", null);
			}
			System.out.println(msg);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param port
	 * @param model
	 * @param clientTreeModel
	 */
	public ClientConnectThread(int port, StyledDocument model,
			DefaultTreeModel clientTreeModel) {
		// public ClientConnectThread(int port, StyledDocument model) {
		try {
			this.model = model;
			this.clientTreeModel = clientTreeModel;
			printMsg("Binding to port " + port + ", please wait  ...");

			server = new ServerSocket(port);
			printMsg("Server started: " + server);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	@Override
	public void run() {
		while (!canStop) {
			printMsg("Waiting for a client ...");
			Socket socket;
			try {
				socket = server.accept();
				printMsg("Client accepted: " + socket);

				// Accept new client or close the socket
				acceptClient(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param socket
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 */
	private void acceptClient(Socket socket) throws IOException,
			InterruptedException {

		// Read user login and pwd
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF(LOGIN_PWD);
		while (dis.available() <= 0) {
			Thread.sleep(100);
		}

		String reponse = dis.readUTF();
		String[] userPwd = reponse.split(SEPARATOR);
		String login = userPwd[1];
		String pwd = userPwd[2];
		String nom = userPwd[3];
		String prenom = userPwd[4];

		User newUser = new User(login, pwd, nom, prenom);
		boolean isUserOK = authentication(newUser);
		if (isUserOK) {

			ServerToClientThread client = new ServerToClientThread(newUser,
					socket);
			dos.writeUTF(OK);

			if (BroadcastThread.addClient(newUser, client)) {
				client.start();
				dos.writeUTF(ADD + login);
				addNode(newUser);

			}
		} else {
			System.out.println("socket.close()");
			dos.writeUTF(KO);
			dos.close();
			socket.close();
		}
	}

	private boolean authentication(User newUser) {
		return BroadcastThread.accept(newUser);
	}

	public void open() throws IOException {
	}

	public void close() throws IOException {
		System.err.println("server:close()");
		if (server != null)
			server.close();
	}

	public static void removeNode(User u) {

		System.out.println("supprime un node");
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) clientTreeModel.getRoot();
		
		for (int i = 0; i < root.getChildCount(); i++) {

			if (root.getChildAt(i).toString().equals(u.getLogin()) ) {
				System.out.println("dégage connard");
				root.remove(i);
			}
			clientTreeModel.reload();
		}
		
	}
	
    public void addNode(User u) {

		DefaultMutableTreeNode racine = (DefaultMutableTreeNode) clientTreeModel.getRoot();;
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(u.getLogin());
		racine.add( parent );
		parent.add(new DefaultMutableTreeNode(u.toString()));
		clientTreeModel.reload();
		
    }
    
    
}
