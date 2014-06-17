package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cfranc.irc.impl.UserImpl;

/**
 * <code>SimpleChatClientApp</code> Frame de connection Client.
 * 
 * @beaninfo description: Permet au client de se connecter.
 * 
 * @author Bertrand Wittmer
 */


public class ConnectionFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userLoginField;
	private JPasswordField userPwdField;
	private JTextField serverField;
	private JTextField serverPortField;
	private UserImpl userConnect = null;


	public UserImpl getUserConnect() {
		return userConnect;
	}

	/**
	 * Create the application.
	 */
	public ConnectionFrame() {
	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 353, 184);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("Bienvenue sur le chat!");
		
		JPanel panelBtn = new JPanel();
		getContentPane().add(panelBtn, BorderLayout.SOUTH);

		JButton ConnectBtn = new JButton(new Connecter());
		ConnectBtn.setText("Connecter");
		JButton annulerBtn = new JButton(new Annuler());
		annulerBtn.setText("Annuler");
		panelBtn.add(ConnectBtn);
		panelBtn.add(annulerBtn);

		JPanel panelField = new JPanel();
		getContentPane().add(panelField, BorderLayout.CENTER);
		GridBagLayout gbl_panelField = new GridBagLayout();
		gbl_panelField.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelField.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_panelField.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelField.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelField.setLayout(gbl_panelField);

		JLabel userLoginLabel = new JLabel("Login :");
		GridBagConstraints gbc_userLoginLabel = new GridBagConstraints();
		gbc_userLoginLabel.anchor = GridBagConstraints.EAST;
		gbc_userLoginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userLoginLabel.gridx = 1;
		gbc_userLoginLabel.gridy = 4;
		panelField.add(userLoginLabel, gbc_userLoginLabel);

		userLoginField = new JTextField("");
		GridBagConstraints gbc_userLoginField = new GridBagConstraints();
		gbc_userLoginField.insets = new Insets(0, 0, 5, 5);
		gbc_userLoginField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userLoginField.gridx = 2;
		gbc_userLoginField.gridy = 4;
		panelField.add(userLoginField, gbc_userLoginField);
		userLoginField.setColumns(10);

		JLabel userPwdLabel = new JLabel("Mot de Passe :");
		GridBagConstraints gbc_userPwdLabel = new GridBagConstraints();
		gbc_userPwdLabel.anchor = GridBagConstraints.EAST;
		gbc_userPwdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userPwdLabel.gridx = 1;
		gbc_userPwdLabel.gridy = 5;
		panelField.add(userPwdLabel, gbc_userPwdLabel);

		userPwdField = new JPasswordField("");
		GridBagConstraints gbc_userPwdField = new GridBagConstraints();
		gbc_userPwdField.insets = new Insets(0, 0, 5, 5);
		gbc_userPwdField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userPwdField.gridx = 2;
		gbc_userPwdField.gridy = 5;
		panelField.add(userPwdField, gbc_userPwdField);
		userPwdField.setColumns(10);

		JLabel serverLabel = new JLabel("Serveur :");
		GridBagConstraints gbc_serverLabel = new GridBagConstraints();
		gbc_serverLabel.anchor = GridBagConstraints.EAST;
		gbc_serverLabel.insets = new Insets(0, 0, 5, 5);
		gbc_serverLabel.gridx = 1;
		gbc_serverLabel.gridy = 6;
		panelField.add(serverLabel, gbc_serverLabel);

		serverField = new JTextField("localhost");
		GridBagConstraints gbc_serverField = new GridBagConstraints();
		gbc_serverField.insets = new Insets(0, 0, 5, 5);
		gbc_serverField.fill = GridBagConstraints.HORIZONTAL;
		gbc_serverField.gridx = 2;
		gbc_serverField.gridy = 6;
		panelField.add(serverField, gbc_serverField);
		serverField.setColumns(10);

		JLabel serverPortLabel = new JLabel("port :");
		GridBagConstraints gbc_serverPortLabel = new GridBagConstraints();
		gbc_serverPortLabel.anchor = GridBagConstraints.EAST;
		gbc_serverPortLabel.insets = new Insets(0, 0, 5, 5);
		gbc_serverPortLabel.gridx = 1;
		gbc_serverPortLabel.gridy = 7;
		panelField.add(serverPortLabel, gbc_serverPortLabel);

		serverPortField = new JTextField("4567");
		GridBagConstraints gbc_serverPortField = new GridBagConstraints();
		gbc_serverPortField.insets = new Insets(0, 0, 5, 5);
		gbc_serverPortField.fill = GridBagConstraints.HORIZONTAL;
		gbc_serverPortField.gridx = 2;
		gbc_serverPortField.gridy = 7;
		panelField.add(serverPortField, gbc_serverPortField);
		serverPortField.setColumns(10);
	}

	/********************* Action des textField ********************/
	public String getUserLoginField() {
		return userLoginField.getText();
	}

	public String getUserPwdField() {
		return userPwdField.getText();
	}

	public String getServerField() {
		return serverField.getText();
	}

	public String getServerPortField() {
		return serverPortField.getText();
	}

	/********************* Action des boutons *********************/
	private class Connecter extends AbstractAction {


		public void actionPerformed(ActionEvent e) {

			// Vérifier les saisies
			if (getUserLoginField() == "") {
				JOptionPane.showMessageDialog(null,
						"Vous devez saisir un Login.", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (getServerField() == "") {
				JOptionPane.showMessageDialog(null,
						"Vous devez saisir un Serveur.", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (getServerPortField() == "") {
				JOptionPane.showMessageDialog(null,
						"Vous devez saisir un Port.", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			userConnect = new UserImpl(getUserLoginField(), getUserPwdField());
			// 1 : Vérifier le Login
			if (!(userConnect.estInscrit())) {
				int reply = JOptionPane
						.showConfirmDialog(null,
								"Ce Login n'existe pas \r Voulez-vous créer un nouveau compte ?",
								"Login inexistant", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					UserCompte f = new UserCompte(1);
					f.setModal(true);
					f.setLocationRelativeTo(null); 	// Permet de centrer l'écran au lancement de l'application
					f.setVisible(true);

					
					return;

				} else {
					return;
				}
			}
			// Vérifier le mot de passe
			if (!(userConnect.estMotPasseCorrect())) {
				JOptionPane.showMessageDialog(null, "Mot de passe incorrect.", "Inane error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// Charger le compte de l'utilisateur
			userConnect.charger(userConnect.getLogin(), userConnect.getPwd());
			setVisible(false);
			;
		}

	}

	private class Annuler extends AbstractAction {


		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
