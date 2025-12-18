package com.facturacion.dao;

import com.facturacion.entity.Cliente;
import com.facturacion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDAO {

    public void guardar(Cliente cliente) {

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.saveOrUpdate(cliente);

            tx.commit();

        } catch (Exception e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
