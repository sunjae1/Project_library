package Project.Project_library.Repository;

import Project.Project_library.domain.Reservation;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {

    private final EntityManager em;

    public ReservationRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Reservation reservation)
    {
        em.persist(reservation);
        em.flush(); //바로 insert 쿼리 날림.
    }
}
