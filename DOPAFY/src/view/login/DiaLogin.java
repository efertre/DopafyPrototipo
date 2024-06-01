package view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.DiaPrincipal;

public class DiaLogin extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JPasswordField txtUserPass;

	public DiaLogin(JFrame context) {
		getContentPane().setBackground(new Color(255, 250, 240));
		setTitle("Iniciar Sesi\u00F3n");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelPrincipal.setBackground(new Color(255, 250, 240));
		getContentPane().add(panelPrincipal);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(new Color(255, 250, 240));
		panelDatos.setLayout(new GridLayout(2, 2, 10, 10));
		panelDatos.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);

		JLabel lblUserName = new JLabel("Usuario:");
		lblUserName.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panelDatos.add(lblUserName);

		txtUserName = new JTextField();
		panelDatos.add(txtUserName);

		JLabel lblUserPass = new JLabel("Contrase\u00F1a:");
		lblUserPass.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panelDatos.add(lblUserPass);

		txtUserPass = new JPasswordField();
		panelDatos.add(txtUserPass);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(255, 250, 240));
		panelBotones.setLayout(new GridLayout(1, 1, 10, 10));
		panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

		JButton btnLogin = new JButton("Iniciar Sesi\u00F3n");
		btnLogin.setBackground(new Color(30, 144, 255)); // Azul
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFocusPainted(false);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Realizar el inicio de sesi\u00F3n aqu\u00ED
				dispose(); // Cerrar el di\u00E1logo despu\u00E9s del inicio de sesi\u00F3n

			}
		});

		// Controlar el cerrado
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new DiaPrincipal(context);
			}
		});
		
		panelBotones.add(btnLogin);

		setVisible(true);
	}
}
