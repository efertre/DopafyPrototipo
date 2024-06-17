package view.login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Usuario;
import view.CtrlPrincipal;
import view.FrmPrincipal;
import view.user.DataUser;

public class PanLogin extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton btnLogin;

	private JLabel background;
	private JLabel lblExit;
	private JLabel lblUserName;
	public static JTextField textUsername;
	private JLabel lblUserPass;
	private JPasswordField textPassword;
	private JLabel lblTextRegister;

	private JCheckBox chkShowPassword;

	private FrmPrincipal mainFrame;
	private CtrDiaLogin ctrlDiaLogin;

	public PanLogin(FrmPrincipal frmPrincipal) {
		this.mainFrame = frmPrincipal;
		this.ctrlDiaLogin = new CtrDiaLogin();

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

		// Crear etiqueta para navegar hacia el panel de Register
		lblTextRegister = new JLabel("¿No tienes una cuenta?");
		lblTextRegister.setBounds(270, 300, 141, 13);
		add(lblTextRegister);

		// Crear el JLabel con imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 900, 500);
		add(background);

		// Crear el panel para los campos de inicio de sesión
		JPanel loginPanel = new JPanel(null); // Usamos un layout nulo para posicionar los componentes dentro de este
		// panel
		loginPanel.setOpaque(false); // Hacer el panel transparente para que se vea la imagen de fondo
		loginPanel.setBounds(300, 150, 300, 200);
		background.add(loginPanel);
		ImageIcon iconLogin = new ImageIcon("resources/images/buttons/BTN_INICIAR_SESION.png");

		// Crear icono User y Password
		ImageIcon iconUser = new ImageIcon("resources/images/icons/ICON_USER.png");
		ImageIcon iconPassword = new ImageIcon("resources/images/icons/ICON_PWD.png");

		lblUserName = new JLabel("");
		lblUserName.setIcon(iconUser);
		lblUserName.setToolTipText("Introduce tu nombre de usuario");
		lblUserName.setBounds(70, 25, 16, 16);
		loginPanel.add(lblUserName);

		textUsername = new JTextField();
		textUsername.setToolTipText("Introduce tu nombre de usuario");
		textUsername.setBounds(100, 20, 180, 30);
		loginPanel.add(textUsername);

		lblUserPass = new JLabel("");
		lblUserPass.setIcon(iconPassword);
		lblUserPass.setToolTipText("Introduce tu contraseña");
		lblUserPass.setBounds(70, 65, 16, 16);
		loginPanel.add(lblUserPass);

		textPassword = new JPasswordField();
		textPassword.setToolTipText("Introduce tu contraseña");
		textPassword.setBounds(100, 60, 180, 30);
		loginPanel.add(textPassword);

		// Checkbox para mostrar/ocultar contraseña
		chkShowPassword = new JCheckBox("Mostrar contraseña");
		chkShowPassword.setOpaque(false);
		chkShowPassword.setBounds(140, 100, 140, 30);
		loginPanel.add(chkShowPassword);

		// Botón iniciar sesión
		btnLogin = new JButton("");
		btnLogin.setIcon(iconLogin);
		btnLogin.setBounds(120, 140, 160, 40);
		loginPanel.add(btnLogin);
	}

	private void addListeners() {
		chkShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkShowPassword.isSelected()) {
					textPassword.setEchoChar((char) 0); // Mostrar texto plano
				} else {
					textPassword.setEchoChar('\u2022'); // Ocultar texto y mostrar puntos
				}
			}
		});
		// Acción del botón de login
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = new String(textPassword.getPassword());

				try {
					Optional<Usuario> user = ctrlDiaLogin.authenticate(username, password);
					if (user.isPresent()) {
						Usuario usuario = user.get();
						DataUser.loggedInUsername = username;
						

						if (usuario.getEsAdmin()) {
							mainFrame.showPanel("PanAdmin");
						} else {
							mainFrame.showPanel("PanUser");

						}
					} else {
						// Mostrar mensaje de error
						JOptionPane.showMessageDialog(PanLogin.this, "Nombre de usuario o contraseña no válido.",
								"Error de autenticación", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		// Acción para redirigir al registro
		lblTextRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.showPanel("PanRegister");
			}
		});

		CtrlPrincipal ctrl = new CtrlPrincipal();

		// Agregar el listener para el movimiento del ratón
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				ctrl.animateBackground(e, PanLogin.this);
			}
		});

		// Controlar salida del programa
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.exitQuestion(PanLogin.this);
			}
		});

		// Controlar la etiqueta "¿No tienes una cuenta?"
		lblTextRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTextRegister.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblTextRegister.setForeground(Color.BLACK);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblTextRegister.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.showPanel("PanRegister");
			}
		});
	}
}
