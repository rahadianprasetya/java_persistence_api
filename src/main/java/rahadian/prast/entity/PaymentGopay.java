package rahadian.prast.entity;

import javax.persistence.Entity;

/**
 * Author ian
 * create 07/01/25 11.47
 */
@Entity
public class PaymentGopay extends Payment {

    private String gopayId;

    public String getGopayId() {
        return gopayId;
    }

    public void setGopayId(String gopayId) {
        this.gopayId = gopayId;
    }
}
