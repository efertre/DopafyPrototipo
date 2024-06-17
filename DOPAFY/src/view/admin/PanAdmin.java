package view.admin;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.CtrlUsuario;
import dbm.DataBase;
import view.CtrlPrincipal;
import view.FrmPrincipal;

public class PanAdmin extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private static DefaultTableModel tableModel;

	private JLabel lblExit;

	private JFrame mainFrame;

	private static CtrlUsuario ctrlUser;

	private JButton btnAdd, btnEdit, btnDelete, btnRefresh, btnSearch;

	private JTextField txtSearchId, txtSearchUsername;

	public PanAdmin(FrmPrincipal frmPrincipal) {
		ctrlUser = new CtrlUsuario();

		this.mainFrame = frmPrincipal;
		setLayout(new BorderLayout(0, 0));

		// Etiqueta de salida
		lblExit = new JLabel("X");
		lblExit.setHorizontalAlignment(SwingConstants.RIGHT);

		lblExit.setBounds(873, 0, 30, 30);
		add(lblExit, BorderLayout.NORTH);

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("resources/images/fondoMPGestion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImagePanel panel = new ImagePanel(image);
		add(panel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		btnAdd = new JButton("Añadir Usuario");
		btnEdit = new JButton("Editar Usuario");
		btnDelete = new JButton("Eliminar Usuario");
		btnRefresh = new JButton("Actualizar"); 
		btnSearch = new JButton("Buscar"); 

		txtSearchId = new JTextField(10);
		txtSearchUsername = new JTextField(10);

		buttonPanel.add(new JLabel("ID:"));
		buttonPanel.add(txtSearchId);
		buttonPanel.add(new JLabel("Username:"));
		buttonPanel.add(txtSearchUsername);
		buttonPanel.add(btnSearch); 
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnRefresh); 
		add(buttonPanel, BorderLayout.SOUTH);

		String[] columnNames = { "ID", "Nombre", "Email", "Username", "Contraseña", "Es Admin" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		ctrlUser.loadUsuarios(tableModel);

		addListeners();
	}

	private void addListeners() {
		CtrlPrincipal ctrl = new CtrlPrincipal();
		// Controlar salida del programa
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.exitQuestion(mainFrame);
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showUserDialog(null);
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String id = tableModel.getValueAt(selectedRow, 0).toString();
					showUserDialog(id);
				} else {
					JOptionPane.showMessageDialog(PanAdmin.this, "Por favor, selecciona un usuario para editar.");
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String id = tableModel.getValueAt(selectedRow, 0).toString();
					ctrlUser.deleteUser(id);
					updateTable();
				} else {
					JOptionPane.showMessageDialog(PanAdmin.this, "Por favor, selecciona un usuario para eliminar.");
				}
			}

		});

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanAdmin.updateTable();
			}
		});
		
		 btnSearch.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String id = txtSearchId.getText().trim();
	                String username = txtSearchUsername.getText().trim();
	                ctrlUser.searchUsers(tableModel, id, username);
	            }
	        });
	}

	private static void updateTable() {
		ctrlUser.loadUsuarios(tableModel); // Recargar los datos en el modelo de la tabla
		tableModel.fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
	}

	public void showUserDialog(String userId) {
		JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Usuario", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(6, 2));
		JTextField txtNombre = new JTextField();
		JTextField txtEmail = new JTextField();
		JTextField txtUsername = new JTextField();
		JTextField txtPassword = new JTextField();
		JCheckBox chkEsAdmin = new JCheckBox();

		if (userId != null) {

			String sql = "SELECT * FROM USUARIO WHERE userId = ?";
			try {
				DataBase.open();
				ResultSet rs = DataBase.executePreparedQuery(sql, userId);
				if (rs.next()) {
					txtNombre.setText(rs.getString("nombre"));
					txtEmail.setText(rs.getString("email"));
					txtUsername.setText(rs.getString("username"));
					txtPassword.setText(rs.getString("password"));
					chkEsAdmin.setSelected(rs.getBoolean("esAdmin"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DataBase.close();
			}
		}

		panel.add(new JLabel("Nombre:"));
		panel.add(txtNombre);
		panel.add(new JLabel("Email:"));
		panel.add(txtEmail);
		panel.add(new JLabel("Nombre de Usuario:"));
		panel.add(txtUsername);
		panel.add(new JLabel("Contraseña:"));
		panel.add(txtPassword);
		panel.add(new JLabel("Es Admin:"));
		panel.add(chkEsAdmin);

		JButton btnSave = new JButton("Guardar");
		JButton btnCancel = new JButton("Cancelar");

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				boolean esAdmin = chkEsAdmin.isSelected();

				if (userId == null) {
					ctrlUser.addUser(nombre, email, username, password, esAdmin);
				} else {
					ctrlUser.updateUser(userId, nombre, email, username, password, esAdmin);
				}

				ctrlUser.loadUsuarios(tableModel);

				dialog.dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnSave);
		buttonPanel.add(btnCancel);

		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	private class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage image;

		public ImagePanel(BufferedImage image) {
			this.image = image;
			setLayout(new BorderLayout());
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				int width = getWidth();
				int height = getHeight();
				Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				g.drawImage(scaledImage, 0, 0, null);
			}
		}
	}
}
