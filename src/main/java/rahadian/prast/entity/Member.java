package rahadian.prast.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Author ian
 * create 03/01/25 21.41
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_member")
    @SequenceGenerator(sequenceName = "sq_member", allocationSize = 1, name = "sq_member")
    private Long id;

    @Embedded
    private Name name;



    private String email;

    @ElementCollection
    @CollectionTable(name = "hobby", joinColumns = @JoinColumn(
            name = "member_id", referencedColumnName = "id"
    ))
    @Column(name = "name")
    private List<String> hobby;

    @ElementCollection
    @CollectionTable(name = "skill", joinColumns = @JoinColumn(
            name = "member_id", referencedColumnName = "id"
    ))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, Integer> skills;

    @Transient
    private String fullName;

    @PostLoad
    public void postLoad(){
        fullName = name.getTitle() + " " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }
}
