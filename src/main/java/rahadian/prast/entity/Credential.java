package rahadian.prast.entity;

import javax.persistence.*;

/**
 * Author ian
 * create 04/01/25 16.19
 */
@Entity
public class Credential {

    @Id
    private Long id;
    private String email;
    private String password;

    @OneToOne(mappedBy = "credential")
    private Users users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
