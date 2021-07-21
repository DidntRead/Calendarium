package calendarium.bl;

import calendarium.db.EventRepository;
import calendarium.db.entity.Event;

import java.time.ZonedDateTime;
import java.util.Iterator;

public class DataBaseManager {

    private EventRepository eventRepository;

    public DataBaseManager() {
        eventRepository = new EventRepository();
    }

    public boolean addEvent(String name, String description, ZonedDateTime startTime, ZonedDateTime endTime, boolean notification) {
        try {
            Event event = new Event();
            event.setName(name);
            event.setDescription(description);
            if (startTime.toInstant().compareTo(endTime.toInstant()) > 0)
                throw new Exception("StartTime is after endTime!");
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setEventNotification(notification);
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addEvent(Event event) throws Exception {

        if (event.getStartTime().toInstant().compareTo(event.getEndTime().toInstant()) > 0)
            throw new Exception("StartTime is after endTime!");
        eventRepository.save(event);

    }

    public boolean updateEvent(long id, String name, String description, ZonedDateTime startTime, ZonedDateTime endTime, boolean notification) {
        Event ev;
        try {
            if ((ev = eventRepository.findById(id)) == null) {
                throw new Exception("Event not found!");
            }
            if (startTime.toInstant().compareTo(endTime.toInstant()) > 0)
                throw new Exception("StartTime is after endTime!");
            ev.setName(name);
            ev.setDescription(description);
            ev.setStartTime(startTime);
            ev.setEndTime(endTime);
            ev.setEventNotification(notification);
            eventRepository.update(ev);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateEvent(Event event) throws Exception {

        if (eventRepository.findById(event.getEventID()) == null) {
            throw new Exception("Event not found!");
        }
        if (event.getStartTime().toInstant().compareTo(event.getEndTime().toInstant()) > 0)
            throw new Exception("Start time is after end time!");
        eventRepository.update(event);

    }

    public boolean delete(long id) {
        try {
            Event event;
            if ((event = eventRepository.findById(id)) == null) {
                throw new Exception("Event not found!");
            }
            eventRepository.delete(event);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Iterator<Event> getEventsBetween(ZonedDateTime startTime, ZonedDateTime endTime) {
        Iterator<Event> iterator = eventRepository.findAllBetweenDate(startTime, endTime);
        return iterator;
    }

    public Iterator<Event> getEventsWithNotifications(boolean notificationsEnabled) {
        return eventRepository.findAllWithNotifications(notificationsEnabled);
    }

    public Event getNextEventWithNotification() {
        return eventRepository.findNextEventWithNotification();
    }

    public Iterator<Event> getEventsContainingDate(ZonedDateTime date) {
        return eventRepository.findAllContainingDate(date);
    }
}
