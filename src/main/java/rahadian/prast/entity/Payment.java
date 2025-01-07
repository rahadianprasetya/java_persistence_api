package rahadian.prast.entity;

import javax.persistence.*;

/**
 * Author ian
 * create 07/01/25 11.43
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_payment")
    @SequenceGenerator(sequenceName = "sq_payment", allocationSize = 1, name = "sq_payment")
    private Long id;
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
