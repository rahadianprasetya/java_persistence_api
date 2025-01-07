package rahadian.prast.test;

import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Author ian
 * create 31/12/24 01.53
 */
public class TransactionTest {


    @Test
    void transaction() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Assert.assertNotNull(entityTransaction);

        try {
            entityTransaction.begin();

            entityTransaction.begin();
        }catch (Throwable throwable) {
            entityTransaction.rollback();
        }


        entityManager.close();
    }
}
