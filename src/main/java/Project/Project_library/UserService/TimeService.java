package Project.Project_library.UserService;

import Project.Project_library.Repository.JpaUserRepository;
import Project.Project_library.Repository.TimeRepository;
import Project.Project_library.Repository.MemoryUserRepository;
import Project.Project_library.Repository.UserRepository;
import Project.Project_library.domain.AllTime;
import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TimeService {
    private final TimeRepository timeRepository;
    private final UserRepository userRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository, JpaUserRepository jpaUserRepository) {
        this.timeRepository = timeRepository;
        this.userRepository = jpaUserRepository;
    }

    public void reserve(LocalDate date, Room room, List<String> times) {
        timeRepository.addReservation(date, room, times);
    }

    /**
     * 시간을 보여주는 메소드.
     */
    public List<String> timeShow(Room room) {
        AllTime allTime = new AllTime();
        List<String> alltimes = allTime.getAlltime();

        /**
         * 여기서부터 JPA 로 교체.
         */


//        List<User> all = userRepository.findAll(); //모든 유저 리스트로 받음.

        List<User> all = userRepository.findAll();


        for (User user : all) {
            if (user.getReservation()!=null)
            { //예약이 없을때, 널포인트예외 안터지게.
                if (user.getReservation().getRoom() == room) {   //ex) room2랑 반복 객체 user가 고른 room이 room2랑 같으면.

                        List<String> times1 = user.getReservation().getTimes();
                        /*임시*/ // 공백 제거
                        List<String> cleanedTimes = times1.stream()
                                .map(String::trim)
                                .collect(Collectors.toList());

                        alltimes.removeAll(cleanedTimes);



                }
            }
        }
        return alltimes;

        /**
         * 여기까지.====================================
         */
    }


    //시간 취소. --> 전체 예약 초기화
    @Transactional(readOnly = false)
    public void timeCancel(String email) {
        //form 태그로 name에 email이 날아왔다 가정. //아니면 세션에서 User 객체
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));


        //전체 예약 초기화. --> em.createQuery 해도 영속성에서 관리해서 변경감지 가능.

        Reservation reservation = user.getReservation();
//        Long id = user.getId();

        // RESERVATION_TIMES ID 제거.
//        userRepository.selectReservation(Long id) {
//
//        }



        if (user.getReservation() != null) //예약 있을때만 기능.
        {
            user.setReservation(null); //연관관계 끊고 . 읽기 전용.
//            userRepository.flush();

            userRepository.deleteReservation(reservation);


//            reservation.setUser(null);



//        user.getReservation().setTimes(new ArrayList<>());
//        userRepository.save(user);
        }



    }




}
