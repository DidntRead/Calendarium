package calendarium.db;

import calendarium.db.entity.Event;
import calendarium.db.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;

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
    public Event findById(long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root).where(cb.equal(root.get("event_id"), Id));
        Query queryRes = session.createQuery(query);
        Event result = (Event) queryRes.getSingleResult();
        session.close();
        return result;
    }

    @Transactional
    public Iterator<Event> findAllBetweenDate(ZonedDateTime startTime, ZonedDateTime endTime) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root).where(cb.and(cb.ge(root.get("start_time"), Timestamp.valueOf(startTime.toLocalDateTime()).getTime()), cb.le(root.get("end_time"), Timestamp.valueOf(endTime.toLocalDateTime()).getTime())));
        Query queryRes = session.createQuery(query);
        List<Event> results = queryRes.getResultList();
        session.close();
        return results.iterator();
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
