package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * La clase MainBackground extiende JPanel y se utiliza para dibujar una imagen de fondo.
 * La imagen de fondo se ajusta al tama\u00F1o del panel.
 */
public class MainBackground extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	private int backgroundX = 0;
	private int backgroundY = 0;

	/**
	 * Constructor que inicializa el panel con una imagen de fondo.
	 *
	 * @param imagePath La ruta de la imagen de fondo.
	 */
	public MainBackground(String imagePath) {
		backgroundImage = new ImageIcon(imagePath).getImage();
	}

	/**
	 * Sobrescribe el m\u00E9todo paintComponent para dibujar la imagen de fondo.
	 *
	 * @param g El contexto gr\u00E1fico en el que se debe dibujar.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Dibujar la imagen de fondo
		g.drawImage(backgroundImage, backgroundX, backgroundY, getWidth(), getHeight(), this);
	}
}
