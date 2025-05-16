package Project.Project_library.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class  Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date; //yyyy-MM-dd


    @ElementCollection
    @CollectionTable(name = "reservation_times",
            joinColumns = @JoinColumn(name = "reservation_id"))
    private List<String> times = new ArrayList<>(); //["10:00", "11:00", "12:00"]

    @Enumerated(EnumType.STRING)
    private Room room;



    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    protected Reservation() {
    }

    //생성자
    public Reservation(LocalDate date, List<String> times, Room room) {
        this.date = date;
        this.times = times;
        this.room = room;
    }








    //Getter Setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
