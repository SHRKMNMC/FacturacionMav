package com.facturacion.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Gestión de Clientes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        mostrarMenuPrincipal();
    }

    public void mostrarMenuPrincipal() {
        JPanel menuPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton btnCrear = new JButton("Crear Cliente");
        btnCrear.addActionListener(e -> mostrarPanel(new ClientePanel(this)));

        JButton btnModificar = new JButton("Modificar Cliente");
        btnModificar.addActionListener(e -> {
            try {
                mostrarPanel(new ModificarClientePanel(this));
            } catch (Exception ex) {
                System.out.println("No se puede acceder a Modificar Cliente (sin conexión a BD)");
            }
        });

        JButton btnBorrar = new JButton("Borrar Cliente");
        btnBorrar.addActionListener(e -> {
            try {
                mostrarPanel(new BorrarClientePanel(this));
            } catch (Exception ex) {
                System.out.println("No se puede acceder a Borrar Cliente (sin conexión a BD)");
            }
        });

        JButton btnListar = new JButton("Listar Clientes");
        btnListar.addActionListener(e -> {
            try {
                mostrarPanel(new ListarClientesPanel(this));
            } catch (Exception ex) {
                System.out.println("No se puede acceder a Listar Clientes (sin conexión a BD)");
            }
        });

        menuPanel.add(btnCrear);
        menuPanel.add(btnModificar);
        menuPanel.add(btnBorrar);
        menuPanel.add(btnListar);

        contentPanel.removeAll();
        contentPanel.add(menuPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
