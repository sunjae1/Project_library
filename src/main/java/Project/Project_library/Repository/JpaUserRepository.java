package Project.Project_library.Repository;

import Project.Project_library.domain.Reservation;
import Project.Project_library.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository{


    @PersistenceContext
    private EntityManager em;


    public JpaUserRepository() {
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class,id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u",User.class)
                .getResultList();
    }

    @Override
    public Optional<User>findByEmail(String email) {
        List<User> result = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return result.stream().findFirst();
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        em.remove(reservation);
        em.flush();
    }

    @Override
    public void flush() {
        em.flush();
    }


//    @Override
//    public void selectReservation(Long id) {
//        em.find(Re)
//    }
}
