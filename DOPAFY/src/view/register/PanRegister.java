package view.register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Usuario;
import view.CtrlPrincipal;
import view.FrmPrincipal;

public class PanRegister extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField txtUserName, txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnRegister;
    private JLabel lblTextLogin;
    private JLabel background;
    private JLabel lblExit;
    private FrmPrincipal mainFrame;
    private CtrDiaRegister ctrlRegister;

    public PanRegister(FrmPrincipal FrmPrincipal) {
        this.mainFrame = FrmPrincipal;
        this.ctrlRegister = new CtrDiaRegister();

        setLayout(null); // Usamos un layout nulo para posicionar los componentes manualmente

        addComponents();
        addListeners();

        // Revalidar y repintar el panel
        revalidate();
        repaint();
    }

    private void addComponents() {
        // Cargar la imagen de fondo
        ImageIcon iconBackground = new ImageIcon("resources/images/BG_USER.png");

        // X Para salir
        lblExit = new JLabel("X");
        lblExit.setBounds(873, 0, 30, 30);
        add(lblExit);

        lblTextLogin = new JLabel("¿Ya tienes una cuenta?");
        lblTextLogin.setBounds(310, 320, 141, 13);
        add(lblTextLogin);

        // Crear el JLabel con imagen de fondo
        background = new JLabel(iconBackground);
        background.setBounds(0, 0, 900, 500);
        add(background);

        // Crear el panel para los campos de registro
        JPanel registerPanel = new JPanel(null); // Usamos un layout nulo para posicionar los componentes dentro de este
        // panel
        registerPanel.setOpaque(false); // Hacer el panel transparente para que se vea la imagen de fondo
        registerPanel.setBounds(300, 100, 300, 300);
        background.add(registerPanel); // Añadir el panel de registro al panel de fondo

        // Crear y posicionar los componentes dentro del panel de registro
        // Crear los iconos para las etiquetas
        ImageIcon iconUser = new ImageIcon("resources/images/icons/ICON_USER.png");
        ImageIcon iconMail = new ImageIcon("resources/images/icons/ICON_MAIL.png");
        ImageIcon iconPwd = new ImageIcon("resources/images/icons/ICON_PWD.png");
        ImageIcon iconConfPwd = new ImageIcon("resources/images/icons/ICON_CONF_PWD.png");

        JLabel lblUserName = new JLabel("");
        lblUserName.setToolTipText("Introduce un nombre de usuario");
        lblUserName.setIcon(iconUser);
        lblUserName.setBounds(60, 16, 16, 16);
        registerPanel.add(lblUserName);

        txtUserName = new JTextField();
        txtUserName.setToolTipText("Introduce un nombre de usuario");
        txtUserName.setBounds(100, 10, 180, 30);
        registerPanel.add(txtUserName);

        JLabel lblEmail = new JLabel("");
        lblEmail.setToolTipText("Introduce tu correo electrónico");
        lblEmail.setIcon(iconMail);
        lblEmail.setBounds(60, 58, 16, 16);
        registerPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setToolTipText("Introduce tu correo electrónico");
        txtEmail.setBounds(100, 50, 180, 30);
        registerPanel.add(txtEmail);

        JLabel lblPassword = new JLabel("");
        lblPassword.setToolTipText("Introduce una contraseña");
        lblPassword.setIcon(iconPwd);
        lblPassword.setBounds(60, 98, 16, 16);
        registerPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("Introduce una contraseña");
        txtPassword.setBounds(100, 90, 180, 30);
        registerPanel.add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("");
        lblConfirmPassword.setToolTipText("Confirma tu contraseña");
        lblConfirmPassword.setIcon(iconConfPwd);
        lblConfirmPassword.setBounds(60, 138, 16, 16);
        registerPanel.add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setToolTipText("Confirma tu contraseña");
        txtConfirmPassword.setBounds(100, 130, 180, 30);
        registerPanel.add(txtConfirmPassword);

        // Checkbox para mostrar/ocultar contraseña
        JCheckBox chkShowPassword = new JCheckBox("Mostrar contraseña");
        chkShowPassword.setOpaque(false);
        chkShowPassword.setBounds(140, 170, 140, 30);
        registerPanel.add(chkShowPassword);
        chkShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chkShowPassword.isSelected()) {
                    txtPassword.setEchoChar((char) 0); // Mostrar texto plano
                    txtConfirmPassword.setEchoChar((char) 0); // Mostrar texto plano
                } else {
                    txtPassword.setEchoChar('\u2022'); // Ocultar texto y mostrar puntos
                    txtConfirmPassword.setEchoChar('\u2022'); // Ocultar texto y mostrar puntos
                }
            }
        });

        btnRegister = new JButton("");
        ImageIcon iconRegister = new ImageIcon("resources/images/buttons/BTN_REGISTER.png");
        btnRegister.setIcon(iconRegister);
        btnRegister.setBounds(156, 210, 120, 40);
        registerPanel.add(btnRegister);
    }

    private void addListeners() {
        CtrlPrincipal ctrl = new CtrlPrincipal();
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUserName.getText();
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                String email = txtEmail.getText();

                if (username == null || username.length() > Usuario.MAX_USERNAME_LENGTH || !username.matches("^[A-Za-z0-9_]+$")) {
                	JOptionPane.showMessageDialog(PanRegister.this, "El nombre de usuario no puede ser nulo, debe tener un mínimo de "
        					+ Usuario.MIN_USERNAME_LENGTH + " y un máximo de " + Usuario.MAX_USERNAME_LENGTH
        					+ " caracteres, no puede contener espacios ni caracteres especiales",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return;
        		}
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(PanRegister.this, "Ingrese un correo electrónico válido.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password == null || password.length() > Usuario.MAX_PASSWORD_LENGTH
                        || password.length() < Usuario.MIN_PASSWORD_LENGTH) {
                    JOptionPane.showMessageDialog(PanRegister.this,
                            "Las contraseñas deben tener entre " + Usuario.MIN_PASSWORD_LENGTH + " y "
                                    + Usuario.MAX_PASSWORD_LENGTH + " caracteres.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(PanRegister.this, "Las contraseñas no coinciden.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    ctrlRegister.register(username, password, email);
                    JOptionPane.showMessageDialog(PanRegister.this, "Registro exitoso.", "Registro",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.showPanel("PanLogin");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PanRegister.this, ex.getMessage(), "Error de registro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar el listener para el movimiento del ratón
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ctrl.animateBackground(e, PanRegister.this);
            }
        });

        // Controlar salida del programa
        lblExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ctrl.exitQuestion(PanRegister.this);
            }
        });

        // Controlar la etiqueta "¿Ya tienes una cuenta?"
        lblTextLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblTextLogin.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblTextLogin.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lblTextLogin.setForeground(Color.GRAY);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showPanel("PanLogin");
            }
        });
    }

    private boolean isValidEmail(String email) {
        // Patrón simple para verificar si el email tiene un formato básico correcto
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
