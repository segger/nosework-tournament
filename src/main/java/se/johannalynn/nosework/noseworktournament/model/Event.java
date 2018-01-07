package se.johannalynn.nosework.noseworktournament.model;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private EventType type;

    @Column
    private Level level;

    //@Column
    //private DateTime maxTime;

    //points
    //results

    public Event() {
    }
}
