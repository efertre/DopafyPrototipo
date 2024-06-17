package view.user.settings;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import dbm.DataBase;
import view.user.DataUser;

public class DiaChangePassword extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JCheckBox showPasswordCheckBox;
	private JButton changePasswordButton;
	private JButton cancelButton;
	private boolean passwordChanged = false;

	public DiaChangePassword(PanSettings panSettings) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/icon.png"));
		setTitle("Cambiar contraseña");
		initComponents();
		setupListeners();
		pack();
		setLocationRelativeTo(panSettings);
	}

	private void initComponents() {
		oldPasswordField = new JPasswordField(20);
		newPasswordField = new JPasswordField(20);
		confirmPasswordField = new JPasswordField(20);

		showPasswordCheckBox = new JCheckBox("Mostrar contraseñas");
		showPasswordCheckBox.setMnemonic(KeyEvent.VK_M);

		changePasswordButton = new JButton("Cambiar");
		cancelButton = new JButton("Cancelar");

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Contraseña Antigua:"), constraints);

		constraints.gridx = 1;
		panel.add(oldPasswordField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(new JLabel("Nueva Contraseña:"), constraints);

		constraints.gridx = 1;
		panel.add(newPasswordField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(new JLabel("Confirmar Nueva Contraseña:"), constraints);

		constraints.gridx = 1;
		panel.add(confirmPasswordField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		panel.add(showPasswordCheckBox, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		panel.add(changePasswordButton, constraints);

		constraints.gridx = 1;
		panel.add(cancelButton, constraints);

		getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void setupListeners() {
		showPasswordCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox.isSelected()) {
					oldPasswordField.setEchoChar((char) 0);
					newPasswordField.setEchoChar((char) 0);
					confirmPasswordField.setEchoChar((char) 0);
				} else {
					oldPasswordField.setEchoChar('*');
					newPasswordField.setEchoChar('*');
					confirmPasswordField.setEchoChar('*');
				}
			}
		});

		changePasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleChangePassword();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void handleChangePassword() {
		String oldPassword = new String(oldPasswordField.getPassword());
		String newPassword = new String(newPasswordField.getPassword());
		String confirmPassword = new String(confirmPasswordField.getPassword());

		if (!newPassword.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "La nueva contraseña y la confirmación no coinciden.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!validateOldPassword(oldPassword)) {
			JOptionPane.showMessageDialog(this, "La contraseña actual no es correcta.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (updatePassword(newPassword)) {
			JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Error al cambiar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean updatePassword(String newPassword) {
		String currentUsername = DataUser.loggedInUsername;
		String sql = "UPDATE USUARIO SET password = ? WHERE username = ?";
		boolean isUpdated = false;

		try {
			DataBase.open();
			DataBase.initTransacction();

			int rowsAffected = DataBase.executePreparedDML(sql, newPassword, currentUsername);
			if (rowsAffected > 0) {
				isUpdated = true;
				DataBase.commitTransacction();
			} else {
				DataBase.rollbackTransacction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				DataBase.rollbackTransacction();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			DataBase.close();
		}

		return isUpdated;
	}

	private boolean validateOldPassword(String oldPassword) {
		String currentUsername = DataUser.loggedInUsername;
		String sql = "SELECT password FROM USUARIO WHERE username = ?";
		boolean isValid = false;

		try {
			DataBase.open();
			ResultSet rs = DataBase.executePreparedQuery(sql, currentUsername);
			if (rs.next()) {
				String storedPassword = rs.getString("password");
				// Validación para comprobar si la contraseña es exactamente a la guardada en la
				// base de datos (sensibilidad a la capitalización)
				if (oldPassword.equals(storedPassword)) {

					isValid = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}

		return isValid;
	}

	public boolean isPasswordChanged() {
		return passwordChanged;
	}
}
