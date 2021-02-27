package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.wiosnademo.domain.Group;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {

    @Query("SELECT g FROM groups g left join fetch g.users WHERE g.name = :name")
        // tzn że ja definiuję zapytanie SQL, czyli HQL
        //pobierze grupę z użytkownikami
    Optional<Group> findByGroupNameWithUsers(@Param("name") String name);   //@Param = musi być, żeby czytał parametr 'name',wykorzystuje EntityManagera
}
