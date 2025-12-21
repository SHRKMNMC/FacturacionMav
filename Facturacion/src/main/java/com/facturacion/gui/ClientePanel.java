package com.facturacion.gui;

import com.facturacion.dao.ClienteDAO;
import com.facturacion.entity.Cliente;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ClientePanel extends JPanel {

    private JTextField txtNombre, txtDni, txtDireccion, txtPoblacion, txtProvincia,
            txtCodigoPostal, txtTelefonoFijo, txtTelefonoMovil, txtCorreoElectronico,
            txtPaginaWeb, txtMetodoPago, txtLimiteCredito, txtNumCuentaBancaria;
    private JCheckBox chkActivo;
    private JTextArea txtObservaciones;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private MainFrame mainFrame;

    public ClientePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));

        // Panel principal con scroll
        JPanel panelCampos = new JPanel(new GridBagLayout());
        JScrollPane scroll = new JScrollPane(panelCampos);
        add(scroll, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        txtNombre = addField(panelCampos, gbc, "Nombre *");
        txtDni = addField(panelCampos, gbc, "DNI *");
        txtDireccion = addField(panelCampos, gbc, "Dirección *");
        txtPoblacion = addField(panelCampos, gbc, "Población *");
        txtProvincia = addField(panelCampos, gbc, "Provincia *");
        txtCodigoPostal = addField(panelCampos, gbc, "Código Postal");
        txtTelefonoFijo = addField(panelCampos, gbc, "Teléfono Fijo");
        txtTelefonoMovil = addField(panelCampos, gbc, "Teléfono Móvil");
        txtCorreoElectronico = addField(panelCampos, gbc, "Correo Electrónico");
        txtPaginaWeb = addField(panelCampos, gbc, "Página Web");
        txtMetodoPago = addField(panelCampos, gbc, "Método de Pago (credito/contado)");
        txtLimiteCredito = addField(panelCampos, gbc, "Límite de Crédito");
        txtNumCuentaBancaria = addField(panelCampos, gbc, "Número de Cuenta Bancaria");

        // CheckBox
        gbc.gridx = 0;
        gbc.gridy++;
        panelCampos.add(new JLabel("Activo"), gbc);
        chkActivo = new JCheckBox();
        chkActivo.setSelected(true);
        gbc.gridx = 1;
        panelCampos.add(chkActivo, gbc);

        // Observaciones
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panelCampos.add(new JLabel("Observaciones"), gbc);
        gbc.gridy++;
        txtObservaciones = new JTextArea(4, 20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        panelCampos.add(new JScrollPane(txtObservaciones), gbc);
        gbc.gridwidth = 1;

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCliente());
        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.addActionListener(e -> mainFrame.mostrarMenuPrincipal());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JTextField addField(JPanel panel, GridBagConstraints gbc, String label) {
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        JTextField field = new JTextField(20);
        panel.add(field, gbc);
        gbc.gridy++;
        return field;
    }

    private void guardarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre(txtNombre.getText().trim());
        cliente.setDni(txtDni.getText().trim());
        cliente.setDireccion(txtDireccion.getText().trim());
        cliente.setPoblacion(txtPoblacion.getText().trim());
        cliente.setProvincia(txtProvincia.getText().trim());
        cliente.setCodigoPostal(txtCodigoPostal.getText().trim());
        cliente.setTelefonoFijo(txtTelefonoFijo.getText().trim());
        cliente.setTelefonoMovil(txtTelefonoMovil.getText().trim());
        cliente.setCorreoElectronico(txtCorreoElectronico.getText().trim());
        cliente.setPaginaWeb(txtPaginaWeb.getText().trim());
        cliente.setMetodoPago(txtMetodoPago.getText().trim().toLowerCase());
        cliente.setLimiteCredito(txtLimiteCredito.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(txtLimiteCredito.getText()));
        cliente.setNumCuentaBancaria(txtNumCuentaBancaria.getText().trim());
        cliente.setActivo(chkActivo.isSelected());
        cliente.setObservaciones(txtObservaciones.getText().trim());

        try {
            clienteDAO.guardar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente guardado correctamente");
            limpiarFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar cliente (sin conexión a BD)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDni.setText("");
        txtDireccion.setText("");
        txtPoblacion.setText("");
        txtProvincia.setText("");
        txtCodigoPostal.setText("");
        txtTelefonoFijo.setText("");
        txtTelefonoMovil.setText("");
        txtCorreoElectronico.setText("");
        txtPaginaWeb.setText("");
        txtMetodoPago.setText("");
        txtLimiteCredito.setText("");
        txtNumCuentaBancaria.setText("");
        chkActivo.setSelected(true);
        txtObservaciones.setText("");
    }

    public void setDatosCliente(Cliente c) {
        txtNombre.setText(c.getNombre());
        txtDni.setText(c.getDni());
        txtDireccion.setText(c.getDireccion());
        txtPoblacion.setText(c.getPoblacion());
        txtProvincia.setText(c.getProvincia());
        txtCodigoPostal.setText(c.getCodigoPostal());
        txtTelefonoFijo.setText(c.getTelefonoFijo());
        txtTelefonoMovil.setText(c.getTelefonoMovil());
        txtCorreoElectronico.setText(c.getCorreoElectronico());
        txtPaginaWeb.setText(c.getPaginaWeb());
        txtMetodoPago.setText(c.getMetodoPago());
        txtLimiteCredito.setText(c.getLimiteCredito() != null ? c.getLimiteCredito().toPlainString() : "");
        txtNumCuentaBancaria.setText(c.getNumCuentaBancaria());
        chkActivo.setSelected(c.isActivo());
        txtObservaciones.setText(c.getObservaciones());
    }

    public Cliente getDatosCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre(txtNombre.getText().trim());
        cliente.setDni(txtDni.getText().trim());
        cliente.setDireccion(txtDireccion.getText().trim());
        cliente.setPoblacion(txtPoblacion.getText().trim());
        cliente.setProvincia(txtProvincia.getText().trim());
        cliente.setCodigoPostal(txtCodigoPostal.getText().trim());
        cliente.setTelefonoFijo(txtTelefonoFijo.getText().trim());
        cliente.setTelefonoMovil(txtTelefonoMovil.getText().trim());
        cliente.setCorreoElectronico(txtCorreoElectronico.getText().trim());
        cliente.setPaginaWeb(txtPaginaWeb.getText().trim());
        cliente.setMetodoPago(txtMetodoPago.getText().trim().toLowerCase());
        cliente.setLimiteCredito(txtLimiteCredito.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(txtLimiteCredito.getText()));
        cliente.setNumCuentaBancaria(txtNumCuentaBancaria.getText().trim());
        cliente.setActivo(chkActivo.isSelected());
        cliente.setObservaciones(txtObservaciones.getText().trim());
        return cliente;
    }
}
