package rahadian.prast.test;

import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;
import rahadian.prast.entity.Brand;
import rahadian.prast.entity.Member;
import rahadian.prast.entity.Product;
import rahadian.prast.entity.Users;
import rahadian.prast.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author ian
 * create 07/01/25 21.26
 */
public class JpaQueryLanguangeTest {


    @Test
    void select() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> resultList = query.getResultList();

        for (Brand brand : resultList) {
            System.out.println(brand.getName());
            System.out.println(brand.getDescription());

        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void where() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where m.name.firstName = :firstName " +
                                                                      "and m.name.lastName = :lastName", Member.class);

        query.setParameter("firstName", "Rahadian");
        query.setParameter("lastName", "Prasetya");

        List<Member> members = query.getResultList();



        for (Member member : members) {
            System.out.println(member.getFullName());
        }

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void joinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :name", Product.class);
        query.setParameter("name", "Xiaomi");

        List<Product> resultList = query.getResultList();

        for (Product product : resultList) {
            System.out.println(product.getName()+ " : " + product.getDescription());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinFetch() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Users> query = entityManager.createQuery("select u from Users u join fetch u.likes p where p.name = :name", Users.class);
        query.setParameter("name", "Samsung A10");
        List<Users> resultList = query.getResultList();
        for (Users user : resultList) {
            System.out.println("User: "+user.getName());
            for (Product like : user.getLikes()) {
                System.out.println("Products: "+like.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void insertRandomBrand() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (int i = 14; i < 100; i++) {
            Brand brand = new Brand();
            brand.setId(i);
            brand.setName("Brand " + i);
            brand.setDescription("Ini adalah deskripsi dari Brand " + i);
            brand.setCreatedAt(LocalDateTime.now());
            brand.setUpdatedAt(LocalDateTime.now());
            entityManager.persist(brand);
        }

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void limitOffset() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);
        query.setFirstResult(10);
        query.setMaxResults(10);

        List<Brand> resultList = query.getResultList();
        for (Brand brand : resultList) {
            System.out.println(brand.getId() +  " : " +brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void namedQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "Samsung");
        List<Brand> resultList = query.getResultList();
        for (Brand brand : resultList) {
            System.out.println(brand.getId() +  " : " +brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaAgregatQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        criteria.select(builder.array(
                b.get("name"),
                builder.min(p.get("price")),
                builder.max(p.get("price")),
                builder.avg(p.get("price"))
        ));

        criteria.groupBy(b.get("id"));
        criteria.having(builder.greaterThan(builder.min(p.get("price")), 500_000L));

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> resultList = query.getResultList();
        for (Object[] objects : resultList) {
            System.out.println("Brand :" + objects[0]);
            System.out.println("Min :" + objects[1]);
            System.out.println("Max :" + objects[2]);
            System.out.println("Avg :" + objects[3]);
        }

        entityTransaction.commit();
        entityManager.close();
    }
}
