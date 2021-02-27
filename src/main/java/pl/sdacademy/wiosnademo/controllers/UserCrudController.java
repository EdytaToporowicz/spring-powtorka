package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.services.UserCrudService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/*
GET users - pobierz wszystkich userów
GET users/ID - pobierz Usera po id (username)
POST users  - dodaj Usera
PUT users/ID - aktualizuj Usera po id
DELETE users/ID - usuń Usera po id
 */

@RestController // jest to połączenie @Controller i @ResponseBody
// @Controller - i wtedy ResponseEntity...
@RequestMapping("api/users")
public class UserCrudController {

    private UserCrudService userCrudService;

    public UserCrudController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping
    // przy @Controller => public ResponseEntity <List<user>> getAllUsers().......
    public List<User> getAllUsers() {   //nie musimy użyć ResponseEntity, bo u góry użyliśmy RestController(któy ma ResponseBody)
        return userCrudService.getAllUsers();
    }

    @GetMapping("/{username}")  //{} = zmienna część ścieżki
    public User getUser(@PathVariable(name = "username") String username) {    // po PathVariable może być (name = "username")-pobiera z żądania http i do Stringa
        return userCrudService.getUserByUsername(username);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) throws URISyntaxException { // konwertuje ciało żądania z np.JSON na User
        userCrudService.createUser(user);   //tu zwroci na, body obiektu=wszsytko
        return ResponseEntity.created(new URI("/api/users/" + user.getUsername())).build();   //zwróci adres url do obiektu
    }

    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String username, @Valid @RequestBody User user) {
        userCrudService.updateUser(username, user);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) {
        userCrudService.deleteUser(username);
    }
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<List<User>> getAllUsers() {
//    return ResponseEntity.ok(userCrudService.getAllUsers());
//  }

    // http://localhost:8080/api/users/search?email=wartosc&mobile=wartosc
    //szukajka po Userze
    @GetMapping("/search")
    public Set<User> findUsers(@RequestParam(name = "email") String email, @RequestParam(name = "mobile") String mobile,
                               @RequestParam(name = "type") String type) { // or lub and
        return userCrudService.search(email, mobile, type);
    }
}
