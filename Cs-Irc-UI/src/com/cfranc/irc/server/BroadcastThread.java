package com.cfranc.irc.server;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.cfranc.irc.IfClientServerProtocol;
import com.cfranc.irc.ui.SimpleChatFrameServer;

public class BroadcastThread extends Thread {
	
	public static HashMap<User, ServerToClientThread> clientTreadsMap=new HashMap<User, ServerToClientThread>();
	static{
		Collections.synchronizedMap(clientTreadsMap);
	}
	
	/**
	 * 
	 * @param sender : user � l'origine de l'action
	 * @author Olivier Roussilhe
	 * 
	 * Signale aux utilisateurs connect�s l'ajout de l'utilisateur qui vient d'entrer 
	 */
	public static void addUser(User sender, ServerToClientThread serverToClientThread) {
		
		// R�cup�re la liste des users connect�s
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		
		// Envoie l'ajout du sender � tous les users connect�s
		while (receiverClientThreadIterator.hasNext()) {
			
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			
			// Envoie le message
			clientThread.post(IfClientServerProtocol.ADD + sender.getLogin());			
			System.out.println("ajoute : "+"#"+sender.getLogin()+"#");
		}
		
		// Pour tous les users connect�s
		Collection<User> listUsers = clientTreadsMap.keySet();	
		Iterator<User> iteratorUser = listUsers.iterator();
	
		for (User user : listUsers) {
			
			if (user != sender) {
				serverToClientThread.post(IfClientServerProtocol.ADD + user.getLogin());
			}
		}
	}


	/**
	 * 
	 * @param sender : user � l'origine de l'action
	 * @author Olivier Roussilhe
	 * 
	 * Signale aux utilisateurs connect�s la suppression de l'utilisateur 
	 */
	public static void deleteUser(User sender) {
		
		System.out.println("deleteUser");
		// R�cup�re la liste des users connect�s
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		
		// Envoie l'ajout du sender � tous les users connect�s
		while (receiverClientThreadIterator.hasNext()) {
			
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			
			// Envoie le message
			clientThread.post(IfClientServerProtocol.DEL + sender.getLogin());			
			System.out.println("supprime : "+"#"+sender.getLogin()+"#");
			
		}
		ClientConnectThread.removeNode(sender);
		clientTreadsMap.remove(sender);

		
		
//		
//		// Pour tous les users connect�s
//		Collection<User> listUsers = clientTreadsMap.keySet();	
//		Iterator<User> iteratorUser = listUsers.iterator();
//	
//		for (User user : listUsers) {
//			
//			if (user != sender) {
//				serverToClientThread.post(IfClientServerProtocol.DEL + user.getLogin());
//			}
//		}
	}
	
	
	/**
	 * 
	 * @param sender : user � l'origine de l'action
	 * @param msg : message � envoyer
	 * @author Olivier Roussilhe
	 * 
	 * Envoie aux users connect�s au serveur le message du sender
	 */
	public static void sendMessage(User sender, String msg){
		
		// R�cup�re la liste des users connect�s
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		
		// Pour tous les users connect�ss
		while (receiverClientThreadIterator.hasNext()) {
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			
			// Envoie le message
			clientThread.post("#"+sender.getLogin()+"#"+msg);			
			System.out.println("sendMessage : "+"#"+sender.getLogin()+"#"+msg);
		}
	}

	/**
	 * 
	 * @param user
	 * @param serverToClientThread
	 * @return 
	 * 
	 * Ajoute un user au serveur avec son socket (ServerToClientThread)
	 * Si le user est d�j� inscrit, retourne false
	 * Sinon retourne vrai et l'inscrit
	 */
	public static boolean addClient(User user, ServerToClientThread serverToClientThread){
		
		boolean res=true;
		
		if(clientTreadsMap.containsKey(user)){
			res=false;
		}
		else{
			clientTreadsMap.put(user, serverToClientThread);
			addUser(user, serverToClientThread);
			
		}
		return res;
	}

	
	
	// Suppression du user du Map
	public static void removeClient(User user){
		clientTreadsMap.remove(user);
		deleteUser(user);
		
	}
	
	/**
	 * 
	 * @param user
	 * @return booleen
	 * 
	 * Si user est d�j� dans la liste, il n'est pas ajout� et retourne false
	 * Sinon retourne true
	 */
	public static boolean accept(User user){
		boolean res=true;
		if(clientTreadsMap.containsKey(user)){
			res= false;

		}
		return res;
	}
}
