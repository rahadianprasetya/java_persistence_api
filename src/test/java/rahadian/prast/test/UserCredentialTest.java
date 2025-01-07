package rahadian.prast.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rahadian.prast.entity.*;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Author ian
 * create 04/01/25 16.27
 */
public class UserCredentialTest {

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

        Credential credential = new Credential();
        credential.setId(6L);
        credential.setEmail("user@example.com");
        credential.setPassword("Alfariel");
        entityManager.persist(credential);

        Users users = new Users();
        users.setId(6L);
        users.setName("Alfariel");
        entityManager.persist(users);

        transaction.commit();
        entityManager.close();

    }


    @Test
    void findById() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users =  entityManager.find(Users.class, 5L);
        Assertions.assertNotNull(users.getCredential());
        Assertions.assertNotNull(users.getWallet());

        Assertions.assertEquals("rahadian@rahadian.com", users.getCredential().getEmail());
        Assertions.assertEquals("rahadian", users.getCredential().getPassword());

        Assertions.assertEquals(1_000_000, users.getWallet().getBalance());

        transaction.commit();
        entityManager.close();
    }


    @Test
    void joinColumnWallet() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users =  entityManager.find(Users.class, 5L);

        Wallet wallet = new Wallet();
        wallet.setUsers(users);
        wallet.setBalance(1_000_000L);
        entityManager.persist(wallet);

        transaction.commit();
        entityManager.close();
    }


    @Test
    void findUserWallet() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users =  entityManager.find(Users.class, 5L);

        Assertions.assertNotNull(users.getWallet());
        Assertions.assertEquals(1_000_000L, users.getWallet().getBalance());

        transaction.commit();
        entityManager.close();
    }
}
