package calendarium.db;

import calendarium.db.entity.Event;
import calendarium.db.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Iterator;

public class EventRepository {
    @Transactional
    public void delete(Event ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.delete(ev);
        session.close();
    }

    @Transactional
    public void deleteAll(Iterator<Event> ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        while(ev.hasNext()) {
            Event e = ev.next();
            session.delete(e);
        }
        session.close();
    }

    @Transactional
    public void save(Event ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(ev);
        session.close();
    }

    @Transactional
    public void saveAll(Iterator<Event> ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        while(ev.hasNext()) {
            Event e = ev.next();
            session.save(e);
        }
        session.close();
    }

    @Transactional
    public long count() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery("select * from event");
        long count = query.getResultList().size();
        session.close();
        return count;
    }

    @Transactional
    public Iterator<Event> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery("select * from event");
        Iterator<Event> it = query.getResultList().iterator();
        session.close();
        return it;
    }

    @Transactional
    public void update(Event ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.update(ev);
        session.close();
    }

    @Transactional
    public void updateAll(Iterator<Event> ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        while(ev.hasNext()) {
            Event e = ev.next();
            session.update(e);
        }
        session.close();
    }
}
