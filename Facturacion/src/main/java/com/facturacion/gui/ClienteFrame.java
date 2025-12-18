package com.facturacion.gui;

import javax.swing.*;

public class ClienteFrame extends JFrame {

    public ClienteFrame() {
        setTitle("Gestión de Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new ClientePanel());
    }
}
