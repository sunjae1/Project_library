package Project.Project_library.UserService;

import Project.Project_library.Repository.JpaUserRepository;
import Project.Project_library.Repository.MemoryUserRepository;
import Project.Project_library.Repository.ReservationRepository;
import Project.Project_library.Repository.UserRepository;
import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public UserService(JpaUserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;

    }

    // 로그인 처리
    public boolean login(String email, String password) {
//        return user != null && user.getPassword().equals(password);

        Optional<User> user1 = userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password));

        return user1.isPresent(); //있으면 true
    }

    // 회원가입 처리
    @Transactional(readOnly = false)
    public void join(User user) {
        userRepository.save(user);
    }


    // 한 사용자 조회
    public Optional<User> findOne(String email) {
        return userRepository.findByEmail(email);
    }

    // 모든 사용자 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

////    ROOM 추가.
//    public User roomAdd(String email, Room room) {
//        String Idemail = userRepository.saveRoom(email, room);
//        User roomuser = userRepository.findOne(Idemail);
//        return roomuser;
//
//    }




    // 예약 추가 (detail 시간 추가)
    @Transactional(readOnly = false)
    public void reserve(String email, Reservation reservation) {
        System.out.println("reserve Method in in");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));


        //이전 예약 끊기.
        Reservation oldReservation = user.getReservation();
        if (oldReservation != null) //예약이 있다면,
        {
            oldReservation.setUser(null);
            user.setReservation(null); //둘 다 영속이라, 쓰기지연 SQL에 delete 들어감. 이미 엔티티 사라져서 스냅샷이랑 다름. flush() 타이밍에 delete문 SQL 만들어짐.
//            userRepository.deleteReservation(oldReservation);
// 근데 여기서 이미 영속성 컨텍스트에 엔티티로 없는걸 요청하니깐(이미 삭제) Transient exception 터짐.
            userRepository.flush(); //삭제 쿼리 먼저 날림.

        }

        reservationRepository.save(reservation);


        reservation.setUser(user); //FK 넣음.
        user.setReservation(reservation);


        System.out.println("save before===========");

//        userRepository.save(user); //User에 reserve 까지 포함한 객체 HahMap에 put.
        System.out.println("save after===========");
    }



}
