package rahadian.prast.test;

import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Author ian
 * create 30/12/24 19.39
 */
public class EntityManagerTest {


    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Pengecheckan
        Assert.assertNotNull(entityManager);
        // close connection
        entityManager.close();

    }
}
