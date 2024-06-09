package view.user;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class CtrlPanUser {

	// Método para el hover de las opciones de menú (se pone el fondo oscuro)
	public void addMouseListenerToPanel(JPanel panel) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(147, 207, 167)); // Tonalidad oscura
				panel.setOpaque(true);
				panel.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(null);
				panel.setOpaque(false);
				panel.repaint();
			}
		});
	}
}
