package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User getByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User getByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role.roleName = 'Admin'")
    User getAdmin();

    @Query("SELECT u FROM User u WHERE u.role.roleName = 'Customer'")
    List<User> getCustomers();
}
