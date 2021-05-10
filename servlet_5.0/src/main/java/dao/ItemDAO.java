package dao;

import javassist.NotFoundException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class ItemDAO<T> {



    final Class<T> typeParameterClass;

    public ItemDAO(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    private static SessionFactory sessionFactory;

    public T save(T obT) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(obT);
            tr.commit();
            System.out.println("Done save " + obT.toString());

            return obT;
        } catch (HibernateException e) {
            System.err.println("Something went wrong during save");
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        }
    }

    public T update(T obT) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.update(obT);
            tr.commit();
            System.out.println("Done update " + obT.toString());
            return obT;

        } catch (HibernateException e) {
            System.err.println("Something went wrong during update");
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        }

    }

    public void delete(long id) {
        Transaction tr = null;
        T obT;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            obT = (T) session.get(typeParameterClass, id);
            if (obT != null) {
                session.delete(obT);
                System.out.println("Done delete");
            }
            tr.commit();

        } catch (HibernateException e) {
            System.err.println("Something went wrong during delete");
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        }
    }

    public T findById(long id) throws NullPointerException {
        Transaction tr = null;
        T obT;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            obT = session.get(typeParameterClass, id);
            if (obT == null) {
                throw new  NullPointerException();
            }
            tr.commit();
            return obT;

        } catch (HibernateException e) {
            System.err.println("Something went wrong during findById");
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        }
    }

    public T findByName(String name) throws HibernateException{
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(typeParameterClass);
            Root<T> root = cr.from(typeParameterClass);
            cr.select(root).where(cb.equal(root.get("name"), name));  //here you pass a class field, not a table column (in this example they are called the same)

            Query<T> query = session.createQuery(cr);
            query.setMaxResults(1);
            List<T> result = query.getResultList();

            if (result.get(0) != null) {
                System.out.println("Done findByName");
            }
            tr.commit();
            return result.get(0);
        } catch (HibernateException e) {
            System.err.println("Something went wrong during findByName");
            e.printStackTrace();
            if (tr != null) {
                tr.rollback();
            }
             throw e;
        }
    }



    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }



}













