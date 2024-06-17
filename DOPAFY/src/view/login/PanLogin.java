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

/**
 * Panel de inicio de sesi\u00F3n donde los usuarios pueden ingresar sus
 * credenciales. Permite autenticar a los usuarios y redirigirlos a diferentes
 * paneles según su rol.
 */
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
	private CtrlDiaLogin ctrlDiaLogin;

	/**
	 * Constructor de PanLogin.
	 * 
	 * @param frmPrincipal El frame principal donde se encuentra este panel.
	 */
	public PanLogin(FrmPrincipal frmPrincipal) {
		this.mainFrame = frmPrincipal;
		this.ctrlDiaLogin = new CtrlDiaLogin();

		setLayout(null); // Layout nulo para posicionamiento manual de componentes

		addComponents(); // Agregar los componentes visuales al panel
		addListeners(); // Agregar los listeners para manejar eventos

		// Revalidar y repintar el panel
		revalidate();
		repaint();
	}

	/**
	 * Método privado para agregar todos los componentes visuales al panel.
	 */
	private void addComponents() {
		// Cargar la imagen de fondo
		ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");

		// Bot\u00F3n para salir (X)
		lblExit = new JLabel("X");
		lblExit.setBounds(873, 0, 30, 30);
		add(lblExit);

		// Etiqueta para navegar hacia el panel de registro
		lblTextRegister = new JLabel("¿No tienes una cuenta?");
		lblTextRegister.setBounds(270, 300, 141, 13);
		add(lblTextRegister);

		// JLabel con imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 900, 500);
		add(background);

		// Panel para los campos de inicio de sesi\u00F3n
		JPanel loginPanel = new JPanel(null); // Layout nulo para posicionamiento manual
		loginPanel.setOpaque(false); // Hacer el panel transparente para mostrar la imagen de fondo
		loginPanel.setBounds(300, 150, 300, 200);
		background.add(loginPanel);

		// Iconos para usuario y contrase\u00F1a
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
		lblUserPass.setToolTipText("Introduce tu contrase\u00F1a");
		lblUserPass.setBounds(70, 65, 16, 16);
		loginPanel.add(lblUserPass);

		textPassword = new JPasswordField();
		textPassword.setToolTipText("Introduce tu contrase\u00F1a");
		textPassword.setBounds(100, 60, 180, 30);
		loginPanel.add(textPassword);

		// Checkbox para mostrar/ocultar contrase\u00F1a
		chkShowPassword = new JCheckBox("Mostrar contrase\u00F1a");
		chkShowPassword.setOpaque(false);
		chkShowPassword.setBounds(140, 100, 140, 30);
		loginPanel.add(chkShowPassword);

		// Bot\u00F3n para iniciar sesi\u00F3n
		ImageIcon iconLogin = new ImageIcon("resources/images/buttons/BTN_INICIAR_SESION.png");
		btnLogin = new JButton("");
		btnLogin.setIcon(iconLogin);
		btnLogin.setBounds(120, 140, 160, 40);
		loginPanel.add(btnLogin);
	}

	/**
	 * M\u00E9todo privado para agregar todos los listeners a los componentes.
	 */
	private void addListeners() {
		// Listener para mostrar/ocultar contrase\u00F1a
		chkShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkShowPassword.isSelected()) {
					textPassword.setEchoChar((char) 0); // Mostrar texto plano
				} else {
					textPassword.setEchoChar('\u2022'); // Ocultar texto y mostrar puntos
				}
			}
		});

		// Listener para el bot\u00F3n de iniciar sesi\u00F3n
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = new String(textPassword.getPassword());

				try {
					Optional<Usuario> user = ctrlDiaLogin.authenticate(username, password);
					if (user.isPresent()) {
						Usuario usuario = user.get();
						if (usuario.getEsAdmin()) {
							mainFrame.showPanel("PanAdmin"); // Mostrar panel de administrador
						} else {
							mainFrame.showPanel("PanUser"); // Mostrar panel de usuario normal
						}
					} else {
						// Mostrar mensaje de error si las credenciales son incorrectas
						JOptionPane.showMessageDialog(PanLogin.this, "Nombre de usuario o contraseña no válido.",
								"Error de autenticación", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace(); // Imprimir la traza de la excepci\u00F3n en caso de error
				}
			}
		});

		// Listener para redirigir al panel de registro al hacer clic en "¿No tienes una
		// cuenta?"
		lblTextRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.showPanel("PanRegister"); // Mostrar panel de registro
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblTextRegister.setForeground(Color.WHITE); // Cambiar color al pasar el mouse
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblTextRegister.setForeground(Color.BLACK); // Restaurar color al salir del \u00E1rea
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblTextRegister.setForeground(Color.GRAY); // Cambiar color al presionar el bot\u00F3n
			}
		});

		// Listener para el movimiento del rat\u00F3n
		CtrlPrincipal ctrl = new CtrlPrincipal();
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// No se utiliza para esta implementaci\u00F3n
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				ctrl.animateBackground(e, PanLogin.this); // Llamar al m\u00E9todo para animar el fondo
			}
		});

		// Listener para la salida del programa
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.exitQuestion(PanLogin.this); // Llamar al m\u00E9todo para preguntar antes de salir
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
