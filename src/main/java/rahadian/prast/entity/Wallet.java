package rahadian.prast.entity;

import javax.persistence.*;

/**
 * Author ian
 * create 04/01/25 17.18
 */
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_member")
    @SequenceGenerator(sequenceName = "sq_member", allocationSize = 1, name = "sq_member")
    private Long id;
    private Long balance;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
