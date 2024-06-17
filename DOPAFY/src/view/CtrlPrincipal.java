package view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * La clase CtrlPrincipal contiene los m\u00E9todos de control para la ventana principal de la aplicaci\u00F3n.
 * Maneja la salida de la aplicaci\u00F3n y la animación de fondo basada en la posici\u00F3n del rat\u00F3n.
 */
public class CtrlPrincipal {

    /**
     * Cierra la aplicaci\u00F3n.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Muestra un cuadro de di\u00E1logo de confirmaci\u00F3n antes de salir de la aplicaci\u00F3n.
     *
     * @param context El componente desde el cual se muestra el cuadro de di\u00E1logo, debe ser un JFrame o JPanel.
     */
    public void exitQuestion(Component context) {
        if (!(context instanceof JFrame || context instanceof JPanel)) {
            throw new IllegalArgumentException("El componente debe ser un JFrame o JPanel.");
        }

        String pregunta = "\u00BFQuiere salir de la aplicaci\u00F3n?";
        int respuesta = JOptionPane.showConfirmDialog(context, pregunta, "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Anima el fondo de un panel basado en la posición del rat\u00F3n.
     *
     * @param e     El evento del rat\u00F3n.
     * @param panel El panel en el que se realizar\u00E1 la animaci\u00F3n.
     */
    public void animateBackground(MouseEvent e, JPanel panel) {
        // Obtener la posici\u00F3n del ratón
        int mouseX = e.getX();
        int mouseY = e.getY();

        int backgroundX = 0;
        int backgroundY = 0;

        if (isMouseInsidePanel(mouseX, mouseY, panel)) {
            // Mover el fondo solo si el rat\u00F3n está dentro del panel
            backgroundX = -(mouseX - panel.getWidth() / 2) / 60;
            backgroundY = -(mouseY - panel.getHeight() / 2) / 60;
            panel.repaint();
        }

        // Establecer la posici\u00F3n del fondo animado
        panel.setLocation(backgroundX, backgroundY);
    }

    /**
     * Verifica si el rat\u00F3n est\u00E1 dentro de los l\u00EDmites del panel.
     *
     * @param x     La coordenada X del rat\u00F3n.
     * @param y     La coordenada Y del rat\u00F3n.
     * @param panel El panel en el que se verifica la posici\u00F3n del rat\u00F3n.
     * @return true si el rat\u00F3n está dentro del panel, false en caso contrario.
     */
    private boolean isMouseInsidePanel(int x, int y, JPanel panel) {
        return x >= 0 && x <= panel.getWidth() && y >= 0 && y <= panel.getHeight();
    }
}
