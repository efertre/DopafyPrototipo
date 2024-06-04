package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainBackground extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	private int backgroundX = 0;
	private int backgroundY = 0;

	public MainBackground(String imagePath) {
		backgroundImage = new ImageIcon(imagePath).getImage();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Dibujar la imagen de fondo
		g.drawImage(backgroundImage, backgroundX, backgroundY, getWidth(), getHeight(), this);
	}

}
