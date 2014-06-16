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
	 * @param sender : user à l'origine de l'action
	 * @author Olivier Roussilhe
	 * 
	 * Signale aux utilisateurs connectés l'ajout de l'utilisateur qui vient d'entrer 
	 */
	public static void addUser(User sender, ServerToClientThread serverToClientThread) {
		
		// Récupère la liste des users connectés
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		
		// Envoie l'ajout du sender à tous les users connectés
		while (receiverClientThreadIterator.hasNext()) {
			
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			
			// Envoie le message
			clientThread.post(IfClientServerProtocol.ADD + sender.getLogin());			
			System.out.println("ajoute : "+"#"+sender.getLogin()+"#");
		}
		
		// Pour tous les users connectés
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
	 * @param sender : user à l'origine de l'action
	 * @author Olivier Roussilhe
	 * 
	 * Signale aux utilisateurs connectés la suppression de l'utilisateur 
	 */
	public static void deleteUser(User sender) {
		
		System.out.println("deleteUser");
		// Récupère la liste des users connectés
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		
		// Envoie l'ajout du sender à tous les users connectés
		while (receiverClientThreadIterator.hasNext()) {
			
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			
			// Envoie le message
			clientThread.post(IfClientServerProtocol.DEL + sender.getLogin());			
			System.out.println("supprime : "+"#"+sender.getLogin()+"#");
			
		}
		ClientConnectThread.removeNode(sender);
		clientTreadsMap.remove(sender);

		
		
//		
//		// Pour tous les users connectés
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
	 * @param sender : user à l'origine de l'action
	 * @param msg : message à envoyer
	 * @author Olivier Roussilhe
	 * 
	 * Envoie aux users connectés au serveur le message du sender
	 */
	public static void sendMessage(User sender, String msg){
		
		// Récupère la liste des users connectés
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		
		// Pour tous les users connectéss
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
	 * Si le user est déjà inscrit, retourne false
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
	 * Si user est déjà dans la liste, il n'est pas ajouté et retourne false
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
