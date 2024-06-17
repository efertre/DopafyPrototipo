package view.user.rewards;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class PanRewards extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel background;

	public PanRewards() {
		setBackground(new Color(152, 251, 152));
		setBounds(200, 50, 700, 450);
		setVisible(false);

		addComponents();

		// Revalidar y repintar el panel
		revalidate();
		repaint();
	}

	private void addComponents() {
		// Cargar la imagen de fondo
		ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");
		setLayout(null);
		
		JLabel lblComingSoon = new JLabel("PRÓXIMAMENTE (NECESITA ESTAR CONECTADO)");
		lblComingSoon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblComingSoon.setBounds(200, 205, 320, 13);
		add(lblComingSoon);

		// Crear el JLabel con imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 700, 450);
		add(background);
	}
}
