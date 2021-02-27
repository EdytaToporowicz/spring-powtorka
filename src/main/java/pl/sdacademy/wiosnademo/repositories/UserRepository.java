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
    //Set szybszy niż List
    Set<User> findAllByPhoneNumberEndsWith(String phoneNumber);

    //po nr tel i emailu
    Optional<User> findByEmailAndPhoneNumberEndsWith(String email, String phoneNumber);

    //po nr tel lub email
    Set<User> findAllByEmailOrPhoneNumberEndsWith(String email, String phoneNumber);

    //znajdź po nazwie użyt i email
    Optional<User> findByUsernameOrEmail(String username, String email);
}
