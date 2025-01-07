package rahadian.prast.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Author ian
 * create 30/12/24 19.22
 */
public class EntitymanagerFactoryTest {


    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        Assertions.assertNotNull(entityManagerFactory);
    }
}
