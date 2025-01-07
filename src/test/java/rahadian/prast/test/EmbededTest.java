package rahadian.prast.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rahadian.prast.entity.Member;
import rahadian.prast.entity.Name;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author ian
 * create 31/12/24 02.03
 */
public class EmbededTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void insert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member members = new Member();

        members.setEmail("arel@rahadian.com");

        Name name = new Name();
        name.setTitle("MR");
        name.setFirstName("Rahadian");
        name.setMiddleName("Alfian");
        name.setLastName("Prasetya");

        members.setName(name);
        members.setHobby(new ArrayList<>());
        members.getHobby().add("CODING");
        members.getHobby().add("GAMING");

        members.setSkills(new HashMap<>());
        members.getSkills().put("JAVA", 90);
        members.getSkills().put("GOLANG", 90);
        members.getSkills().put("VUE", 85);

        entityManager.persist(members);
        transaction.commit();
        entityManager.close();

    }

    @Test
    void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member members = entityManager.find(Member.class, 10L);
        members.getHobby().add("TRAVELING");
        members.getSkills().put("JAVA", 90);
        members.getSkills().put("GOLANG", 90);
        members.getSkills().put("VUE", 85);


        entityManager.persist(members);
        transaction.commit();
        entityManager.close();

    }

    @Test
    void getFullname() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Member members = entityManager.find(Member.class, 10L);
        Assertions.assertEquals("MR Rahadian Alfian Prasetya", members.getFullName());

        transaction.commit();
        entityManager.close();
    }
}
