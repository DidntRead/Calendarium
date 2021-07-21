package calendarium.bl;

import calendarium.db.EventRepository;
import calendarium.db.entity.Event;

import java.time.ZonedDateTime;
import java.util.Iterator;

public class DataBaseManager {

    private EventRepository eventRepository;

    public DataBaseManager(){
        eventRepository=new EventRepository();
    }



    public boolean addEvent(String name, String description, ZonedDateTime startTime, ZonedDateTime endTime, boolean notification){
        try {
            Event event=new Event();
            event.setName(name);
            event.setDescription(description);
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            eventRepository.save(event);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEvent(long id, String name, String description, ZonedDateTime startTime, ZonedDateTime endTime, boolean notification){
        Event ev;
        try {
            if((ev = eventRepository.findById(id))==null){
                throw new Exception("Event not found!");
            }
            ev.setName(name);
            ev.setDescription(description);
            ev.setStartTime(startTime);
            ev.setEndTime(endTime);
            eventRepository.update(ev);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id){
        try {
            Event event;
            if((event=eventRepository.findById(id))==null){
                throw new Exception("Event not found!");
            }
            eventRepository.delete(event);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Iterator<Event> getEventsBetween(ZonedDateTime startTime,ZonedDateTime endTime){
        Iterator<Event> iterator=eventRepository.findAllBetweenDate(startTime,endTime);
        return iterator;
    }

    public Iterator<Event> getEventsWithNotifications(boolean notificationsEnabled) {
        return eventRepository.findAllWithNotifications(notificationsEnabled);
    }
}
