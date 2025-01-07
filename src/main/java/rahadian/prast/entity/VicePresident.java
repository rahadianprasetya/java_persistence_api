package rahadian.prast.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Author ian
 * create 06/01/25 21.52
 */
@Entity
@DiscriminatorValue("VP")
public class VicePresident extends Employee{

    private Integer totalManager;


    public Integer getTotalManager() {
        return totalManager;
    }

    public void setTotalManager(Integer totalManager) {
        this.totalManager = totalManager;
    }
}
