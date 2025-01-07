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
 * create 06/01/25 22.10
 */
public class InheritanceTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void singleTableInsert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Employee employee = new Employee();
        employee.setName("Rahadian");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setName("Trisna");
        manager.setTotalEmployee(9);
        entityManager.persist(manager);

        VicePresident vicePresident = new VicePresident();
        vicePresident.setName("Kemal");
        vicePresident.setTotalManager(10);
        entityManager.persist(vicePresident);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void singleTableFind() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Manager manager = entityManager.find(Manager.class, 6L);
        Assertions.assertEquals("Trisna", manager.getName());


        transaction.commit();
        entityManager.close();

    }


    @Test
    void joinTableInsert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        PaymentGopay paymentGopay = new PaymentGopay();
        paymentGopay.setGopayId("0812747456");
        paymentGopay.setAmount(1_000_000L);
        entityManager.persist(paymentGopay);

        PaymentCreditcard paymentCreditcard = new PaymentCreditcard();
        paymentCreditcard.setBank("BANK JAVA JAKARTA");
        paymentCreditcard.setMaskedCard("345-897");
        paymentCreditcard.setAmount(1_000_000_000L);
        entityManager.persist(paymentCreditcard);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void joinTableFind() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Payment payment = entityManager.find(Payment.class, 5L);
        PaymentGopay paymentGopay = (PaymentGopay) payment;
        Assertions.assertEquals("0812747456", paymentGopay.getGopayId());
        Assertions.assertEquals(1_000_000L, paymentGopay.getAmount());

        transaction.commit();
        entityManager.close();

    }

    @Test
    void tablePerclassInsert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Transaction transaction1 = new Transaction();
        transaction1.setBalance(1_000_000L);
        entityManager.persist(transaction1);

        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setBalance(1_000_000L);
        transactionDebit.setDebitAmount(500_000L);
        entityManager.persist(transactionDebit);

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setCreditAmount(2_000_000L);
        transactionCredit.setBalance(1_000_000L);
        entityManager.persist(transactionCredit);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void tablePerclassFind() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = entityManager.find(Transaction.class, 8L);
        TransactionDebit transactionDebit = entityManager.find(TransactionDebit.class, 9L);
        TransactionCredit transactionCredit = entityManager.find(TransactionCredit.class, 10L);

        entityTransaction.commit();
        entityManager.close();

    }
}
