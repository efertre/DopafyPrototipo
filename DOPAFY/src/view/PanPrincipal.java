package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanPrincipal extends JPanel {
  private static final long serialVersionUID = 1L;

  /**
   * Crear un panel que contiene una imagen de fondo
   * que se redimensiona junto con el JFrame.
   */
  public PanPrincipal() {
    setLayout(new BorderLayout(0, 0));

    BufferedImage image = null;
    try {
      image = ImageIO.read(new File("resources/images/fondoMPGestion.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    ImagePanel panel = new ImagePanel(image);
    add(panel);
  }

  private class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
      this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (image != null) {
        int width = getWidth();
        int height = getHeight();
        // Escalar la imagen al tamaño del panel
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, null);
      }
    }
  }

}