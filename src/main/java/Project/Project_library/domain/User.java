package Project.Project_library.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    private Reservation reservation; // 새 방식.

    // 생성자


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }



    // getter
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    //방을 가진 객체 반환 -- Setter 닫고, 새 객체 생성 방법. --
    public User withRoom(Room room) {
        return new User(this.email,this.password);
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



    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


}