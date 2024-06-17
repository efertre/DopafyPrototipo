package view.user.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanSettings extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel background;

    public PanSettings() {
        setBackground(new Color(152, 251, 152));
        setBounds(200, 50, 700, 450);
        setVisible(false);

        addComponents();

        // Revalidar y repintar el panel
        revalidate();
        repaint();
    }

    private void addComponents() {
        // Cargar la imagen de fondo
        ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBounds(10, 180, 680, 50);
        panel.setBackground(new Color(152, 251, 152));
        add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnChangePassword = createButton("Cambiar Contraseña");
        panel.add(btnChangePassword);

        JButton btnHelpManual = createButton("Manual de Ayuda");
        panel.add(btnHelpManual);

        JButton btnLogout = createButton("Cerrar Sesión");
        panel.add(btnLogout);

        // Crear el JLabel con imagen de fondo
        background = new JLabel(iconBackground);
        background.setBounds(0, 0, 700, 450);
        add(background);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 30)); // Tamaño inicial
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setPreferredSize(new Dimension(170, 35)); // Tamaño aumentado
                button.revalidate(); // Revalidar el botón para aplicar el nuevo tamaño
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setPreferredSize(new Dimension(150, 30)); // Tamaño original
                button.revalidate(); // Revalidar el botón para aplicar el tamaño original
            }
        });
        return button;
    }
}
