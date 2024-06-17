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

/**
 * Di\u00E1logo para cambiar la contrase\u00F1a del usuario.
 */
public class DiaChangePassword extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPasswordField oldPasswordField, newPasswordField, confirmPasswordField;

	private JCheckBox showPasswordCheckBox;
	private JButton changePasswordButton;
	private JButton cancelButton;
	private boolean passwordChanged = false;

	/**
	 * Constructor para inicializar el di\u00E1logo de cambio de contrase\u00F1a.
	 * 
	 * @param panSettings Panel de configuraci\u00F3n asociado para ubicar el
	 *                    di\u00E1logo.
	 */
	public DiaChangePassword(PanSettings panSettings) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/icon.png"));
		setTitle("Cambiar contrase\u00F1a");
		initComponents();
		setupListeners();
		pack();
		setLocationRelativeTo(panSettings);
	}

	/**
	 * Inicializa los componentes visuales del di\u00E1logo.
	 */
	private void initComponents() {
		oldPasswordField = new JPasswordField(20);
		newPasswordField = new JPasswordField(20);
		confirmPasswordField = new JPasswordField(20);

		showPasswordCheckBox = new JCheckBox("Mostrar contrase\u00F1as");
		showPasswordCheckBox.setMnemonic(KeyEvent.VK_M);

		changePasswordButton = new JButton("Cambiar");
		cancelButton = new JButton("Cancelar");

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(new JLabel("Contrase\u00F1a Antigua:"), constraints);

		constraints.gridx = 1;
		panel.add(oldPasswordField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(new JLabel("Nueva Contrase\u00F1a:"), constraints);

		constraints.gridx = 1;
		panel.add(newPasswordField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(new JLabel("Confirmar Nueva Contrase\u00F1a:"), constraints);

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

	/**
	 * Configura los escuchadores de eventos para los componentes del di\u00E1logo.
	 */
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

	/**
	 * Maneja la acci\u00F3n de cambio de contrase\u00F1a. Valida las contrase\u00F1as
	 * ingresadas y realiza la actualizaci\u00F3n en la base de datos.
	 */
	private void handleChangePassword() {
		String oldPassword = new String(oldPasswordField.getPassword());
		String newPassword = new String(newPasswordField.getPassword());
		String confirmPassword = new String(confirmPasswordField.getPassword());

		if (!newPassword.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "La nueva contrase\u00F1a y la confirmación no coinciden.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!validateOldPassword(oldPassword)) {
			JOptionPane.showMessageDialog(this, "La contrase\u00F1a actual no es correcta.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (updatePassword(newPassword)) {
			JOptionPane.showMessageDialog(this, "Contrase\u00F1a cambiada exitosamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Error al cambiar la contrase\u00F1a.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Actualiza la contrase\u00F1a del usuario en la base de datos.
	 * 
	 * @param newPassword Nueva contrase\u00F1a a establecer.
	 * @return true si la contrase\u00F1a se actualiz\u00F3 correctamente, false si
	 *         ocurri\u00F3 un error.
	 */
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

			}
		} finally {
			DataBase.close();
		}

		return isUpdated;
	}

	/**
	 * Valida la contrase\u00F1a actual del usuario con la almacenada en la base de
	 * datos.
	 * 
	 * @param oldPassword Contrase\u00F1a actual ingresada por el usuario.
	 * @return true si la contrase\u00F1a actual es v\u00E1lida, false si no lo es.
	 */
	private boolean validateOldPassword(String oldPassword) {
		String currentUsername = DataUser.loggedInUsername;
		String sql = "SELECT password FROM USUARIO WHERE username = ?";
		boolean isValid = false;

		try {
			DataBase.open();
			ResultSet rs = DataBase.executePreparedQuery(sql, currentUsername);
			if (rs.next()) {
				String storedPassword = rs.getString("password");
				// Validaci\u00F3n para comprobar si la contrase\u00F1a es exactamente a la
				// guardada en la base de datos (sensibilidad a la capitalizaci\u00F3n)
				if (oldPassword.equals(storedPassword)) {
					isValid = true;
				}
			}
		} catch (Exception e) {

		} finally {
			DataBase.close();
		}

		return isValid;
	}

	/**
	 * Verifica si la contrase\u00F1a fue cambiada exitosamente.
	 * 
	 * @return true si la contrase\u00F1a fue cambiada, false si no.
	 */
	public boolean isPasswordChanged() {
		return passwordChanged;
	}
}
