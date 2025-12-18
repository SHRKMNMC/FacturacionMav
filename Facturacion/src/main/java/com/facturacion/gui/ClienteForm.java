package com.facturacion.gui;

import com.facturacion.entity.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClienteForm extends JDialog {

    private JTextField txtNombre, txtDni, txtDireccion, txtPoblacion, txtProvincia, txtCP;
    private JCheckBox chkActivo;

    public ClienteForm(Frame owner, Cliente cliente) {
        super(owner, "Cliente", true);
        setSize(600, 400);
        setLocationRelativeTo(owner);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("General", crearPanelGeneral());
        tabs.add("Contacto", crearPanelContacto());
        tabs.add("Finanzas", crearPanelFinanzas());
        tabs.add("Otros", crearPanelOtros());

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel botones = new JPanel();
        botones.add(btnGuardar);
        botones.add(btnCancelar);

        add(tabs, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private JPanel crearPanelGeneral() {
        JPanel p = new JPanel(new GridLayout(7, 2, 5, 5));

        txtNombre = new JTextField();
        txtDni = new JTextField();
        txtDireccion = new JTextField();
        txtPoblacion = new JTextField();
        txtProvincia = new JTextField();
        txtCP = new JTextField();
        chkActivo = new JCheckBox("Activo");

        p.add(new JLabel("Nombre"));
        p.add(txtNombre);
        p.add(new JLabel("DNI"));
        p.add(txtDni);
        p.add(new JLabel("Dirección"));
        p.add(txtDireccion);
        p.add(new JLabel("Población"));
        p.add(txtPoblacion);
        p.add(new JLabel("Provincia"));
        p.add(txtProvincia);
        p.add(new JLabel("Código Postal"));
        p.add(txtCP);
        p.add(new JLabel(""));
        p.add(chkActivo);

        return p;
    }

    private JPanel crearPanelContacto() {
        return new JPanel(); // se rellena igual
    }

    private JPanel crearPanelFinanzas() {
        return new JPanel();
    }

    private JPanel crearPanelOtros() {
        return new JPanel();
    }
}
