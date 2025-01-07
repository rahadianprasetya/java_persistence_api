package rahadian.prast.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Author ian
 * create 30/12/24 19.19
 */
public class JpaUtil {

    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManagerFactory getEntityManagerFactory() {

        if(entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("BELAJAR");
        }

        return entityManagerFactory;
    }
}
