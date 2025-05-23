package Project.Project_library.domain;

public class User {
    private String email;
    private String password;
    private Room room; //기존 방식.
    private Reservation reservation; // 새 방식.

    // 생성자


    public User(String email, String password, Room room) {
        this.email = email;
        this.password = password;
        this.room = room;
    }

    // getter
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Room getRoom() {
        return room;
    }

    //방을 가진 객체 반환 -- Setter 닫고, 새 객체 생성 방법. --
    public User withRoom(Room room) {
        return new User(this.email,this.password,room);
    }

    //예약 생성자
    public void reserve(Reservation r) {
        this.reservation = r;
    }

    //예약 Getter
    public Reservation getReservation() {
        return reservation;
    }


    //Setter


}