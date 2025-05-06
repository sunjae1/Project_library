package Project.Project_library.domain;

import java.util.ArrayList;
import java.util.List;

public class AllTime {

    private List<String> alltime;

    //생성자, 전체 시간 템플릿 세팅.
    public AllTime() {
        alltime = new ArrayList<>();
        for (int hour = 9; hour <= 21; hour++) {
            alltime.add(String.format("%02d:00", hour));
        }
    }

    public List<String> getAlltime() {
        return alltime;
    }
}
