package com.cfranc.irc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.activation.MailcapCommandMap;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.UIDefaults;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import com.cfranc.irc.server.User;

public class ConnectionPanel extends JPanel {

	private JTextField serverPortField;
	private JTextField serverField;
	private JTextField userNomField;
	private JTextField userPrenomField;
	private JTextField userLoginField;
	private JPasswordField userPwdField;

	public JTextField getServerPortField() {
		return serverPortField;
	}

	public JTextField getServerField() {
		return serverField;
	}

    /**
     * se connecter au panel
     **/
	public ConnectionPanel() {

		JPanel connectionPanel = new JPanel(false);
		connectionPanel.setLayout(new BoxLayout(connectionPanel, BoxLayout.X_AXIS));

		JPanel namePanel = new JPanel(false);
		namePanel.setLayout(new GridLayout(0, 1));

		JPanel fieldPanel = new JPanel(false);
		fieldPanel.setLayout(new GridLayout(0, 1));
		JLabel userNomLabel = new JLabel("Nom Utilisateur : ", JLabel.RIGHT);
		userNomField = new JTextField("NomUser");
		JLabel userPrenomLabel = new JLabel("Prénom Utilisateur : ", JLabel.RIGHT);
		userPrenomField = new JTextField("PrenomUser");
		JLabel userLoginLabel = new JLabel("Login Utilisateur : ", JLabel.RIGHT);
		userLoginField = new JTextField("guest");
		JLabel userPwdLabel = new JLabel("Mot de passe : ", JLabel.RIGHT);
		userPwdField = new JPasswordField("trustworthy");
		JLabel serverLabel = new JLabel("Nom du Serveur :", JLabel.RIGHT);
		serverField = new JTextField("localhost");
		JLabel serverPortLabel = new JLabel("Port : ", JLabel.RIGHT);
		serverPortField = new JTextField("4567");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap(1004, Short.MAX_VALUE)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(userNomLabel)
										.addComponent(userPrenomLabel)
										.addComponent(userLoginLabel)
										.addComponent(userPwdLabel)
										.addGroup(
												groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(serverPortLabel).addComponent(serverLabel))
														.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING).addComponent(namePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(userPwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(serverField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(serverPortField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(userNomField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addComponent(userPrenomField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addComponent(userLoginField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.RELATED)
										
						.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addGroup(
																groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(userNomLabel)
																		.addComponent(userNomField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(userPrenomLabel)
																		.addComponent(userPrenomField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(userLoginLabel)
																		.addComponent(userLoginField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(
																				groupLayout.createSequentialGroup().addGap(3)
																						.addComponent(namePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(Alignment.BASELINE)
																										.addComponent(userPwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE).addComponent(userPwdLabel))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(Alignment.BASELINE)
																										.addComponent(serverField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE).addComponent(serverLabel))))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(
																groupLayout.createParallelGroup(Alignment.BASELINE)
																		.addComponent(serverPortField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(serverPortLabel)))
										.addGroup(
												groupLayout.createSequentialGroup().addGap(5)
														.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(202, Short.MAX_VALUE)));
		setLayout(groupLayout);
		setPreferredSize(new Dimension(300, 200));
	}

	public JTextField getUserLoginField() {
		return userLoginField;
	}

	public JPasswordField getPwdField() {
		return userPwdField;
	}

	public JTextField getNomField() {
		return userNomField;
	}

	public JTextField getPrenomField() {
		return userPrenomField;
	}
}
