package highton.team2.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String password;
    private String name;
    private Integer age;
    private String school;
    @ElementCollection
    private List<String> license;
}