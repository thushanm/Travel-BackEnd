package lk.travel.authservice.repo;

import lk.travel.authservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);
}
