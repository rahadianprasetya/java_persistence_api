package rahadian.prast.entity;

import javax.persistence.Entity;

/**
 * Author ian
 * create 07/01/25 11.46
 */
@Entity
public class PaymentCreditcard extends Payment {


    private String maskedCard;
    private String bank;


    public String getMaskedCard() {
        return maskedCard;
    }

    public void setMaskedCard(String maskedCard) {
        this.maskedCard = maskedCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
