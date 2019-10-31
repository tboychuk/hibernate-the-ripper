package ua.com.devoxx.util.jpa;

import ua.com.devoxx.util.jpa.exception.JpaUtilException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

public class JpaUtil {
    private static EntityManagerFactory emf;

    public static void init() {
        emf = Persistence.createEntityManagerFactory("DevoxxEntitiesH2");
    }

    public static void close() {
        emf.close();
    }

    public static void performWithinPersistenceContext(Consumer<EntityManager> operation) {
        performReturningWithinPersistenceContext(entityManager -> {
            operation.accept(entityManager);
            return null;
        });
    }

    public static  <T> T performReturningWithinPersistenceContext(Function<EntityManager, T> entityManagerFunction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            T result = entityManagerFunction.apply(em);
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new JpaUtilException(e);
        } finally {
            em.close();
        }
    }
}
