package com.facturacion.main;

import com.facturacion.gui.ClienteFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClienteFrame().setVisible(true);
        });
    }
}
