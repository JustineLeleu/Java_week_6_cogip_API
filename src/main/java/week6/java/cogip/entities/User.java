package week6.java.cogip.entities;

// All imports for the User entity
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// The User entity
// The @Entity annotation tells Spring that this is a JPA entity, and it should be mapped to a database table.
// The @Table annotation specifies the name of the database table to be used for mapping.
// The @Id annotation specifies the primary key of an entity.
// The @GeneratedValue annotation is used to specify the primary key generation strategy to use.
// The @Setter and @Getter annotations from Lombok are used to generate the setters and getters for the entity.
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String role;
    

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public User() {
    
    }
    
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }
    
}
