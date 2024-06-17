package view.user.rewards;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel de recompensas para adquirir a trav\u00E9s de los puntos de los objetivos completados, a\u00FAn en proceso
 * (requiere conexi\u00F3n).
 */
public class PanRewards extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel background;

	/**
	 * Constructor para inicializar el panel de recompensas.
	 * Establece el fondo y las dimensiones del panel, pero inicialmente lo hace invisible.
	 * Carga los componentes visuales como la imagen de fondo y un mensaje de pr\u00F3xima disponibilidad.
	 */
	public PanRewards() {
		setBackground(new Color(152, 251, 152)); // Color de fondo verde claro
		setBounds(200, 50, 700, 450); // Establecer posición y tamaño del panel
		setVisible(false); // Inicialmente el panel no es visible

		addComponents(); // Agregar los componentes visuales al panel

		// Revalidar y repintar el panel para asegurar que los cambios sean visibles
		revalidate();
		repaint();
	}

	/**
	 * M\u00E9todo privado para agregar los componentes visuales al panel.
	 * Carga una imagen de fondo desde un archivo local.
	 * Muestra un mensaje de texto indicando que la caracter\u00EDstica est\u00E1 pr\u00F3xima y requiere conexi\u00F3n.
	 */
	private void addComponents() {
		// Cargar la imagen de fondo desde el archivo local
		ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");
		setLayout(null); // Usar un layout nulo para posicionar los componentes manualmente
		
		// Etiqueta que indica que la caracter\u00EDstica est\u00E1 pr\u00F3xima y requiere conexi\u00F3n
		JLabel lblComingSoon = new JLabel("PR\u00D3XIMAMENTE (NECESITA ESTAR CONECTADO)");
		lblComingSoon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10)); // Fuente en negrita e it\u00E1lica, tama\u00F1o 10
		lblComingSoon.setBounds(200, 205, 320, 13); // Posici\u00F3n y tama\u00F1o de la etiqueta
		add(lblComingSoon); // Agregar la etiqueta al panel
		
		// Crear el JLabel con la imagen de fondo
		background = new JLabel(iconBackground);
		background.setBounds(0, 0, 700, 450); // Posici\u00F3n y tama\u00F1o del JLabel de fondo
		add(background); // Agregar el JLabel de fondo al panel
	}
}
