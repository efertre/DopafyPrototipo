package view.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.CtrlPrincipal;
import view.FrmPrincipal;

public class PanUser extends JPanel {

	private static final long serialVersionUID = 1L;

	// Label para colocar el fondo principal del panel
	private JLabel background;

	private JLabel lblExit;

	// Frame padre
	FrmPrincipal mainFrame;
	private final JLabel lblMenu = new JLabel("");
	private JLabel lblUserInfo;
	private JLabel lblWindow;
	private JLabel lblUserImage;
	private JLabel lblUserStatus;
	private JLabel lblUsername;

	public PanUser(FrmPrincipal frmPrincipal) {
		this.mainFrame = frmPrincipal;

		setLayout(null); // Usamos un layout nulo para posicionar los componentes manualmente

		addComponents();
		addListeners();

	}

	private void addComponents() {
		// X Para salir
		lblExit = new JLabel("X");
		lblExit.setBounds(873, 0, 30, 30);
		add(lblExit);

		// Cargar la imagen del menu
		ImageIcon iconMenu = new ImageIcon("resources/images/backgrounds/BG_MENU.png");

		// Cargar la imagen de fondo de la información de usuario
		ImageIcon iconUserInfo = new ImageIcon("resources/images/backgrounds/BG_USER_INFO.png");

		// Cargar la imagen de fondo del apartado de ventana
		ImageIcon iconWindow = new ImageIcon("resources/images/backgrounds/BG_WINDOW.png");

		// Cargar la imagen de fondo del apartado de ventana
		ImageIcon iconImgUser = new ImageIcon("resources/images/icons/IMG_USER.png");

		// Cargar la imagen del icono desconectado
		ImageIcon iconOffline = new ImageIcon("resources/images/icons/ICON_OFFLINE.png");

		lblUserImage = new JLabel("");
		lblUserImage.setIcon(iconImgUser);
		lblUserImage.setBackground(new Color(204, 255, 204));
		lblUserImage.setBounds(50, 20, 100, 70);
		add(lblUserImage);

		lblUsername = new JLabel("Nombre de usuario");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 10));
		lblUsername.setBounds(52, 98, 100, 10);
		add(lblUsername);

		lblUserStatus = new JLabel("Desconectado");
		lblUserStatus.setFont(new Font("Arial", Font.PLAIN, 10));
		lblUserStatus.setIcon(iconOffline);
		lblUserStatus.setBounds(60, 116, 100, 10);
		add(lblUserStatus);

		lblUserInfo = new JLabel("");
		lblUserInfo.setIcon(iconUserInfo);
		lblUserInfo.setBackground(new Color(204, 255, 204));
		lblUserInfo.setBounds(0, 0, 200, 140);
		add(lblUserInfo);

		lblWindow = new JLabel("");
		lblWindow.setIcon(iconWindow);
		lblWindow.setBackground(new Color(204, 255, 204));
		lblWindow.setBounds(200, 0, 700, 50);
		add(lblWindow);

		lblMenu.setIcon(iconMenu);

		lblMenu.setBackground(new Color(204, 255, 204));
		lblMenu.setBounds(0, 0, 200, 500);
		add(lblMenu);

	}

	private void addListeners() {
		CtrlPrincipal ctrl = new CtrlPrincipal();

		// Controlar salida del programa
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.exitQuestion(PanUser.this);
			}
		});

	}
}
