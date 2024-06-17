package view.user.settings;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.FrmPrincipal;
import view.config.DiaManual;

/**
 * Controlador para la configuraci\u00F3n de usuario que gestiona la interacci\u00F3n entre el panel de configuraci\u00F3n y otros componentes.
 */
public class CtrlSettings {

	private PanSettings panSettings; // Referencia al panel de ajustes

	/**
	 * Constructor del controlador de configuraci\u00F3n.
	 * 
	 * @param panSettings Panel de configuraci\u00F3n que se est\u00E1 controlando.
	 */
	public CtrlSettings(PanSettings panSettings) {
		this.panSettings = panSettings;
	}

	/**
	 * M\u00E9todo para manejar la acci\u00F3n de cambiar contrase\u00F1a.
	 * Abre un di\u00E1logo para cambiar la contrase\u00F1a.
	 */
	public void handleChangePassword() {
		DiaChangePassword dialog = new DiaChangePassword(panSettings);
		dialog.setVisible(true);
	}

	/**
	 * M\u00E9todo para mostrar el manual de ayuda.
	 * 
	 * @param panel Panel sobre el cual se mostrar\u00E1 el manual.
	 */
	public void showHelpManual(JPanel panel) {
		@SuppressWarnings("unused")
		DiaManual manual = new DiaManual(panel);
	}

	/**
	 * M\u00E9todo para cerrar sesión del usuario.
	 * Muestra un mensaje de confirmaci\u00F3n antes de cerrar la sesi\u00F3n.
	 * Si se confirma, elimina todos los componentes del ancestro superior y muestra el panel principal.
	 */
	public void logout() {
		if (confirmLogout()) {
			panSettings.getTopLevelAncestor().removeAll();
			FrmPrincipal mainframe = new FrmPrincipal();
			mainframe.showPanel("PanPrincipal");
		}
	}

	/**
	 * M\u00E9todo privado para confirmar el cierre de sesi\u00F3n.
	 * 
	 * @return true si el usuario confirma cerrar sesi\u00F3n, false si no.
	 */
	private boolean confirmLogout() {
		int option = JOptionPane.showConfirmDialog(panSettings, "¿Est\u00E1s seguro de que deseas cerrar sesi\u00F3n?",
				"Cerrar Sesi\u00F3n", JOptionPane.YES_NO_OPTION);
		return option == JOptionPane.YES_OPTION;
	}

}
