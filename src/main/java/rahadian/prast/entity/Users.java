package rahadian.prast.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Author ian
 * create 04/01/25 16.18
 */
@Entity
public class Users {

    @Id
    private Long id;
    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn(
            name = "id",
            referencedColumnName = "id"
    )
    private Credential credential;

    @OneToOne(mappedBy = "users")
    private Wallet wallet;

    @ManyToMany
    @JoinTable(
            name = "users_like_products",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> likes;

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

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Set<Product> getLikes() {
        return likes;
    }

    public void setLikes(Set<Product> likes) {
        this.likes = likes;
    }
}
