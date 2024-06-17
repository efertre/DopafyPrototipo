package view.user.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

/**
 * Panel de configuraci\u00F3n de usuario que permite cambiar contrase\u00F1a, acceder al manual de ayuda y cerrar sesi\u00F3n.
 */
public class PanSettings extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel background;

	private JButton btnChangePassword;
	private JButton btnHelpManual;
	private JButton btnLogout;

	/**
	 * Constructor del panel de configuraci\u00F3n.
	 */
	public PanSettings() {
		setBackground(new Color(152, 251, 152));
		setBounds(200, 50, 700, 450);
		setVisible(false);

		addComponents();

		// Revalidar y repintar el panel
		revalidate();
		repaint();
	}

	/**
	 * M\u00E9todo privado para agregar los componentes al panel.
	 */
	private void addComponents() {
		// Cargar la imagen de fondo
		ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 180, 680, 50);
		panel.setBackground(new Color(152, 251, 152));
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnChangePassword = createButton("Cambiar Contrase\u00F1a");
		panel.add(btnChangePassword);

		btnHelpManual = createButton("Manual de Ayuda");
		panel.add(btnHelpManual);

		btnLogout = createButton("Cerrar Sesi\u00F3n");
		panel.add(btnLogout);

		// Crear el JLabel con imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 700, 450);
		add(background);

		// Crear el controlador de configuraci\u00F3n y asociar acciones a los botones
		CtrlSettings ctrl = new CtrlSettings(PanSettings.this);

		btnHelpManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.showHelpManual(PanSettings.this);
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.logout();
			}
		});

		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.handleChangePassword();
			}
		});

	}

	/**
	 * M\u00E9todo privado para crear y configurar un bot\u00F3n con efecto de cambio de tama\u00F1o al pasar el rat\u00F3n.
	 * 
	 * @param text Texto del bot\u00F3n
	 * @return JButton configurado
	 */
	private JButton createButton(String text) {
		JButton button = new JButton(text);

		button.setPreferredSize(new Dimension(150, 30)); // Tama\u00F1o inicial
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setPreferredSize(new Dimension(170, 35)); // Tama\u00F1o aumentado
				button.revalidate(); // Revalidar el botón para aplicar el nuevo tama\u00F1o
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setPreferredSize(new Dimension(150, 30)); // Tama\u00F1o original
				button.revalidate(); // Revalidar el bot\u00F3n para aplicar el tama\u00F1o original
			}
		});
		return button;
	}

}
