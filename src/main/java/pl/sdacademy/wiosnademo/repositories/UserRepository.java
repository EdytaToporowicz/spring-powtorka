package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.wiosnademo.domain.User;

import java.util.Optional;
import java.util.Set;

// @Repository - nie dokładamy, bo nie trzeba
public interface UserRepository extends JpaRepository<User, String> { //jest jeszcze interfejs Crud
    // znajdź User - ale jak nie znajdzie to null, lepiej Optional

    // po mailu-unikalny
    Optional<User> findByEmail(String email);

    //po nr tel-nie unikalny
    //123 123 123 = taki lub +48 123 123 123
    Set<User> findAllByPhoneNumberEndsWith(String phoneNumber);

}
