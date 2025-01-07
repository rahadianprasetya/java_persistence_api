package rahadian.prast.entity;

import javax.persistence.Entity;

/**
 * Author ian
 * create 07/01/25 13.46
 */
@Entity
public class TransactionCredit extends Transaction {

    private Long creditAmount;

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }
}
