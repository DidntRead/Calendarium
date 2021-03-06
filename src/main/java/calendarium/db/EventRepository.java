package calendarium.db;

import calendarium.db.entity.Event;
import calendarium.db.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;

public class EventRepository {
    @Transactional
    public void delete(Event ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.delete(ev);
        trans.commit();
        session.close();
    }

    @Transactional
    public void deleteAll(Iterator<Event> ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        while(ev.hasNext()) {
            Event e = ev.next();
            session.delete(e);
        }
        trans.commit();
        session.close();
    }

    @Transactional
    public void save(Event ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.save(ev);
        trans.commit();
        session.close();
    }

    @Transactional
    public void saveAll(Iterator<Event> ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        while(ev.hasNext()) {
            Event e = ev.next();
            session.save(e);
        }
        trans.commit();
        session.close();
    }

    @Transactional
    public long count() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root);
        Query queryRes = session.createQuery(query);
        long size = queryRes.getResultList().size();
        session.close();
        return size;
    }

    @Transactional
    public Iterator<Event> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root);
        Query queryRes = session.createQuery(query);
        Iterator<Event> it = queryRes.getResultList().iterator();
        session.close();
        return it;
    }
    
    @Transactional
    public Event findById(long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root).where(cb.equal(root.get("eventID"), Id));
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
        query.select(root).where(cb.and(cb.greaterThanOrEqualTo(root.get("endTime"), startTime), cb.lessThanOrEqualTo(root.get("startTime"), endTime)));
        Query queryRes = session.createQuery(query);
        List<Event> results = queryRes.getResultList();
        session.close();
        return results.iterator();
    }

    @Transactional
    public Iterator<Event> findAllContainingDate(ZonedDateTime date) {

        ZonedDateTime start = date;
        ZonedDateTime end = date.plus(Period.ofDays(1));
        return this.findAllBetweenDate(start,end);
    }

    @Transactional
    public Iterator<Event> findAllWithNotifications(boolean notificationsEnabled) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root).where(cb.equal(root.get("eventNotification"), notificationsEnabled));
        Query queryRes = session.createQuery(query);
        List<Event> results = queryRes.getResultList();
        session.close();
        return results.iterator();
    }

    @Transactional
    public Event findNextEventWithNotification() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        query.select(root).orderBy(cb.asc(root.get("startTime"))).where(cb.and(cb.greaterThanOrEqualTo(root.get("startTime"), ZonedDateTime.now()), cb.isTrue(root.get("eventNotification"))));
        Query queryRes = session.createQuery(query);
        List<Event> results = queryRes.getResultList();
        return results.size() == 0 ? null : results.get(0);
    }

    @Transactional()
    public void update(Event ev) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            session.update(ev);
            trans.commit();
            session.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateAll(Iterator<Event> ev) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        while(ev.hasNext()) {
            Event e = ev.next();
            session.update(e);
        }
        trans.commit();
        session.close();
    }
}
