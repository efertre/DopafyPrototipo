package view.register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.CtrlPrincipal;
import view.FrmPrincipal;

public class PanRegister extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField txtUserName, txtUserPass, txtConfirmPass, txtEmail;

	private JButton btnRegister;
	private JLabel lblTextLogin;

	private JLabel background;
	private JLabel lblExit;

	private FrmPrincipal mainFrame;

	public PanRegister(FrmPrincipal FrmPrincipal) {
		this.mainFrame = FrmPrincipal;

		setLayout(null); // Usamos un layout nulo para posicionar los componentes manualmente

		addComponents();
		addListeners();

		// Revalidar y repintar el panel
		revalidate();
		repaint();
	}

	private void addComponents() {
		// Cargar la imagen de fondo
		ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");

		// X Para salir
		lblExit = new JLabel("X");
		lblExit.setBounds(873, 0, 30, 30);
		add(lblExit);

		lblTextLogin = new JLabel("¿Ya tienes una cuenta?");

		lblTextLogin.setBounds(310, 302, 141, 13);
		add(lblTextLogin);

		// Crear el JLabel con imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 900, 500);
		add(background);

		// Crear el panel para los campos de registro
		JPanel registerPanel = new JPanel(null); // Usamos un layout nulo para posicionar los componentes dentro de este
													// panel
		registerPanel.setOpaque(false); // Hacer el panel transparente para que se vea la imagen de fondo
		registerPanel.setBounds(300, 100, 300, 300);
		background.add(registerPanel); // Añadir el panel de registro al panel de fondo

		// Crear y posicionar los componentes dentro del panel de registro
		// Crear los iconos para las etiquetas
		ImageIcon iconUser = new ImageIcon("resources/images/icons/ICON_USER.png");
		ImageIcon iconMail = new ImageIcon("resources/images/icons/ICON_MAIL.png");
		ImageIcon iconPwd = new ImageIcon("resources/images/icons/ICON_PWD.png");
		ImageIcon iconConfPwd = new ImageIcon("resources/images/icons/ICON_CONF_PWD.png");

		JLabel lblUserName = new JLabel("");
		lblUserName.setToolTipText("Introduce un nombre de usuario");
		lblUserName.setIcon(iconUser);
		lblUserName.setBounds(60, 16, 16, 16);
		registerPanel.add(lblUserName);

		txtUserName = new JTextField();
		txtUserName.setToolTipText("Introduce un nombre de usuario");
		txtUserName.setBounds(100, 10, 180, 30);
		registerPanel.add(txtUserName);

		JLabel lblEmail = new JLabel("");
		lblEmail.setToolTipText("Introduce tu correo electr\u00F3nico");
		lblEmail.setIcon(iconMail);
		lblEmail.setBounds(60, 58, 16, 16);
		registerPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setToolTipText("Introduce tu correo electr\u00F3nico");
		txtEmail.setBounds(100, 50, 180, 30);
		registerPanel.add(txtEmail);

		JLabel lblUserPass = new JLabel("");
		lblUserPass.setToolTipText("Introduce una contrase\u00f1a");
		lblUserPass.setIcon(iconPwd);
		lblUserPass.setBounds(60, 98, 16, 16);
		registerPanel.add(lblUserPass);

		txtUserPass = new JTextField();
		txtUserPass.setToolTipText("Introduce una contrase\u00f1a");
		txtUserPass.setBounds(100, 90, 180, 30);
		registerPanel.add(txtUserPass);

		JLabel lblConfirmPass = new JLabel("");
		lblConfirmPass.setToolTipText("Confirma tu contrase\u00f1a");
		lblConfirmPass.setIcon(iconConfPwd);
		lblConfirmPass.setBounds(60, 138, 16, 16);
		registerPanel.add(lblConfirmPass);

		txtConfirmPass = new JTextField();
		txtConfirmPass.setToolTipText("Confirma tu contrase\u00f1a");
		txtConfirmPass.setBounds(100, 130, 180, 30);
		registerPanel.add(txtConfirmPass);

		btnRegister = new JButton("");
		ImageIcon iconRegister = new ImageIcon("resources/images/buttons/BTN_REGISTER.png");
		btnRegister.setIcon(iconRegister);
		btnRegister.setBounds(156, 190, 120, 40);
		registerPanel.add(btnRegister);
	}

	private void addListeners() {
		CtrlPrincipal ctrl = new CtrlPrincipal();
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí se debe agregar la lógica para registrar
			}
		});

		// Agregar el listener para el movimiento del ratón
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				ctrl.animateBackground(e, PanRegister.this);
			}
		});

		// Controlar salida del programa
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.exitQuestion(PanRegister.this);
			}
		});

		// Controlar la etiqueta "¿Ya tienes una cuenta?"
		lblTextLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTextLogin.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblTextLogin.setForeground(Color.BLACK);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblTextLogin.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.showPanel("PanLogin");

			}
		});
	}
}
