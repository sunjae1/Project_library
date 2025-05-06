package Project.Project_library.UserService;

import Project.Project_library.Repository.TimeRepository;
import Project.Project_library.Repository.UserRepository;
import Project.Project_library.domain.AllTime;
import Project.Project_library.domain.Room;
import Project.Project_library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {
    private final TimeRepository timeRepository;
    private final UserRepository userRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository, UserRepository userRepository) {
        this.timeRepository = timeRepository;
        this.userRepository = userRepository;
    }

    public void reserve(String date, Room room, List<String> times) {
        timeRepository.addReservation(date, room, times);
    }

    /**
     * 시간을 보여주는 메소드.
     */
    public List<String> timeShow(Room room) {
        AllTime allTime = new AllTime();
        List<String> alltimes = allTime.getAlltime();
        List<User> all = userRepository.findAll(); //모든 유저 리스트로 받음.

        for (User user : all) {
            if (user.getRoom() == room)
            {   //ex) room2랑 반복 객체 user가 고른 room이 room2랑 같으면.
                if (user.getReservation() !=null) { //처음이 아닌경우.
                    List<String> times1 = user.getReservation().getTimes();
                    /*임시*/
                    List<String> cleanedTimes = times1.stream()
                            .map(String::trim) // 공백 제거
                            .collect(Collectors.toList());

                    alltimes.removeAll(cleanedTimes);

                    System.out.println("alltimes = " + alltimes);
                    System.out.println("alltimes = " + alltimes);
                    System.out.println("alltimes = " + alltimes);
                    System.out.println("alltimes = " + alltimes);
                    System.out.println("alltimes = " + alltimes);
                    System.out.println("alltimes = " + alltimes);


//                    alltimes.removeAll(times1);

//                    return alltimes;
                }
            }
        }
        return alltimes;
    }


    //시간 취소.
    public void timeCancel(String email) {
        //form 태그로 name에 email이 날아왔다 가정. //아니면 세션에서 User 객체
        User user = userRepository.findOne(email);
        user.getReservation().setTimes(new ArrayList<>());
        userRepository.save(user);


    }




}
