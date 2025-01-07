package rahadian.prast.entity;

import rahadian.prast.listener.UpdatedAtListener;
import rahadian.prast.model.UpdatedAtAware;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Author ian
 * create 07/01/25 13.43
 */
@Entity
@EntityListeners({
        UpdatedAtListener.class
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Transaction implements UpdatedAtAware {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_transaction")
    @SequenceGenerator(sequenceName = "sq_transaction", allocationSize = 1, name = "sq_transaction")
    private Long id;
    private Long balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
