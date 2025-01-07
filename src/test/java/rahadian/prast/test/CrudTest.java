package rahadian.prast.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;
import rahadian.prast.entity.Customer;
import rahadian.prast.entity.CustomerType;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Author ian
 * create 31/12/24 02.03
 */
public class CrudTest {

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

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("Ian");
        customer.setAge(23);
        customer.setMarried(true);
        customer.setType(CustomerType.PREMIUM);

        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();

    }


    @Test
    void findById() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer =  entityManager.find(Customer.class, "2");
        Assertions.assertEquals("2", customer.getId());
        Assertions.assertEquals("Ian", customer.getName());
        Assertions.assertEquals(23, customer.getAge());
        Assertions.assertTrue(customer.getMarried());
        Assertions.assertEquals(CustomerType.PREMIUM, customer.getType());

        transaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer =  entityManager.find(Customer.class, "2");
        customer.setName("Ian Rahadian");
        entityManager.merge(customer);

        transaction.commit();
        entityManager.close();
    }

    @Test
    void delete() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer =  entityManager.find(Customer.class, "2");
        entityManager.remove(customer);

        transaction.commit();
        entityManager.close();
    }
}
