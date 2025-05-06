package Project.Project_library.domain;

import java.util.List;

public class Reservation {
    private String date; //yyyy-MM-dd
    private List<String> times; //["10:00", "11:00", "12:00"]
    private Room room;

    //생성자
    public Reservation(String date, List<String> times, Room room) {
        this.date = date;
        this.times = times;
        this.room = room;
    }

    //Getter Setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
