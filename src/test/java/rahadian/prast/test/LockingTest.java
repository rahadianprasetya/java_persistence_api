package rahadian.prast.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rahadian.prast.entity.Brand;
import rahadian.prast.entity.Product;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import java.time.LocalDateTime;

/**
 * Author ian
 * create 07/01/25 18.55
 */
public class LockingTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void pessimisticLocking() throws InterruptedException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Brand brand = entityManager.find(Brand.class, 13, LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Oppo Update");
        brand.setUpdatedAt(LocalDateTime.now());


        Thread.sleep(10 * 1000L);
        entityManager.persist(brand);

        transaction.commit();
        entityManager.close();
    }

    @Test
    void pessimisticLocking2() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Brand brand = entityManager.find(Brand.class, 13, LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Oppo Update 2");
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        transaction.commit();
        entityManager.close();
    }
}
