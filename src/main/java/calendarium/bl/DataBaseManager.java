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

    public void addEvent(Event event) throws Exception {

        if(event.getName().isEmpty()){
            throw new Exception("Every event should have tittle!");
        }

        if (event.getStartTime().toInstant().compareTo(event.getEndTime().toInstant()) > 0)
            throw new Exception("StartTime is after endTime!");


        eventRepository.save(event);

    }

    public void updateEvent(Event event) throws Exception {

        if(event.getName().isEmpty()){
            throw new Exception("Every event should have tittle!");
        }
        if (eventRepository.findById(event.getEventID()) == null) {
            throw new Exception("Event not found!");
        }
        if (event.getStartTime().toInstant().compareTo(event.getEndTime().toInstant()) > 0)
            throw new Exception("Start time is after end time!");
        eventRepository.update(event);

    }

    public void delete(Event event) {
            eventRepository.delete(event);
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
