package rahadian.prast.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rahadian.prast.entity.Brand;
import rahadian.prast.entity.Product;
import rahadian.prast.entity.Users;
import rahadian.prast.entity.Wallet;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Author ian
 * create 06/01/25 13.05
 */
public class RelationshipTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }


    @Test
    void oneToMany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Brand brand = new Brand();
        brand.setId(13);
        brand.setName("Oppo");
        brand.setDescription("Oppo In China");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        Product product1 = new Product();
        product1.setName("Oppo A12");
        product1.setDescription("Cell phone Oppo");
        product1.setPrice(1_000_000L);
        product1.setBrand(brand);
        entityManager.persist(product1);


        Product product2= new Product();
        product2.setName("REDNOTE 14 Pro Max");
        product2.setDescription("Cell phone type REDNODE");
        product2.setPrice(1_500_000L);
        product2.setBrand(brand);
        entityManager.persist(product2);

        transaction.commit();
        entityManager.close();
    }


    @Test
    void findBrand() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Brand brand = entityManager.find(Brand.class, 13);
        Assertions.assertNotNull(brand.getVersion());
        Assertions.assertEquals(2, brand.getProducts().size());

        brand.getProducts().forEach(product -> {
            System.out.println(product.getName());
        });

        transaction.commit();
        entityManager.close();
    }

    @Test
    void ManyToMany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users user =  entityManager.find(Users.class, 6L);
        user.setLikes(new HashSet<>());
        Product product1 = entityManager.find(Product.class, 6);
        Product product2 = entityManager.find(Product.class, 7);

        user.getLikes().add(product1);
        user.getLikes().add(product2);

        entityManager.merge(user);
        transaction.commit();
        entityManager.close();
    }


    @Test
    void findUsersProducts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users user =  entityManager.find(Users.class, 6L);

        Assertions.assertEquals(2, user.getLikes().size());

        user.getLikes().forEach(product -> {
            System.out.println(product.getName());
        });

        transaction.commit();
        entityManager.close();
    }
}
