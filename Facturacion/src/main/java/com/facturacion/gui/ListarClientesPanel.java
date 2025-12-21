package com.facturacion.gui;

import com.facturacion.dao.ClienteDAO;
import com.facturacion.entity.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarClientesPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private MainFrame mainFrame;

    public ListarClientesPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{
                "ID", "Nombre", "DNI", "Dirección", "Población", "Provincia",
                "Código Postal", "Teléfono Fijo", "Teléfono Móvil", "Activo"
        }, 0);

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.addActionListener(e -> mainFrame.mostrarMenuPrincipal());
        add(btnVolver, BorderLayout.SOUTH);

        cargarClientes();
    }

    private void cargarClientes() {
        modelo.setRowCount(0);
        try {
            List<Cliente> clientes = clienteDAO.listar();
            for (Cliente c : clientes) {
                modelo.addRow(new Object[]{
                        c.getId(),
                        c.getNombre(),
                        c.getDni(),
                        c.getDireccion(),
                        c.getPoblacion(),
                        c.getProvincia(),
                        c.getCodigoPostal(),
                        c.getTelefonoFijo(),
                        c.getTelefonoMovil(),
                        c.isActivo() ? "Sí" : "No"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar clientes (sin conexión a BD)", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
