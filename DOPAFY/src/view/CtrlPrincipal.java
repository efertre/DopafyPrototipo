package view;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CtrlPrincipal {

	public void exit() {
		System.exit(0);
	}

	public void exitQuestion(Component context) {
		if (!(context instanceof JFrame || context instanceof JPanel)) {
			throw new IllegalArgumentException("El componente debe ser un JFrame o JPanel.");
		}

		String pregunta = "¿Quiere salir de la aplicaci\u00F3n?";
		int respuesta = JOptionPane.showConfirmDialog(context, pregunta, "Salir", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void animateBackground(MouseEvent e, JPanel panel) {
		// Obtener la posición del ratón
		int mouseX = e.getX();
		int mouseY = e.getY();

		int backgroundX = 0;
		int backgroundY = 0;

		if (isMouseInsidePanel(e.getX(), e.getY(), panel)) {
			// Mover el fondo solo si el ratón está dentro del panel
			backgroundX = -(mouseX - panel.getWidth() / 2) / 60;
			backgroundY = -(mouseY - panel.getHeight() / 2) / 60;
			panel.repaint();
		}

		// Establecer la posición del fondo animado
		panel.setLocation(backgroundX, backgroundY);
	}

	private boolean isMouseInsidePanel(int x, int y, JPanel panel) {
		return x >= 0 && x <= panel.getWidth() && y >= 0 && y <= panel.getHeight();
	}
}
