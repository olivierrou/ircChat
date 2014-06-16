package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.cfranc.irc.impl.DbSingleton;
import com.cfranc.irc.impl.UserImpl;

/**
 * <code>SimpleChatClientApp</code> Frame de connection Client.
 * 
 * @beaninfo description: Permet au client de se connecter.
 * 
 * @author Bertrand Wittmer
 */

public class UserCompteFrame {

	public JDialog frame;
	private JTextField userLoginField;
	private JTextField userPwdField;
	private JTextField nomField;
	private JTextField prenomField;
	private UserImpl userConnect = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCompteFrame window = new UserCompteFrame();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JDialog getFrame() {
		return frame;
	}

	public UserImpl getUserConnect() {
		return userConnect;
	}

	/**
	 * Create the application.
	 */
	public UserCompteFrame() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 357, 257);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelBtn = new JPanel();
		frame.getContentPane().add(panelBtn, BorderLayout.SOUTH);

		JButton validerBtn = new JButton(new Connecter("Connecter"));
		validerBtn.setText("Valider");
		JButton annulerBtn = new JButton(new Annuler("Annuler"));
		annulerBtn.setText("Annuler");
		panelBtn.add(validerBtn);
		panelBtn.add(annulerBtn);

		JPanel panelField = new JPanel();
		frame.getContentPane().add(panelField, BorderLayout.CENTER);
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

		userPwdField = new JTextField("");
		GridBagConstraints gbc_userPwdField = new GridBagConstraints();
		gbc_userPwdField.insets = new Insets(0, 0, 5, 5);
		gbc_userPwdField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userPwdField.gridx = 2;
		gbc_userPwdField.gridy = 5;
		panelField.add(userPwdField, gbc_userPwdField);
		userPwdField.setColumns(10);

		JLabel motpasseLabel = new JLabel("Nom : ");
		GridBagConstraints gbc_motpasseLabel = new GridBagConstraints();
		gbc_motpasseLabel.anchor = GridBagConstraints.EAST;
		gbc_motpasseLabel.insets = new Insets(0, 0, 5, 5);
		gbc_motpasseLabel.gridx = 1;
		gbc_motpasseLabel.gridy = 6;
		panelField.add(motpasseLabel, gbc_motpasseLabel);

		nomField = new JTextField("");
		GridBagConstraints gbc_nomField = new GridBagConstraints();
		gbc_nomField.insets = new Insets(0, 0, 5, 5);
		gbc_nomField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomField.gridx = 2;
		gbc_nomField.gridy = 6;
		panelField.add(nomField, gbc_nomField);
		nomField.setColumns(10);

		JLabel prenomLabel = new JLabel("Pr\u00E9nom :");
		GridBagConstraints gbc_prenomLabel = new GridBagConstraints();
		gbc_prenomLabel.anchor = GridBagConstraints.EAST;
		gbc_prenomLabel.insets = new Insets(0, 0, 5, 5);
		gbc_prenomLabel.gridx = 1;
		gbc_prenomLabel.gridy = 7;
		panelField.add(prenomLabel, gbc_prenomLabel);

		prenomField = new JTextField("");
		GridBagConstraints gbc_prenomField = new GridBagConstraints();
		gbc_prenomField.insets = new Insets(0, 0, 5, 5);
		gbc_prenomField.fill = GridBagConstraints.HORIZONTAL;
		gbc_prenomField.gridx = 2;
		gbc_prenomField.gridy = 7;
		panelField.add(prenomField, gbc_prenomField);
		prenomField.setColumns(10);
		
		JLabel lblAvatar = new JLabel("Avatar :");
		GridBagConstraints gbc_lblAvatar = new GridBagConstraints();
		gbc_lblAvatar.anchor = GridBagConstraints.EAST;
		gbc_lblAvatar.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvatar.gridx = 1;
		gbc_lblAvatar.gridy = 8;
		panelField.add(lblAvatar, gbc_lblAvatar);
	}

	/********************* Action des textField ********************/
	public String getUserLoginField() {
		return userLoginField.getText();
	}

	public String getUserPwdField() {
		return userPwdField.getText();
	}

	/********************* Action des boutons *********************/
	public class Connecter extends AbstractAction {
		public Connecter(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
//			// Se connecter à la Base
//			if (!(DbSingleton.getInstance().connectSqlLite("db/ircdb.sqlite"))) {
//				return;
//			}
			;
			// Vérifier les saisies
			if (getUserLoginField() == "") {
				JOptionPane.showMessageDialog(null,
						"Vous devez saisir un Login.", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			userConnect = new UserImpl(userLoginField.getText(), userPwdField.getText(), nomField.getText(), prenomField.getText());
			userConnect.enregistrer();
			
			// Charger le compte de l'utilisateur
			userConnect.charger(userConnect.getLogin(), userConnect.getPwd());
			frame.setVisible(false);
			;
		}

	}

	public class Annuler extends AbstractAction {
		public Annuler(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
}
