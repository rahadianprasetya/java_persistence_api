package rahadian.prast.entity;

import javax.persistence.*;

/**
 * Author ian
 * create 06/01/25 21.45
 */
@Entity
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_employee")
    @SequenceGenerator(sequenceName = "sq_employee", allocationSize = 1, name = "sq_employee")
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
