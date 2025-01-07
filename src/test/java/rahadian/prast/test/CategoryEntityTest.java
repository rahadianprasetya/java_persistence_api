package rahadian.prast.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rahadian.prast.entity.Category;
import rahadian.prast.entity.Customer;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Author ian
 * create 02/01/25 23.18
 */
public class CategoryEntityTest {

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

        Category category = new Category();
        category.setName("Belajar Vue 3");
        category.setDescription("Vue vit 3");
        entityManager.persist(category);
        Assertions.assertNotNull(category.getId());


        transaction.commit();
        entityManager.close();

    }

    @Test
    void findById() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Category category =  entityManager.find(Category.class, 1);
        Assertions.assertEquals(1, category.getId());
        Assertions.assertEquals("Belajar Java Persistence", category.getName());
        Assertions.assertEquals("Java Persistence", category.getDescription());

        transaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Category category =  entityManager.find(Category.class, 2);
        category.setName("Vue vite 3");
        category.setDescription("Belajar Vue 3");
        entityManager.merge(category);

        transaction.commit();
        entityManager.close();
    }
}
