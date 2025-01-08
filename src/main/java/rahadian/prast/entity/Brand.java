package rahadian.prast.entity;

import rahadian.prast.model.UpdatedAtAware;

import javax.persistence.*;
import java.util.List;

/**
 * Author ian
 * create 06/01/25 11.47
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Brand.findAll", query = "select b from Brand b"),
        @NamedQuery(name = "Brand.findAllByName", query = "select b from Brand b where b.name = :name")
})
public class Brand extends AuditableEntity<Integer> {


    private String name;
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}
