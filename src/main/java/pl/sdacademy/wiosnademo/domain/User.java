package pl.sdacademy.wiosnademo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Length(min = 3)
    private String username;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    //@Pattern(//regex) lub @AssertTrue na dole
    @JsonProperty("mobile")         //pole w Jsonie będzie się nazywało "mobile"
    private String phoneNumber;

    @JsonIgnore             //żeby JSON nie wyświetlał nam w body tego pola
    @CreationTimestamp      //automatycznie zadzieje się data i czas
    private LocalDateTime localDateTime;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @JsonIgnore
    @AssertTrue     //zamiast @Pattern
    public boolean isPhoneNumberValid() {
        if (phoneNumber == null) {
            return true;
        }
        String[] splitData = phoneNumber.split(" ");
        if ((splitData.length == 3)) {
            boolean allElementsHaveSameExpectedSize = Arrays.stream(splitData).allMatch(subPart -> subPart.length() == 3);
            if (!allElementsHaveSameExpectedSize) {
                return false;
            }

            return Arrays.stream(splitData).allMatch(subPart -> subPart.chars().allMatch(ch -> Character.isDigit(ch)));
        } else if (splitData.length == 4) {
            //todo
        }


        return false;
    }
}
