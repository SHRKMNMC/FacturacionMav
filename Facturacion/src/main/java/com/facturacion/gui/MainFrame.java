package com.facturacion.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Facturación");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnNuevo = new JButton("Nuevo cliente");
        btnNuevo.addActionListener(e ->
                new ClienteForm(this, null).setVisible(true)
        );

        JPanel top = new JPanel();
        top.add(btnNuevo);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(new JTable()), BorderLayout.CENTER);
    }
}
