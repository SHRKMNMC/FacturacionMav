package com.facturacion.dao;

import com.facturacion.entity.Cliente;
import com.facturacion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteDAO {

    // Guardar o actualizar (si el id existe, actualiza)
    public void guardar(Cliente cliente) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // Actualizar cliente existente
    public void actualizar(Cliente cliente) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // Borrar por id
    public void borrarPorId(Integer id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Cliente c = session.get(Cliente.class, id);
            if (c != null) session.delete(c);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // Listar todos los clientes
    public List<Cliente> listar() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Cliente", Cliente.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // devuelve lista vacía si hay error
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // Obtener cliente por id
    public Cliente getPorId(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }
}
