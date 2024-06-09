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

	private JLabel lblExit;

	// Frame padre
	FrmPrincipal mainFrame;
	private final JLabel lblMenu = new JLabel("");
	private JLabel lblUserInfo;
	private JLabel lblWindow;
	private JLabel lblUserImage;
	private JLabel lblUserStatus;
	private JLabel lblUsername;

	// Etiquetas para los iconos del menu
	private JLabel lblIconProfile, lblIconGoals, lblIconRewards, lblIconSettings;

	// Etiquetas para los textos del menu
	private JLabel lblProfile, lblObjetivos, lblRewards, lblSettings;

	// Opciones del menu
	private JPanel panMenu, panMenu_1, panMenu_2, panMenu_3;

	// Paneles de contenido
	private JPanel panEstadisticas, panObjetivos, panPremios, panAjustes;
	private JLabel lblNewLabel;
	private JLabel lblObjetivos_1;
	private JLabel lblObjetivos_3;
	private JLabel lblWindowText;
	private JLabel lblNewLabel_1;

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

		// Cargar la imagen del icono de perfil
		ImageIcon iconProfile = new ImageIcon("resources/images/icons/ICON_USER.png");

		// Cargar la imagen del icono de objetivos
		ImageIcon iconGoals = new ImageIcon("resources/images/icons/ICON_GOALS.png");

		// Cargar la imagen del icono de premios
		ImageIcon iconRewards = new ImageIcon("resources/images/icons/ICON_REWARDS.png");

		// Cargar la imagen del icono de ajustes
		ImageIcon iconSettings = new ImageIcon("resources/images/icons/ICON_SETTINGS.png");

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

		lblWindowText = new JLabel("ESTADÍSTICAS");
		lblWindowText.setFont(new Font("Arial", Font.BOLD, 22));
		lblWindowText.setBackground(new Color(204, 255, 204));
		lblWindowText.setBounds(243, 0, 200, 50);
		add(lblWindowText);

		lblWindow = new JLabel("");
		lblWindow.setIcon(iconWindow);
		lblWindow.setBackground(new Color(204, 255, 204));
		lblWindow.setBounds(200, 0, 700, 50);
		add(lblWindow);

		panMenu = new JPanel();
		panMenu.setLayout(null);
		panMenu.setOpaque(false);
		panMenu.setBounds(0, 150, 200, 40);
		add(panMenu);

		lblIconProfile = new JLabel("");
		lblIconProfile.setIcon(iconProfile);
		lblIconProfile.setBounds(20, 12, 16, 16);
		panMenu.add(lblIconProfile);

		lblProfile = new JLabel("ESTADÍSTICAS");
		lblProfile.setFont(new Font("Arial", Font.BOLD, 14));
		lblProfile.setBounds(50, 13, 120, 13);
		panMenu.add(lblProfile);

		panMenu_1 = new JPanel();
		panMenu_1.setLayout(null);
		panMenu_1.setOpaque(false);
		panMenu_1.setBounds(0, 200, 200, 40);
		add(panMenu_1);

		lblIconGoals = new JLabel("");
		lblIconGoals.setIcon(iconGoals);
		lblIconGoals.setBounds(20, 12, 16, 16);
		panMenu_1.add(lblIconGoals);

		lblObjetivos = new JLabel("OBJETIVOS");
		lblObjetivos.setFont(new Font("Arial", Font.BOLD, 14));
		lblObjetivos.setBounds(50, 13, 120, 13);
		panMenu_1.add(lblObjetivos);

		panMenu_2 = new JPanel();
		panMenu_2.setLayout(null);
		panMenu_2.setOpaque(false);
		panMenu_2.setBounds(0, 250, 200, 40);
		add(panMenu_2);

		lblIconRewards = new JLabel("");
		lblIconRewards.setIcon(iconRewards);
		lblIconRewards.setBounds(20, 12, 16, 16);
		panMenu_2.add(lblIconRewards);

		lblRewards = new JLabel("PREMIOS");
		lblRewards.setFont(new Font("Arial", Font.BOLD, 14));
		lblRewards.setBounds(50, 13, 120, 13);
		panMenu_2.add(lblRewards);

		panMenu_3 = new JPanel();
		panMenu_3.setLayout(null);
		panMenu_3.setOpaque(false);
		panMenu_3.setBounds(0, 300, 200, 40);
		add(panMenu_3);

		lblIconSettings = new JLabel("");
		lblIconSettings.setIcon(iconSettings);
		lblIconSettings.setBounds(20, 12, 16, 16);
		panMenu_3.add(lblIconSettings);

		lblSettings = new JLabel("AJUSTES");
		lblSettings.setFont(new Font("Arial", Font.BOLD, 14));
		lblSettings.setBounds(50, 13, 120, 13);
		panMenu_3.add(lblSettings);

		lblMenu.setIcon(iconMenu);
		lblMenu.setBackground(new Color(204, 255, 204));
		lblMenu.setBounds(0, 0, 200, 500);
		add(lblMenu);

		// Crear paneles de contenido
		panEstadisticas = new JPanel();
		panEstadisticas.setBackground(Color.WHITE);
		panEstadisticas.setBounds(200, 50, 700, 450);
		add(panEstadisticas);
		panEstadisticas.setLayout(null);

		lblNewLabel = new JLabel("PRÓXIMAMENTE");
		lblNewLabel.setBounds(290, 200, 140, 13);
		panEstadisticas.add(lblNewLabel);

		panObjetivos = new JPanel();
		panObjetivos.setBackground(Color.WHITE);
		panObjetivos.setBounds(200, 50, 700, 450);
		add(panObjetivos);

		lblObjetivos_1 = new JLabel("OBJETIVOS");
		panObjetivos.add(lblObjetivos_1);

		panPremios = new JPanel();
		panPremios.setBackground(Color.WHITE);
		panPremios.setBounds(200, 50, 700, 450);
		add(panPremios);
		panPremios.setLayout(null);
		
		lblNewLabel_1 = new JLabel("PRÓXIMAMENTE (necesita estar conectado)");
		lblNewLabel_1.setBounds(226, 200, 280, 13);
		panPremios.add(lblNewLabel_1);

		panAjustes = new JPanel();
		panAjustes.setBackground(Color.WHITE);
		panAjustes.setBounds(200, 50, 700, 450);
		add(panAjustes);

		lblObjetivos_3 = new JLabel("AJUSTES");
		panAjustes.add(lblObjetivos_3);

		// Solo mostrar el panel de estadísticas por defecto
		panEstadisticas.setVisible(true);
		panObjetivos.setVisible(false);
		panPremios.setVisible(false);
		panAjustes.setVisible(false);
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

		CtrlPanUser ctrlPU = new CtrlPanUser();

		// Controlar el hover en las opciones del menu
		ctrlPU.addMouseListenerToPanel(panMenu);
		ctrlPU.addMouseListenerToPanel(panMenu_1);
		ctrlPU.addMouseListenerToPanel(panMenu_2);
		ctrlPU.addMouseListenerToPanel(panMenu_3);

		// Controlar clics en las opciones del menu para cambiar el contenido
		panMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPanel(panEstadisticas, "ESTADÍSTICAS");
			}
		});
		panMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPanel(panObjetivos, "OBJETIVOS");
			}
		});
		panMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPanel(panPremios, "PREMIOS");
			}
		});
		panMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPanel(panAjustes, "AJUSTES");
			}
		});
	}

	private void showPanel(JPanel panel, String panelName) {
		panEstadisticas.setVisible(false);
		panObjetivos.setVisible(false);
		panPremios.setVisible(false);
		panAjustes.setVisible(false);
		panel.setVisible(true);
		lblWindowText.setText(panelName);
	}
}
