package shop.project.mall.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.project.mall.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findByEmail(String email);


    Optional<User> findByEmailAndProvider(String email, String provider);


}
