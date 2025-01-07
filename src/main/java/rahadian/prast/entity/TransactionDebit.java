package rahadian.prast.entity;

import javax.persistence.Entity;

/**
 * Author ian
 * create 07/01/25 13.47
 */
@Entity
public class TransactionDebit extends Transaction {

    private Long debitAmount;


    public Long getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Long debitAmount) {
        this.debitAmount = debitAmount;
    }
}
