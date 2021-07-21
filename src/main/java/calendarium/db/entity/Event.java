package calendarium.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "event", schema = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventID;
    
    @Column(name = "event_name")
    String name;

    @Column(name = "event_notification")
    boolean eventNotification;

    @Column(name = "event_desc")
    String description;

    @Column(name = "start_time")
    ZonedDateTime startTime;

    @Column(name = "end_time")
    ZonedDateTime endTime;
}
