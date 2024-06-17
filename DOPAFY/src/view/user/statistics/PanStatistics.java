package view.user.statistics;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanStatistics extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel background;

	public PanStatistics() {
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

		// Crear el JLabel con imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 700, 450);
		add(background);
	}

}
