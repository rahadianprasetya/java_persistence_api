package rahadian.prast.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Author ian
 * create 06/01/25 21.50
 */
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {


    private Integer totalEmployee;

    public Integer getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(Integer totalEmployee) {
        this.totalEmployee = totalEmployee;
    }
}
