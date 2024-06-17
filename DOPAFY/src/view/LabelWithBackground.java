package view;

import javax.swing.*;
import java.awt.*;

/**
 * La clase LabelWithBackground extiende JLabel para permitir que una etiqueta tenga una imagen de fondo.
 * La imagen de fondo se ajusta al tama\u00F1o de la etiqueta.
 */
public class LabelWithBackground extends JLabel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	/**
	 * Constructor que inicializa la etiqueta con una imagen de fondo.
	 *
	 * @param icon El ImageIcon que contiene la imagen de fondo.
	 */
	public LabelWithBackground(ImageIcon icon) {
		this.backgroundImage = icon.getImage();
		setOpaque(false); // Para que el fondo sea visible
	}

	/**
	 * Sobrescribe el m\u00E9todo paintComponent para dibujar la imagen de fondo.
	 *
	 * @param g El contexto gr\u00E1fico en el que se debe dibujar.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		super.paintComponent(g);
	}
}
