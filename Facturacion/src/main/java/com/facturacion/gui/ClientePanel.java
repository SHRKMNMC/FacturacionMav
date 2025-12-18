package com.facturacion.gui;

import com.facturacion.dao.ClienteDAO;
import com.facturacion.entity.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClientePanel extends JPanel {

    private JTextField txtNombre;
    private JTextField txtDni;
    private JTextField txtDireccion;
    private JTextField txtPoblacion;
    private JTextField txtProvincia;
    private JTextField txtCodigoPostal;
    private JTextField txtTelefono;
    private JCheckBox chkActivo;

    private ClienteDAO clienteDAO = new ClienteDAO();

    public ClientePanel() {

        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel("Nombre *"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("DNI *"));
        txtDni = new JTextField();
        add(txtDni);

        add(new JLabel("Dirección *"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Población *"));
        txtPoblacion = new JTextField();
        add(txtPoblacion);

        add(new JLabel("Provincia *"));
        txtProvincia = new JTextField();
        add(txtProvincia);

        add(new JLabel("Código Postal"));
        txtCodigoPostal = new JTextField();
        add(txtCodigoPostal);

        add(new JLabel("Teléfono"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        add(new JLabel("Activo"));
        chkActivo = new JCheckBox();
        chkActivo.setSelected(true);
        add(chkActivo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCliente());

        add(new JLabel());
        add(btnGuardar);
    }

    private void guardarCliente() {

        if (!validarFormulario()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Rellena todos los campos obligatorios (*)",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(txtNombre.getText().trim());
        cliente.setDni(txtDni.getText().trim());
        cliente.setDireccion(txtDireccion.getText().trim());
        cliente.setPoblacion(txtPoblacion.getText().trim());
        cliente.setProvincia(txtProvincia.getText().trim());
        cliente.setCodigoPostal(txtCodigoPostal.getText().trim());
        cliente.setTelefonoFijo(txtTelefono.getText().trim());
        cliente.setActivo(chkActivo.isSelected());

        clienteDAO.guardar(cliente);

        JOptionPane.showMessageDialog(this, "Cliente guardado correctamente");
        limpiarFormulario();
    }

    private boolean validarFormulario() {
        return !txtNombre.getText().trim().isEmpty()
                && !txtDni.getText().trim().isEmpty()
                && !txtDireccion.getText().trim().isEmpty()
                && !txtPoblacion.getText().trim().isEmpty()
                && !txtProvincia.getText().trim().isEmpty();
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDni.setText("");
        txtDireccion.setText("");
        txtPoblacion.setText("");
        txtProvincia.setText("");
        txtCodigoPostal.setText("");
        txtTelefono.setText("");
        chkActivo.setSelected(true);
    }
}
