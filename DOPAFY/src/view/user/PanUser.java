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
import view.user.goals.PanGoals;
import view.user.rewards.PanRewards;
import view.user.settings.PanSettings;
import view.user.statistics.PanStatistics;

/**
 * Panel principal de usuario que muestra estad\u00EDsticas, objetivos, premios y
 * ajustes.
 */
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

	// Etiquetas para los iconos del menu
	private JLabel lblIconProfile, lblIconGoals, lblIconRewards, lblIconSettings;

	// Etiquetas para los textos del menu
	private JLabel lblProfile, lblObjetivos, lblRewards, lblSettings;

	// Opciones del menu
	private JPanel panMenu, panMenu_1, panMenu_2, panMenu_3;

	// Paneles de contenido
	private PanStatistics panEstadisticas;
	private PanGoals panObjetivos;
	private PanRewards panPremios;
	private PanSettings panAjustes;

	private JLabel lblWindowText;

	private JLabel lblUsername;

	/**
	 * Constructor de la clase PanUser.
	 *
	 * @param frmPrincipal Frame principal desde el cual se instanci\u00F3 este panel.
	 */
	public PanUser(FrmPrincipal frmPrincipal) {
		this.mainFrame = frmPrincipal;

		setLayout(null); // Usamos un layout nulo para posicionar los componentes manualmente

		addComponents();
		addListeners();

		// Revalidar y repintar el panel
		revalidate();
		repaint();
	}

	/**
	 * Agrega los componentes visuales al panel.
	 */
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

		JPanel panUsername = new JPanel();
		panUsername.setOpaque(false);
		panUsername.setBounds(10, 92, 180, 26);
		add(panUsername);

		lblUsername = new JLabel("USUARIO DESCONOCIDO");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 10));
		panUsername.add(lblUsername);

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

		lblWindowText = new JLabel("ESTAD\u00CDSTICAS");
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

		lblProfile = new JLabel("ESTAD\u00CDSTICAS");
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

		// Crear paneles de contenido utilizando las nuevas clases separadas
		panEstadisticas = new PanStatistics();
		add(panEstadisticas);
		panEstadisticas.setBounds(200, 50, 700, 450);
		panEstadisticas.setVisible(true);

		panObjetivos = new PanGoals();
		add(panObjetivos);
		panObjetivos.setBounds(200, 50, 700, 450);
		panObjetivos.setVisible(false);

		panPremios = new PanRewards();
		add(panPremios);
		panPremios.setBounds(200, 50, 700, 450);
		panPremios.setVisible(false);

		panAjustes = new PanSettings();
		add(panAjustes);
		panAjustes.setBounds(200, 50, 700, 450);
		panAjustes.setVisible(false);
	}

	/**
	 * Agrega los listeners para los componentes interactivos del panel.
	 */
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
				showPanel(panEstadisticas, "ESTAD\u00CDSTICAS");
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

	/**
	 * Muestra el panel especificado y actualiza el texto de la ventana
	 * correspondiente.
	 *
	 * @param panel     Panel a mostrar.
	 * @param panelName Nombre del panel para actualizar el texto de la ventana.
	 */
	private void showPanel(JPanel panel, String panelName) {
		panEstadisticas.setVisible(false);
		panObjetivos.setVisible(false);
		panPremios.setVisible(false);
		panAjustes.setVisible(false);
		panel.setVisible(true);
		lblWindowText.setText(panelName);

		// Actualizar userId en PanGoals si es necesario
		if (panel == panObjetivos) {
			panObjetivos.updateUserId(DataUser.userId);
		}

		// Actualizar userId en PanEstadisticas si es necesario
		if (panel == panEstadisticas) {
			try {
				panEstadisticas.updateUserId(DataUser.userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza el nombre de usuario mostrado en el panel.
	 *
	 * @param username Nombre de usuario a mostrar.
	 */
	public void updateUsername(String username) {
		lblUsername.setText(username.toUpperCase());
	}

	/**
	 * Actualiza las estad\u00EDsticas del panel.
	 */
	public void updatePanelStats() {
		panEstadisticas.updateUserId(DataUser.userId);
	}
}
