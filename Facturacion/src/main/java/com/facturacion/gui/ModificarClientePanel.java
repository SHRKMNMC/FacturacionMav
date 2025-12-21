package com.facturacion.gui;

import com.facturacion.dao.ClienteDAO;
import com.facturacion.entity.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModificarClientePanel extends JPanel {

    private JComboBox<Cliente> cmbClientes;
    private ClientePanel formCliente;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private MainFrame mainFrame;

    public ModificarClientePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecciona cliente:"));
        cmbClientes = new JComboBox<>();
        topPanel.add(cmbClientes);

        JButton btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(e -> cargarCliente());
        topPanel.add(btnCargar);

        add(topPanel, BorderLayout.NORTH);

        formCliente = new ClientePanel(mainFrame);
        add(formCliente, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar, BorderLayout.SOUTH);

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

    private void cargarCliente() {
        Cliente seleccionado = (Cliente) cmbClientes.getSelectedItem();
        if (seleccionado != null) {
            formCliente.setDatosCliente(seleccionado);
        }
    }

    private void guardarCambios() {
        Cliente cliente = formCliente.getDatosCliente();
        try {
            clienteDAO.actualizar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente actualizado");
            cargarClientes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente (sin conexión a BD)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
