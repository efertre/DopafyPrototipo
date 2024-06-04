package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PanPrincipal extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblExit;
	private JButton btnLogin, btnRegister;

	private FrmPrincipal mainFrame;

	/**
	 * Crear un panel que contiene una imagen de fondo que se redimensiona junto con
	 * el JFrame.
	 * 
	 * @param frmPrincipal
	 */
	public PanPrincipal(FrmPrincipal frmPrincipal) {
		this.mainFrame = frmPrincipal;

		addComponents();
		addListeners();

		// Revalidar y repintar el panel
		revalidate();
		repaint();
	}

	private void addComponents() {

		// Cargar la imagen de fondo del btn
		ImageIcon iconBtnRegister = new ImageIcon("resources/images/buttons/BTN_REGISTER.png");
		btnRegister = new JButton("");
		btnRegister.setOpaque(true);
		btnRegister.setIcon(iconBtnRegister);
		btnRegister.setBounds(300, 225, 120, 40);
		add(btnRegister);

		ImageIcon iconBtnLogin = new ImageIcon("resources/images/buttons/BTN_LOGIN.png");
		btnLogin = new JButton("");
		btnLogin.setOpaque(true);
		btnLogin.setIcon(iconBtnLogin);
		btnLogin.setBounds(450, 225, 120, 40);
		add(btnLogin);

		// Etiqueta de salida
		lblExit = new JLabel("X");

		lblExit.setBounds(873, 0, 30, 30);
		add(lblExit);

		// Cargar la imagen de fondo
		ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");
		setLayout(null);

		// Crear el JLabel con imagen de fondo
		LabelWithBackground background = new LabelWithBackground(iconBackground);
		background.setBounds(0, 0, 900, 500);
		add(background);

	}

	private void addListeners() {
		CtrlPrincipal ctrl = new CtrlPrincipal();

		// Controlar salida del programa
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.exitQuestion(mainFrame);
			}
		});

		// Cambiar al panel de login
		btnLogin.addActionListener(e -> mainFrame.showPanel("PanLogin"));
		btnRegister.addActionListener(e -> mainFrame.showPanel("PanRegister"));

		// Agregar el listener para el movimiento del ratón
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				ctrl.animateBackground(e, PanPrincipal.this);
			}
		});
	}

}
