package alibek.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "char_values")
public class CharValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "char_id")
    private Characteristic characteristic;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String value;
}
