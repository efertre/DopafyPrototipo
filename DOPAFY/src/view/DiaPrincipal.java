package view;

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
import javax.swing.JPanel;

import view.login.DiaLogin;
import view.register.DiaRegister;

public class DiaPrincipal extends JDialog {
	private static final long serialVersionUID = 1L;

	public DiaPrincipal(JFrame context) {
		super(context, "Inicio", true);
		
		// Controlar el cerrado de esta ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setSize(300, 200);
		setLocationRelativeTo(context);
		setResizable(false);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 250, 240));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		getContentPane().add(panelPrincipal);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(255, 250, 240));
		panelBotones.setLayout(new GridLayout(2, 1, 10, 10));
		panelPrincipal.add(panelBotones, BorderLayout.CENTER);

		JButton btnLogin = new JButton("Iniciar Sesi\u00F3n");
		btnLogin.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Cerrar el diálogo actual
				new DiaLogin(context); // Abrir el diálogo de inicio de sesión

			}
		});
		panelBotones.add(btnLogin);

		JButton btnRegister = new JButton("Registrarse");
		btnRegister.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Cerrar el diálogo actual
				new DiaRegister(context); // Abrir el diálogo de registro

			}
		});
		panelBotones.add(btnRegister);

		setVisible(true);
	}

}
