package rahadian.prast.entity;

import javax.persistence.*;

/**
 * Author ian
 * create 30/12/24 12.59
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private String id;
    private String name;
    private Integer age;
    private Boolean married;
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}
