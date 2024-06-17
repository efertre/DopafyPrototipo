package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import view.admin.PanAdmin;
import view.login.PanLogin;
import view.register.PanRegister;
import view.user.DataUser;
import view.user.PanUser;
import view.user.goals.PanGoals;

/**
 * La clase FrmPrincipal representa la ventana principal de la aplicaci\u00F3n.
 * Gestiona el cambio entre diferentes paneles usando un CardLayout.
 */
public class FrmPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private MainBackground mainPanel;
    private CardLayout cardLayout;

    private PanUser panUser;
    private PanGoals panGoals = new PanGoals();

    /**
     * Constructor de FrmPrincipal. Configura la ventana principal, incluyendo
     * el tama\u00F1o, el \u00EDcono, y los paneles.
     */
    public FrmPrincipal() {
        // Desactivar los botones de ventana por defecto
        setUndecorated(true);

        setTitle("DOPAFY");
        setSize(900, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/icon.png"));

        // Botones de cuadros de confirmaci\u00F3n o negaci\u00F3n en Espa\u00F1ol
        UIManager.put("OptionPane.yesButtonText", "S\u00ED");
        UIManager.put("OptionPane.okButtonText", "Aceptar");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        // Configurar el CardLayout
        cardLayout = new CardLayout();
        mainPanel = new MainBackground("resources/images/BG_USER.png"); // Ruta de la imagen de fondo

        // Crear los paneles
        PanPrincipal panPrincipal = new PanPrincipal(this);
        PanLogin panLogin = new PanLogin(this);
        PanRegister panRegister = new PanRegister(this);
        panUser = new PanUser(this);
        PanAdmin panAdmin = new PanAdmin(this);

        // A\u00F1adir los paneles al mainPanel
        mainPanel.setLayout(cardLayout);
        mainPanel.add(panPrincipal, "PanPrincipal");
        mainPanel.add(panLogin, "PanLogin");
        mainPanel.add(panRegister, "PanRegister");
        mainPanel.add(panUser, "PanUser");
        mainPanel.add(panAdmin, "PanAdmin");

        // A\u00F1adir mainPanel al JFrame
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Controlar el cierre de la ventana
        CtrlPrincipal ctrl = new CtrlPrincipal();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ctrl.exitQuestion(FrmPrincipal.this);
            }
        });

        setVisible(true); // Mostrar la ventana
    }

    /**
     * Cambia al panel especificado y actualiza su contenido si es necesario.
     *
     * @param panelName el nombre del panel al que se desea cambiar
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
        if (panelName.equalsIgnoreCase("PanUser")) {
            panUser.updateUsername(DataUser.loggedInUsername); // Actualizar el nombre de usuario en PanUser
            panUser.updatePanelStats();
            panGoals.updateUserId(DataUser.userId);
        }
    }
}
