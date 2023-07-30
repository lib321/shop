package alibek.shop.entity;

import alibek.shop.entity.enumeration.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "users")
    private List<Review> reviews;

    @OneToMany(mappedBy = "users")
    private List<CartItems> cartItems;

    @OneToMany(mappedBy = "userId")
    private List<Orders> orders;

    @Enumerated
    private UserRole role;

    private String login;

    private String password;

    private String name;

    private String surname;

    private LocalDateTime registered_at;
}
