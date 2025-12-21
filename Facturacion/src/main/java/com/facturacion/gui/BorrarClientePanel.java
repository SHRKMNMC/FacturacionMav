package com.facturacion.gui;

import com.facturacion.dao.ClienteDAO;
import com.facturacion.entity.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BorrarClientePanel extends JPanel {

    private JComboBox<Cliente> cmbClientes;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private MainFrame mainFrame;

    public BorrarClientePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());

        cmbClientes = new JComboBox<>();
        add(cmbClientes);

        JButton btnBorrar = new JButton("Borrar cliente");
        btnBorrar.addActionListener(e -> borrarCliente());
        add(btnBorrar);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.addActionListener(e -> mainFrame.mostrarMenuPrincipal());
        add(btnVolver);

        cargarClientes();
    }

    private void cargarClientes() {
        cmbClientes.removeAllItems();
        try {
            List<Cliente> clientes = clienteDAO.listar();
            for (Cliente c : clientes) {
                cmbClientes.addItem(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar clientes (sin conexión a BD)", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void borrarCliente() {
        Cliente seleccionado = (Cliente) cmbClientes.getSelectedItem();
        if (seleccionado != null) {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea borrar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                try {
                    clienteDAO.borrarPorId(seleccionado.getId());
                    JOptionPane.showMessageDialog(this, "Cliente borrado");
                    cargarClientes();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al borrar cliente (sin conexión a BD)", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
