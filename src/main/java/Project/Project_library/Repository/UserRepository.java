package Project.Project_library.Repository;

import Project.Project_library.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();


    Optional<User> findByEmail(String email);

}
