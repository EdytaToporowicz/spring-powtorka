package pl.sdacademy.wiosnademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
public class Group {

    @Id
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GroupType type;

    //powiązanie User i Group
    // @ManyToMany (fetch = FetchType.EAGER) = pobieranie Userów z Group na chama
    @ManyToMany
    @JoinTable  // pokaże jak ma wyglądać relacja(łączona tabela)
            (name = "groups_to_users",
                    joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "name"),
                    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"))
    // zobaczyć w H2 jakie nazwy tabel i kolumn

    private List<User> users = new ArrayList<>();

}
