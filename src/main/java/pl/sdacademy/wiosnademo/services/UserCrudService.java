package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service    //lub @Component = każdy działa
@Transactional  //=to tak jakby na każdej metodzie publicznej dodać tranzakcyjność
public class UserCrudService {
    private UserRepository userRepository;

    // @Autowired  //nie musi być = po nowszy Spring ma
    public UserCrudService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // stworzenie użytkownika
    public User createUser(User user) {
        userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .ifPresent(existingUser -> {
                    throw new ApplicationException("User already exists!");
                });
        return userRepository.save(user);
    }

    // aktualizacja
    public void updateUser(String username, User user) {

        //jesli User istnieje, to muszę wypakować z pudełka Optionala
        User existingUser = getUserByUsername(username);
        //zamiast duplikować kod to korzystamy z metody szukania User poniżej
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());

        // userRepository.save(existingUser); = nie jest wymagane bo Hibernate biorąc encję
        // i pracując na tym już zapisuje zmiany linijkami powyżej
    }

    //usuwanie
    public void deleteUser(String username) {   //opcjonalnie sprawdzic czy User istnieje
        getUserByUsername(username);
        userRepository.deleteById(username);
    }

    // znajdowanie Usera
    public User getUserByUsername(String username) {
        Optional<User> existingUserOptional = userRepository.findById(username);
        if (existingUserOptional.isEmpty()) {
            throw new ApplicationException("User does not exist and cannot display");
        }
        //jesli User istnieje, to muszę wypakować z pudełka Optionala
        return  existingUserOptional.get();
    }

    // znajdź wszystkich Userów
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Set<User> search(final String email, final String mobile, final String type) {
        if (type.equalsIgnoreCase("or")) {
            return userRepository.findAllByEmailOrPhoneNumberEndsWith(email, mobile);
        } else if (type.equalsIgnoreCase("and")) {
            return userRepository.findByEmailAndPhoneNumberEndsWith(email, mobile)
                    .map(usr -> Set.of(usr))
                    .orElse(Set.of());
        }
        throw new ApplicationException("Search type not found");
    }
}
