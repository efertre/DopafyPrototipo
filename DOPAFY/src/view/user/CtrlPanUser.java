package view.user;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * Controlador para manejar el efecto hover sobre los paneles de men\u00FA.
 */
public class CtrlPanUser {

	/**
	 * Agrega un listener para controlar el efecto hover sobre un panel. Cuando el
	 * mouse entra en el panel, se cambia el fondo a una tonalidad oscura. Cuando el
	 * mouse sale del panel, se restaura el fondo a su estado original.
	 * 
	 * @param panel Panel al que se le agregar\u00E1 el efecto hover.
	 */
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
