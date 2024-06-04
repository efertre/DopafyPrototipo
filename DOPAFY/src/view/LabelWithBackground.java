package view;

import javax.swing.*;
import java.awt.*;

public class LabelWithBackground extends JLabel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	public LabelWithBackground(ImageIcon icon) {
		this.backgroundImage = icon.getImage();
		setOpaque(false); // Para que el fondo sea visible
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		super.paintComponent(g);
	}
}