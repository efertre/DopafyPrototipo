package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame context;

	public FrmPrincipal() {
		context = this;

		setTitle("DOPAFY");
		setSize(900, 500);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("assets/icon.png"));

		// Botones de cuadros de confirmación en Español
		UIManager.put("OptionPane.yesButtonText", "Sí");

		// Añadir el Panel Inicio
		getContentPane().add(new PanPrincipal(), BorderLayout.CENTER);

		// Controlar el cierre de la ventana
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String pregunta = "¿Quiere salir de la aplicación?";
				int respuesta = JOptionPane.showConfirmDialog(context, pregunta, "Salir.", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		setVisible(true); // Mostrar la ventana

		new DiaPrincipal(context);
	}

}
