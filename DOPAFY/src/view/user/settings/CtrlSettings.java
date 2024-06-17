package view.user.settings;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.FrmPrincipal;
import view.config.DiaManual;

public class CtrlSettings {

	private PanSettings panSettings; // Referencia al panel de ajustes

	public CtrlSettings(PanSettings panSettings) {
		this.panSettings = panSettings;

	}

	public void handleChangePassword() {

		DiaChangePassword dialog = new DiaChangePassword(panSettings);
		dialog.setVisible(true);

	}

	public void showHelpManual(JPanel panel) {
		@SuppressWarnings("unused")
		DiaManual manual = new DiaManual(panel);

	}

	public void logout() {
		if (confirmLogout()) {

			panSettings.getTopLevelAncestor().removeAll();

			FrmPrincipal mainframe = new FrmPrincipal();
			mainframe.showPanel("PanPrincipal");
		}
	}

	private boolean confirmLogout() {
		int option = JOptionPane.showConfirmDialog(panSettings, "¿Estás seguro de que deseas cerrar sesión?",
				"Cerrar Sesión", JOptionPane.YES_NO_OPTION);
		return option == JOptionPane.YES_OPTION;
	}

}
